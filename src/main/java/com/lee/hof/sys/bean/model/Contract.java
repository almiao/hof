package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author tangle
 * @since 2021-09-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Contract extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long buyerUserId;

    private Long sellerUserId;

    private String buyerRealName;

    private String buyerPhoneNum;

    private String buyerAddress;

    private String buyerIdCardNum;


    private String sellerRealName;

    private String sellerPhoneNum;

    private String sellerAddress;

    private String sellerIdCardNum;

    /**
     * 车牌号
     */
    private String carLicenseNum;

    /**
     * 车辆型号
     */
    private String carBrandName;

    /**
     * 上牌时间
     */
    private String carLicenseTime;

    /**
     * 行驶里程
     */
    private String carUsedDistance;

    /**
     * 车辆照片
     */
    private String carPictures;

    /**
     * 保险单照片
     */
    private String carInsurancePictures;

    /**
     * 驾驶证照片
     */
    private String driverLicensePictures;

    /**
     * 行驶证照片
     */
    private String driveLicensePictures;

    private String carPrice;

    private String transTaxPayBy;

    private String receiveBankName;

    private String receiveAccountName;

    private String receiveAccountCode;

    private String retainPercent;

    private String transPercent;

    private String buyerSignatureFileId;

    private String sellerSignatureFileId;


    public Long getBuyerUserId() {
        return buyerUserId;
    }

    public void setBuyerUserId(Long buyerUserId) {
        this.buyerUserId = buyerUserId;
    }

    public Long getSellerUserId() {
        return sellerUserId;
    }

    public void setSellerUserId(Long sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public String getTransTaxPayBy() {
        return transTaxPayBy;
    }

    public void setTransTaxPayBy(String transTaxPayBy) {
        this.transTaxPayBy = transTaxPayBy;
    }

    public String getReceiveBankName() {
        return receiveBankName;
    }

    public void setReceiveBankName(String receiveBankName) {
        this.receiveBankName = receiveBankName;
    }

    public String getReceiveAccountName() {
        return receiveAccountName;
    }

    public void setReceiveAccountName(String receiveAccountName) {
        this.receiveAccountName = receiveAccountName;
    }

    public String getReceiveAccountCode() {
        return receiveAccountCode;
    }

    public void setReceiveAccountCode(String receiveAccountCode) {
        this.receiveAccountCode = receiveAccountCode;
    }

    public String getRetainPercent() {
        return retainPercent;
    }

    public void setRetainPercent(String retainPercent) {
        this.retainPercent = retainPercent;
    }

    public String getTransPercent() {
        return transPercent;
    }

    public void setTransPercent(String transPercent) {
        this.transPercent = transPercent;
    }

    public String getBuyerSignatureFileId() {
        return buyerSignatureFileId;
    }

    public void setBuyerSignatureFileId(String buyerSignatureFileId) {
        this.buyerSignatureFileId = buyerSignatureFileId;
    }

    public String getSellerSignatureFileId() {
        return sellerSignatureFileId;
    }

    public void setSellerSignatureFileId(String sellerSignatureFileId) {
        this.sellerSignatureFileId = sellerSignatureFileId;
    }

    public String getCarInsurancePictures() {
        return carInsurancePictures;
    }

    public void setCarInsurancePictures(String carInsurancePictures) {
        this.carInsurancePictures = carInsurancePictures;
    }

    public String getDriveLicensePictures() {
        return driveLicensePictures;
    }

    public void setDriveLicensePictures(String driveLicensePictures) {
        this.driveLicensePictures = driveLicensePictures;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getBuyerRealName() {
        return buyerRealName;
    }

    public void setBuyerRealName(String buyerRealName) {
        this.buyerRealName = buyerRealName;
    }

    public String getBuyerPhoneNum() {
        return buyerPhoneNum;
    }

    public void setBuyerPhoneNum(String buyerPhoneNum) {
        this.buyerPhoneNum = buyerPhoneNum;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerIdCardNum() {
        return buyerIdCardNum;
    }

    public void setBuyerIdCardNum(String buyerIdCardNum) {
        this.buyerIdCardNum = buyerIdCardNum;
    }

    public String getSellerRealName() {
        return sellerRealName;
    }

    public void setSellerRealName(String sellerRealName) {
        this.sellerRealName = sellerRealName;
    }

    public String getSellerPhoneNum() {
        return sellerPhoneNum;
    }

    public void setSellerPhoneNum(String sellerPhoneNum) {
        this.sellerPhoneNum = sellerPhoneNum;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerIdCardNum() {
        return sellerIdCardNum;
    }

    public void setSellerIdCardNum(String sellerIdCardNum) {
        this.sellerIdCardNum = sellerIdCardNum;
    }

    public String getCarLicenseNum() {
        return carLicenseNum;
    }

    public void setCarLicenseNum(String carLicenseNum) {
        this.carLicenseNum = carLicenseNum;
    }

    public String getCarBrandName() {
        return carBrandName;
    }

    public void setCarBrandName(String carBrandName) {
        this.carBrandName = carBrandName;
    }

    public String getCarLicenseTime() {
        return carLicenseTime;
    }

    public void setCarLicenseTime(String carLicenseTime) {
        this.carLicenseTime = carLicenseTime;
    }

    public String getCarUsedDistance() {
        return carUsedDistance;
    }

    public void setCarUsedDistance(String carUsedDistance) {
        this.carUsedDistance = carUsedDistance;
    }

    public String getCarPictures() {
        return carPictures;
    }

    public void setCarPictures(String carPictures) {
        this.carPictures = carPictures;
    }

    public String getDriverLicensePictures() {
        return driverLicensePictures;
    }

    public void setDriverLicensePictures(String driverLicensePictures) {
        this.driverLicensePictures = driverLicensePictures;
    }

}
