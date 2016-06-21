package com.xinxin.wotplus.model;

import java.util.List;
import java.util.Map;

/**
 * Created by xinxin on 2016/6/21.
 */
public class XvmTankTopAll {

    private Map tankjsmap;
    private List<XvmTankTopVO> tankTopVOs;

    public Map getTankjsmap() {
        return tankjsmap;
    }

    public void setTankjsmap(Map tankjsmap) {
        this.tankjsmap = tankjsmap;
    }

    public List<XvmTankTopVO> getTankTopVOs() {
        return tankTopVOs;
    }

    public void setTankTopVOs(List<XvmTankTopVO> tankTopVOs) {
        this.tankTopVOs = tankTopVOs;
    }
}
