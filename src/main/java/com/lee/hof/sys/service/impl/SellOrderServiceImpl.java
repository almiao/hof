package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.common.exception.HofException;
import com.lee.hof.sys.bean.SellOrderStatus;
import com.lee.hof.sys.bean.dto.SellOrderListDto;
import com.lee.hof.sys.bean.model.FileManager;
import com.lee.hof.sys.bean.model.SellOrder;
import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.mapper.FileManagerMapper;
import com.lee.hof.sys.mapper.SellOrderMapper;
import com.lee.hof.sys.service.SellOrderService;
import com.lee.hof.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SellOrderServiceImpl extends ServiceImpl<SellOrderMapper, SellOrder> implements SellOrderService {

    @Resource
    SellOrderMapper sellOrderMapper;

    @Resource
    UserService userService;

    @Override
    public Long newSellOrder() {
        long userId = UserContext.getUser().getId();
        SellOrder existOrder = sellOrderMapper.selectOne(new QueryWrapper<SellOrder>()
                .eq("user_id", userId)
                .eq("valid_status", 1)
                .eq("status", SellOrderStatus.EDITING.getCode())
                .last(" limit 1"));

        if (existOrder != null) {
            existOrder.setValidStatus(1);
            sellOrderMapper.updateById(existOrder);
        }

        SellOrder newOrder = new SellOrder();
        newOrder.setValidStatus(1);
        newOrder.setUserId(userId);
        newOrder.setStatus(SellOrderStatus.EDITING.getCode());
        sellOrderMapper.insert(newOrder);

        return newOrder.getId();
    }

    @Override
    public SellOrder saveSellOrder(SellOrder sellOrder) {
        SellOrder existOrder;

        if (sellOrder.getId() == null) {
            existOrder = new SellOrder();
            existOrder.setStatus(SellOrderStatus.EDITING.getCode());
        } else {
            existOrder = sellOrderMapper.selectOne(new QueryWrapper<SellOrder>().eq("id", sellOrder.getId()).eq("user_id", UserContext.getUser().getId()).eq("valid_status", 1).last(" limit 1"));
        }

        if (existOrder.getStatus() != SellOrderStatus.EDITING.getCode()) {
            throw new HofException("状态非编辑中");
        }

        BeanUtils.copyProperties(sellOrder, existOrder, "createTime", "validStatus", "status");

        if(StringUtils.isNoneBlank(sellOrder.getFileIds()) && StringUtils.isEmpty(sellOrder.getCoverFileId())){
            String[] file = StringUtils.split(sellOrder.getFileIds(), ",");
            existOrder.setCoverFileId(file[0]);
        }

        if(StringUtils.isNoneBlank(existOrder.getCoverFileId())){

            FileManager fileManager = fileManagerMapper.getByUuid(existOrder.getCoverFileId());
            existOrder.setCoverHeight(fileManager.getHeight());
            existOrder.setCoverWidth(fileManager.getWidth());
        }

        existOrder.setUserId(UserContext.getUserId());
        existOrder.setValidStatus(1);

        if (existOrder.getId() == null) {
            sellOrderMapper.insert(existOrder);
        } else {
            sellOrderMapper.updateById(existOrder);
        }
        return existOrder;
    }

    @Override
    public SellOrder validate(String sellOrderId) {

        SellOrder existOrder = sellOrderMapper.selectOne(new QueryWrapper<SellOrder>().eq("id", sellOrderId).eq("user_id", UserContext.getUser().getId()).last(" limit 1"));

        if (existOrder == null) {
            throw new HofException("不存在");
        }

        existOrder.setStatus(SellOrderStatus.PUBLISHED.getCode());

        sellOrderMapper.updateById(existOrder);

        return convert(existOrder);
    }

    @Override
    public SellOrder getEditingSellOrder() {
        long userId = UserContext.getUser().getId();

        return sellOrderMapper.selectOne(new QueryWrapper<SellOrder>().eq("user_id", userId)
                .eq("valid_status", 1)
                .eq("status", SellOrderStatus.EDITING.getCode())
                .last("limit 1"));
    }

    @Override
    public SellOrder getSellOrderDetail(Long orderId) {
        long userId = UserContext.getUser().getId();

        return convert(sellOrderMapper.selectOne(new QueryWrapper<SellOrder>().eq("user_id", userId)
                .eq("valid_status", 1)
                .eq("id", orderId)
                .last("limit 1")));
    }

    @Override
    public Page<SellOrder> listPublished(SellOrderListDto dto) {
        QueryWrapper<SellOrder> queryWrapper = new QueryWrapper<>();
        if (dto.getSearchTxt() != null) {
            queryWrapper.and(wrapper->wrapper.like("base_info_text", dto.getSearchTxt()).or().like("brand_name",dto.getSearchTxt()));
        }

        if (dto.getDistanceRangeLow() != null) {
            queryWrapper.ge("distance", dto.getDistanceRangeLow());
        }

        if (dto.getDistanceRangeHigh() != null) {
            queryWrapper.le("distance", dto.getDistanceRangeHigh());
        }

        if (dto.getPriceRangeLow() != null) {
            queryWrapper.ge("price", dto.getPriceRangeLow());
        }

        if (dto.getPriceRangeHigh() != null) {
            queryWrapper.le("price", dto.getPriceRangeHigh());
        }

        queryWrapper.like(dto.getBrandName() != null ,"brand_name", dto.getBrandName());

        queryWrapper.eq("valid_status", 1);

        queryWrapper.eq("status", SellOrderStatus.PUBLISHED.getCode());

        queryWrapper.orderByAsc(StringUtils.isNotBlank(dto.getOrderByFieldAsc()), dto.getOrderByFieldAsc());

        queryWrapper.orderByDesc(StringUtils.isNoneBlank(dto.getOrderByFieldDesc()), dto.getOrderByFieldDesc());

        Page<SellOrder> result = sellOrderMapper.selectPage(new Page<>(dto.getPageNum(), dto.getPageSize()), queryWrapper);

        result.getRecords().forEach(this::convert);
        return result;

    }


    private SellOrder convert(SellOrder sellOrder){
        User user = userService.getUserById(sellOrder.getUserId());
        sellOrder.setUser(user);
        return sellOrder;
    }



    @Resource
    private FileManagerMapper fileManagerMapper;
}
