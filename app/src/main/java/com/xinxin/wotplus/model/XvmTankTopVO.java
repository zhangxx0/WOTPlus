package com.xinxin.wotplus.model;

import java.io.Serializable;

/**
 * Created by xinxin on 2016/6/21.
 * 坦克榜单数据
 */
public class XvmTankTopVO implements Serializable{

    private int tankid;

    private XvmTankTopSingleVO singleVO;


    public int getTankid() {
        return tankid;
    }

    public void setTankid(int tankid) {
        this.tankid = tankid;
    }

    public XvmTankTopSingleVO getSingleVO() {
        return singleVO;
    }

    public void setSingleVO(XvmTankTopSingleVO singleVO) {
        this.singleVO = singleVO;
    }
}
