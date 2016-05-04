package com.xinxin.wotplus.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xinxin on 2016/5/4.
 */
public class KongzhongUserInfo implements Serializable {


    /**
     * show_more_accounts : true
     * name_gt : 折腾5号不死之谜
     * response : [{"account_wins":56.427713255907086,"account_id":"1800661743","account_battles":2497,"account_profile":"/zh-cn/community/accounts/1800661743-%E6%8A%98%E8%85%BE5%E5%8F%B7/","clan_url":"","account_name":"折腾5号","account_exp":2123898,"clan_tag":""},{"account_wins":49.24738219895288,"account_id":"1820022278","account_battles":3056,"account_profile":"/zh-cn/community/accounts/1820022278-%E6%8A%98%E8%85%BE5%E5%8F%B7-/","clan_url":"","account_name":"折腾5号-","account_exp":1550774,"clan_tag":""},{"account_wins":0,"account_id":"1822457353","account_battles":2,"account_profile":"/zh-cn/community/accounts/1822457353-%E6%8A%98%E8%85%BE5%E5%8F%B7--/","clan_url":"","account_name":"折腾5号--","account_exp":78,"clan_tag":""},{"account_wins":0,"account_id":"1827076128","account_battles":0,"account_profile":"/zh-cn/community/accounts/1827076128-%E6%8A%98%E8%85%BE5%E5%8F%B7------/","clan_url":"","account_name":"折腾5号------","account_exp":0,"clan_tag":""},{"account_wins":66.66666666666666,"account_id":"1826754626","account_battles":3,"account_profile":"/zh-cn/community/accounts/1826754626-%E6%8A%98%E8%85%BE5%E5%8F%B7-%E8%99%8E%E7%89%99%E7%9B%B4%E6%92%AD/","clan_url":"","account_name":"折腾5号-虎牙直播","account_exp":570,"clan_tag":""},{"account_wins":0,"account_id":"1827168689","account_battles":0,"account_profile":"/zh-cn/community/accounts/1827168689-%E6%8A%98%E8%85%BE5%E5%8F%B70396/","clan_url":"","account_name":"折腾5号0396","account_exp":0,"clan_tag":""},{"account_wins":44.230769230769226,"account_id":"1821973540","account_battles":52,"account_profile":"/zh-cn/community/accounts/1821973540-%E6%8A%98%E8%85%BE5%E5%8F%B71/","clan_url":"","account_name":"折腾5号1","account_exp":6482,"clan_tag":""},{"account_wins":54.54545454545454,"account_id":"1821329046","account_battles":11,"account_profile":"/zh-cn/community/accounts/1821329046-%E6%8A%98%E8%85%BE5%E5%8F%B72/","clan_url":"","account_name":"折腾5号2","account_exp":895,"clan_tag":""},{"account_wins":0,"account_id":"1821973390","account_battles":0,"account_profile":"/zh-cn/community/accounts/1821973390-%E6%8A%98%E8%85%BE5%E5%8F%B73/","clan_url":"","account_name":"折腾5号3","account_exp":0,"clan_tag":""},{"account_wins":0,"account_id":"1825362151","account_battles":0,"account_profile":"/zh-cn/community/accounts/1825362151-%E6%8A%98%E8%85%BE5%E5%8F%B76/","clan_url":"","account_name":"折腾5号6","account_exp":0,"clan_tag":""},{"account_wins":48,"account_id":"1826119240","account_battles":25,"account_profile":"/zh-cn/community/accounts/1826119240-%E6%8A%98%E8%85%BE5%E5%8F%B7_/","clan_url":"","account_name":"折腾5号_","account_exp":4422,"clan_tag":""},{"account_wins":0,"account_id":"1826737826","account_battles":0,"account_profile":"/zh-cn/community/accounts/1826737826-%E6%8A%98%E8%85%BE5%E5%8F%B7__/","clan_url":"","account_name":"折腾5号__","account_exp":0,"clan_tag":""},{"account_wins":0,"account_id":"1821931450","account_battles":0,"account_profile":"/zh-cn/community/accounts/1821931450-%E6%8A%98%E8%85%BE5%E5%8F%B7___OMG/","clan_url":"","account_name":"折腾5号___OMG","account_exp":0,"clan_tag":""},{"account_wins":51.8213866039953,"account_id":"1817292883","account_battles":851,"account_profile":"/zh-cn/community/accounts/1817292883-%E6%8A%98%E8%85%BE5%E5%8F%B7_Toss5th/","clan_url":"","account_name":"折腾5号_Toss5th","account_exp":268071,"clan_tag":""},{"account_wins":33.33333333333333,"account_id":"1821373155","account_battles":3,"account_profile":"/zh-cn/community/accounts/1821373155-%E6%8A%98%E8%85%BE5%E5%8F%B7A/","clan_url":"","account_name":"折腾5号A","account_exp":964,"clan_tag":""},{"account_wins":60.952380952380956,"account_id":"1826495979","account_battles":105,"account_profile":"/zh-cn/community/accounts/1826495979-%E6%8A%98%E8%85%BE5%E5%8F%B7i/","clan_url":"","account_name":"折腾5号i","account_exp":26645,"clan_tag":""},{"account_wins":100,"account_id":"1826595154","account_battles":1,"account_profile":"/zh-cn/community/accounts/1826595154-%E6%8A%98%E8%85%BE5%E5%8F%B7o/","clan_url":"","account_name":"折腾5号o","account_exp":163,"clan_tag":""},{"account_wins":37.03703703703704,"account_id":"1822642295","account_battles":81,"account_profile":"/zh-cn/community/accounts/1822642295-%E6%8A%98%E8%85%BE5%E5%8F%B7S/","clan_url":"","account_name":"折腾5号S","account_exp":10449,"clan_tag":""},{"account_wins":0,"account_id":"1826155615","account_battles":0,"account_profile":"/zh-cn/community/accounts/1826155615-%E6%8A%98%E8%85%BE5%E5%8F%B7ss/","clan_url":"","account_name":"折腾5号ss","account_exp":0,"clan_tag":""},{"account_wins":91.66666666666666,"account_id":"1831284798","account_battles":12,"account_profile":"/zh-cn/community/accounts/1831284798-%E6%8A%98%E8%85%BE5%E5%8F%B7star-lord/","clan_url":"","account_name":"折腾5号star-lord","account_exp":10579,"clan_tag":""},{"account_wins":42.857142857142854,"account_id":"1824476218","account_battles":7,"account_profile":"/zh-cn/community/accounts/1824476218-%E6%8A%98%E8%85%BE5%E5%8F%B7T59/","clan_url":"","account_name":"折腾5号T59","account_exp":908,"clan_tag":""},{"account_wins":0,"account_id":"1826057012","account_battles":0,"account_profile":"/zh-cn/community/accounts/1826057012-%E6%8A%98%E8%85%BE5%E5%8F%B7Toss5th/","clan_url":"","account_name":"折腾5号Toss5th","account_exp":0,"clan_tag":""},{"account_wins":0,"account_id":"1826124013","account_battles":0,"account_profile":"/zh-cn/community/accounts/1826124013-%E6%8A%98%E8%85%BE5%E5%8F%B7V/","clan_url":"","account_name":"折腾5号V","account_exp":0,"clan_tag":""},{"account_wins":47.355769230769226,"account_id":"1815937791","account_battles":5824,"account_profile":"/zh-cn/community/accounts/1815937791-%E6%8A%98%E8%85%BE5%E5%8F%B7X%E8%B5%A4%E8%89%B2%E7%81%AB%E9%A3%8E/","clan_url":"","account_name":"折腾5号X赤色火风","account_exp":2000464,"clan_tag":""},{"account_wins":0,"account_id":"1827169308","account_battles":0,"account_profile":"/zh-cn/community/accounts/1827169308-%E6%8A%98%E8%85%BE5%E5%8F%B7%E4%B8%8D%E6%AD%BB%E4%B9%8B%E8%B0%9C/","clan_url":"","account_name":"折腾5号不死之谜","account_exp":0,"clan_tag":""}]
     */

