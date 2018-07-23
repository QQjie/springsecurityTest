package com.cnsunet.kjw.model;

public class FlowModel {
    //入境流量
    private float in=-1;
    //出境流量
    private float out=-1;
    //总境流量
    private float all=-1;
    //记录时间
    private String recordTime;
    //分院名称
    private String areaName;
    //协议
    private Integer linkLayerProtocal;

    public float getIn() {
        return in;
    }

    public void setIn(float in) {
        this.in = in;
    }

    public float getOut() {
        return out;
    }

    public void setOut(float out) {
        this.out = out;
    }

    public float getAll() {
        return all;
    }

    public void setAll(float all) {
        this.all = all;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getLinkLayerProtocal() {
        return linkLayerProtocal;
    }

    public void setLinkLayerProtocal(Integer linkLayerProtocal) {
        this.linkLayerProtocal = linkLayerProtocal;
    }
}
