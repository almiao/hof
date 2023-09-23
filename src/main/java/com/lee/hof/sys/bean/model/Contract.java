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

    private String buyerRealName;

    private String buyerPhoneNum;

    private String buyerAddress;

    private String buyerIdCardNum;


    private String sellerRealName;

    private String sellerPhoneNum;

    private String sellerAddress;

    private String sellerIdCardNum;


    private String carLicenseNum;

    private String carBrandName;

    private String carLicenseTime;

    private String carUsedDistance;

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