    private boolean show_more_accounts;
    private String name_gt;
    /**
     * account_wins : 56.427713255907086
     * account_id : 1800661743
     * account_battles : 2497
     * account_profile : /zh-cn/community/accounts/1800661743-%E6%8A%98%E8%85%BE5%E5%8F%B7/
     * clan_url :
     * account_name : 折腾5号
     * account_exp : 2123898
     * clan_tag :
     */

    private List<ResponseEntity> response;

    public boolean isShow_more_accounts() {
        return show_more_accounts;
    }

    public void setShow_more_accounts(boolean show_more_accounts) {
        this.show_more_accounts = show_more_accounts;
    }

    public String getName_gt() {
        return name_gt;
    }

    public void setName_gt(String name_gt) {
        this.name_gt = name_gt;
    }

    public List<ResponseEntity> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseEntity> response) {
        this.response = response;
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

        public double getAccount_wins() {
            return account_wins;
        }

        public void setAccount_wins(double account_wins) {
            this.account_wins = account_wins;
        }

        public String getAccount_id() {
            return account_id;
        }

        public void setAccount_id(String account_id) {
            this.account_id = account_id;
        }

        public int getAccount_battles() {
            return account_battles;
        }

        public void setAccount_battles(int account_battles) {
            this.account_battles = account_battles;
        }

        public String getAccount_profile() {
            return account_profile;
        }

        public void setAccount_profile(String account_profile) {
            this.account_profile = account_profile;
        }

        public String getClan_url() {
            return clan_url;
        }

        public void setClan_url(String clan_url) {
            this.clan_url = clan_url;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public int getAccount_exp() {
            return account_exp;
        }

        public void setAccount_exp(int account_exp) {
            this.account_exp = account_exp;
        }

        public String getClan_tag() {
            return clan_tag;
        }

        public void setClan_tag(String clan_tag) {
            this.clan_tag = clan_tag;
        }
    }
}
