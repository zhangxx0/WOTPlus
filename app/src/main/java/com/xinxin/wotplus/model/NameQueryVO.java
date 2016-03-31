package com.xinxin.wotplus.model;

import java.util.List;

/**
 * Created by xinxin on 2016/3/31.
 *
 * 昵称查询返回的VO
 */
public class NameQueryVO {

    /**
     * show_more_accounts : false
     * name_gt :
     * response : [{"account_wins":54.433508812085144,"account_id":"1509154099","account_battles":21845,"account_profile":"/zh-cn/community/accounts/1509154099-%E5%BA%B7%E6%81%A9%E9%A5%AD_/","clan_url":"http://north.warsaga.cn/clans/2083/?utm_campaign=wot-portal&utm_medium=link","account_name":"康恩饭_","account_exp":13040872,"clan_tag":"小伙伴"}]
     */

    private boolean show_more_accounts;
    private String name_gt;
    /**
     * account_wins : 54.433508812085144
     * account_id : 1509154099
     * account_battles : 21845
     * account_profile : /zh-cn/community/accounts/1509154099-%E5%BA%B7%E6%81%A9%E9%A5%AD_/
     * clan_url : http://north.warsaga.cn/clans/2083/?utm_campaign=wot-portal&utm_medium=link
     * account_name : 康恩饭_
     * account_exp : 13040872
     * clan_tag : 小伙伴
     */

    private List<ResponseEntity> response;

    public void setShow_more_accounts(boolean show_more_accounts) {
        this.show_more_accounts = show_more_accounts;
    }

    public void setName_gt(String name_gt) {
        this.name_gt = name_gt;
    }

    public void setResponse(List<ResponseEntity> response) {
        this.response = response;
    }

    public boolean isShow_more_accounts() {
        return show_more_accounts;
    }

    public String getName_gt() {
        return name_gt;
    }

    public List<ResponseEntity> getResponse() {
        return response;
    }

    public static class ResponseEntity {
        private double account_wins;
        private String account_id;
        private int account_battles;
        private String account_profile;
        private String clan_url;
        private String account_name;
        private int account_exp;
        private String clan_tag;

        public void setAccount_wins(double account_wins) {
            this.account_wins = account_wins;
        }

        public void setAccount_id(String account_id) {
            this.account_id = account_id;
        }

        public void setAccount_battles(int account_battles) {
            this.account_battles = account_battles;
        }

        public void setAccount_profile(String account_profile) {
            this.account_profile = account_profile;
        }

        public void setClan_url(String clan_url) {
            this.clan_url = clan_url;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public void setAccount_exp(int account_exp) {
            this.account_exp = account_exp;
        }

        public void setClan_tag(String clan_tag) {
            this.clan_tag = clan_tag;
        }

        public double getAccount_wins() {
            return account_wins;
        }

        public String getAccount_id() {
            return account_id;
        }

        public int getAccount_battles() {
            return account_battles;
        }

        public String getAccount_profile() {
            return account_profile;
        }

        public String getClan_url() {
            return clan_url;
        }

        public String getAccount_name() {
            return account_name;
        }

        public int getAccount_exp() {
            return account_exp;
        }

        public String getClan_tag() {
            return clan_tag;
        }
    }
}
