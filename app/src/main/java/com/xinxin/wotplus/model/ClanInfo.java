package com.xinxin.wotplus.model;

/**
 * Created by xinxin on 2016/3/27.
 *
 * 军团信息
 * 用于军团信息的获取；有用信息提取到Woter；
 */
public class ClanInfo {


    /**
     * status : ok
     * data : {"show_points_of_glory_history_link":false,"show_invite_to_clan_link":"\n","clan_link":"\n<li><a href=\"http://north.warsaga.cn/clans/2083/?utm_campaign=wot-portal&amp;utm_medium=link&amp;utm_source=user-profile\" class= 'js-clan'>我的军团<\/a><\/li>","clan_block":"\n<div class=\"b-profile-clan js-tooltip\" id=\"js-profile-clan\">\n    <div class=\"b-photo\">\n        <div style=\"background: #fc0505;\" class=\"b-color\"><!-- --><\/div>\n        <a class=\"b-photo_link\" href=\"http://north.warsaga.cn/clans/2083?utm_campaign=wot-portal&amp;utm_medium=link&amp;utm_source=user-profile\">\n            <img alt=\"【小伙伴】--我们是兄弟--『那些年我们一起玩过的坦克』\" src=\"http://north.warsaga.cn/clans/media/clans/emblems/cl_083/2083/emblem_64x64.png\" width=\"32\" height=\"32\"><!--32x32-->\n        <\/a>\n    <\/div>\n    <div class=\"b-text\">\n        <div class=\"b-text-wrpr\">\n            <a class=\"b-link-clan\" href=\"http://north.warsaga.cn/clans/2083?utm_campaign=wot-portal&amp;utm_medium=link&amp;utm_source=user-profile\">\n                <span class=\"b-link-clan_tag tag\">[小伙伴]<\/span>&nbsp;<span class=\"name\">【小伙伴】--我们是兄弟--『那些年我们一起玩过的坦克』<\/span>\n            <\/a>\n        <\/div>\n        <div class=\"b-statistic\">\n            <p class=\"b-statistic_item\">职务: <span class=\"number\">招募<\/span>\n            <\/p>\n            <p class=\"b-statistic_item\">在军团中服役天数: <span class=\"number\">1065<\/span>\n            <\/p>\n        <\/div>\n    <\/div>\n<\/div>\n\n\n<div class=\"b-tooltip-main\" id=\"js-profile-clan_tooltip\">\n    <h5>\n        <a class=\"b-link-clan\" href=\"http://north.warsaga.cn/clans/2083?utm_campaign=wot-portal&amp;utm_medium=link&amp;utm_source=user-profile\">\n            <span class=\"b-link-clan_tag tag\">[小伙伴]<\/span>&nbsp;<span class=\"name\">【小伙伴】--我们是兄弟--『那些年我们一起玩过的坦克』<\/span>\n        <\/a>\n    <\/h5>\n    <div class=\"b-statistic\">\n        <p class=\"b-statistic_item\">职务: <span class=\"number\">招募<\/span>\n        <\/p>\n        <p class=\"b-statistic_item\">在军团中服役天数: <span class=\"number\">1065<\/span>\n        <\/p>\n    <\/div>\n<\/div>","recrutisation_block":"\n\n    \n        <div class=\"b-sidebar-widget clearfix\">\n    <div class=\"b-sidebar-widget_inner b-sidebar-widget_inner__recrut\">\n        <h2 class=\"b-sidebar-widget_title\">招募中心<\/h2>\n            \n            \n        <p class=\"b-sidebar-widget_text\">登录<a href=\"/auth/oid/new/?next=http%3A//north.warsaga.cn/clans/recruitstation/%3Futm_campaign%3Dwot-portal%26utm_medium%3Dlink%26utm_source%3Duser-profile%26utm_content%3Drecruit-form\" class=\"js-auth-openid-link\" data-next-url=\"http://north.warsaga.cn/clans/recruitstation/?utm_campaign=wot-portal&utm_medium=link&utm_source=user-profile&utm_content=recruit-form\"> 账号 <\/a>来加入军团或者招募新的军团成员。<\/p>\n    <\/div>\n<\/div>\n    "}
     */

