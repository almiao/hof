package com.lee.hof.sys.bean.model;



import java.io.Serializable;
import java.util.Date;

public class CarScore implements Serializable {

    private static final long serialVersionUID = 1876655654053364580L;

    private Long id;

    private Long authorId;

    /**
     * 车辆型号
     */
    private String carSeries;

    private Long carPriceId;

    /**
     * 购车信息
     */
    private CarPrice carPrice = new CarPrice();

    /**
     * 舒适性
     */
    private Float comfortScore;

    /**
     * 可靠性
     */
    private Float reliabilityScore;

    /**
     * 外观
     */
    private Float appearanceScore;

    /**
     * 性能
     */
    private Float performanceScore;

    /**
     * 空间
     */
    private Float spaceScore;

    /**
     * 综合评价
     */
    private String overallEvaluation;


    private Date createTime;

    private Date updateTime;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries;
    }

    public Long getCarPriceId() {
        return carPriceId;
    }

    public void setCarPriceId(Long carPriceId) {
        this.carPriceId = carPriceId;
    }

    public CarPrice getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(CarPrice carPrice) {
        this.carPrice = carPrice;
    }

    public Float getComfortScore() {
        return comfortScore;
    }

    public void setComfortScore(Float comfortScore) {
        this.comfortScore = comfortScore;
    }

    public Float getReliabilityScore() {
        return reliabilityScore;
    }

    public void setReliabilityScore(Float reliabilityScore) {
        this.reliabilityScore = reliabilityScore;
    }

    public Float getAppearanceScore() {
        return appearanceScore;
    }

    public void setAppearanceScore(Float appearanceScore) {
        this.appearanceScore = appearanceScore;
    }

    public Float getPerformanceScore() {
        return performanceScore;
    }

    public void setPerformanceScore(Float performanceScore) {
        this.performanceScore = performanceScore;
    }

    public Float getSpaceScore() {
        return spaceScore;
    }

    public void setSpaceScore(Float spaceScore) {
        this.spaceScore = spaceScore;
    }

    public String getOverallEvaluation() {
        return overallEvaluation;
    }

    public void setOverallEvaluation(String overallEvaluation) {
        this.overallEvaluation = overallEvaluation;
    }
}
