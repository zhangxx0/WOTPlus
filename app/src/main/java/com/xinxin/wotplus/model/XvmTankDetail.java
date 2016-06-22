package com.xinxin.wotplus.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by xinxin on 2016/6/23.
 * 战斗明细
 */
public class XvmTankDetail implements Serializable {

    private Map tankDict;
    private Map tankmap;

    public Map getTankDict() {
        return tankDict;
    }

    public void setTankDict(Map tankDict) {
        this.tankDict = tankDict;
    }

    public Map getTankmap() {
        return tankmap;
    }

    public void setTankmap(Map tankmap) {
        this.tankmap = tankmap;
    }
}