    private String status;
    /**
     * show_points_of_glory_history_link : false
     * show_invite_to_clan_link :

     * clan_link :
     <li><a href="http://north.warsaga.cn/clans/2083/?utm_campaign=wot-portal&amp;utm_medium=link&amp;utm_source=user-profile" class= 'js-clan'>我的军团</a></li>
     * clan_block :
     <div class="b-profile-clan js-tooltip" id="js-profile-clan">
     <div class="b-photo">
     <div style="background: #fc0505;" class="b-color"><!-- --></div>
     <a class="b-photo_link" href="http://north.warsaga.cn/clans/2083?utm_campaign=wot-portal&amp;utm_medium=link&amp;utm_source=user-profile">
     <img alt="【小伙伴】--我们是兄弟--『那些年我们一起玩过的坦克』" src="http://north.warsaga.cn/clans/media/clans/emblems/cl_083/2083/emblem_64x64.png" width="32" height="32"><!--32x32-->
     </a>
     </div>
     <div class="b-text">
     <div class="b-text-wrpr">
     <a class="b-link-clan" href="http://north.warsaga.cn/clans/2083?utm_campaign=wot-portal&amp;utm_medium=link&amp;utm_source=user-profile">
     <span class="b-link-clan_tag tag">[小伙伴]</span>&nbsp;<span class="name">【小伙伴】--我们是兄弟--『那些年我们一起玩过的坦克』</span>
     </a>
     </div>
     <div class="b-statistic">
     <p class="b-statistic_item">职务: <span class="number">招募</span>
     </p>
     <p class="b-statistic_item">在军团中服役天数: <span class="number">1065</span>
     </p>
     </div>
     </div>
     </div>


     <div class="b-tooltip-main" id="js-profile-clan_tooltip">
     <h5>
     <a class="b-link-clan" href="http://north.warsaga.cn/clans/2083?utm_campaign=wot-portal&amp;utm_medium=link&amp;utm_source=user-profile">
     <span class="b-link-clan_tag tag">[小伙伴]</span>&nbsp;<span class="name">【小伙伴】--我们是兄弟--『那些年我们一起玩过的坦克』</span>
     </a>
     </h5>
     <div class="b-statistic">
     <p class="b-statistic_item">职务: <span class="number">招募</span>
     </p>
     <p class="b-statistic_item">在军团中服役天数: <span class="number">1065</span>
     </p>
     </div>
     </div>
     * recrutisation_block :


     <div class="b-sidebar-widget clearfix">
     <div class="b-sidebar-widget_inner b-sidebar-widget_inner__recrut">
     <h2 class="b-sidebar-widget_title">招募中心</h2>


     <p class="b-sidebar-widget_text">登录<a href="/auth/oid/new/?next=http%3A//north.warsaga.cn/clans/recruitstation/%3Futm_campaign%3Dwot-portal%26utm_medium%3Dlink%26utm_source%3Duser-profile%26utm_content%3Drecruit-form" class="js-auth-openid-link" data-next-url="http://north.warsaga.cn/clans/recruitstation/?utm_campaign=wot-portal&utm_medium=link&utm_source=user-profile&utm_content=recruit-form"> 账号 </a>来加入军团或者招募新的军团成员。</p>
     </div>
     </div>

     */

    private DataEntity data;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private boolean show_points_of_glory_history_link;
        private String show_invite_to_clan_link;
        private String clan_link;
        private String clan_block;
        private String recrutisation_block;

        public void setShow_points_of_glory_history_link(boolean show_points_of_glory_history_link) {
            this.show_points_of_glory_history_link = show_points_of_glory_history_link;
        }

        public void setShow_invite_to_clan_link(String show_invite_to_clan_link) {
            this.show_invite_to_clan_link = show_invite_to_clan_link;
        }

        public void setClan_link(String clan_link) {
            this.clan_link = clan_link;
        }

        public void setClan_block(String clan_block) {
            this.clan_block = clan_block;
        }

        public void setRecrutisation_block(String recrutisation_block) {
            this.recrutisation_block = recrutisation_block;
        }

        public boolean isShow_points_of_glory_history_link() {
            return show_points_of_glory_history_link;
        }

        public String getShow_invite_to_clan_link() {
            return show_invite_to_clan_link;
        }

        public String getClan_link() {
            return clan_link;
        }

        public String getClan_block() {
            return clan_block;
        }

        public String getRecrutisation_block() {
            return recrutisation_block;
        }
    }
}
