package com.xinxin.wotplus.util.mapper;

import com.xinxin.wotplus.model.ClanInfo;
import com.xinxin.wotplus.model.ClanInfoUsed;
import com.xinxin.wotplus.util.JsoupHtmlUtil;

import rx.functions.Func1;

/**
 * Created by xinxin on 2016/5/20.
 */
public class ClanInfoToWoterMapper implements Func1<ClanInfo, ClanInfoUsed> {

    private static ClanInfoToWoterMapper INSTANCE = new ClanInfoToWoterMapper();

    private ClanInfoToWoterMapper() {

    }

    public static ClanInfoToWoterMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public ClanInfoUsed call(ClanInfo clanInfo) {
        // 将军团信息提取到ClanInfoUsed
        ClanInfoUsed c = null;
        if (clanInfo != null) {
            String clanString = clanInfo.getData().getClan_block().toString();
            c = JsoupHtmlUtil.handleClaninfo(clanString);
        }

        return c;
    }
}
