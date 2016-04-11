package com.xinxin.wotplus.model;

import java.io.Serializable;

/**
 * Created by xinxin on 2016/4/11.
 * 版本信息
 */
public class VersionVo implements Serializable {


    /**
     * name : WOTPlus
     * version : 1
     * changelog : First debug
     * updated_at : 1460383133
     * versionShort : 0.1
     * build : 1
     * installUrl : http://download.fir.im/v2/app/install/570bad9000fc7436da000007?download_token=4ea5f79c0c954e0ea75a680d6ee03ddd
     * install_url : http://download.fir.im/v2/app/install/570bad9000fc7436da000007?download_token=4ea5f79c0c954e0ea75a680d6ee03ddd
     * direct_install_url : http://download.fir.im/v2/app/install/570bad9000fc7436da000007?download_token=4ea5f79c0c954e0ea75a680d6ee03ddd
     * update_url : http://fir.im/rlps
     * binary : {"fsize":5681684}
     */

    private String name;
    private String version;
    private String changelog;
    private int updated_at;
    private String versionShort;
    private String build;
    private String installUrl;
    private String install_url;
    private String direct_install_url;
    private String update_url;
    /**
     * fsize : 5681684
     */

    private BinaryEntity binary;

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public void setVersionShort(String versionShort) {
        this.versionShort = versionShort;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public void setInstallUrl(String installUrl) {
        this.installUrl = installUrl;
    }

    public void setInstall_url(String install_url) {
        this.install_url = install_url;
    }

    public void setDirect_install_url(String direct_install_url) {
        this.direct_install_url = direct_install_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }

    public void setBinary(BinaryEntity binary) {
        this.binary = binary;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getChangelog() {
        return changelog;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public String getVersionShort() {
        return versionShort;
    }

    public String getBuild() {
        return build;
    }

    public String getInstallUrl() {
        return installUrl;
    }

    public String getInstall_url() {
        return install_url;
    }

    public String getDirect_install_url() {
        return direct_install_url;
    }

    public String getUpdate_url() {
        return update_url;
    }

    public BinaryEntity getBinary() {
        return binary;
    }

    public static class BinaryEntity {
        private int fsize;

        public void setFsize(int fsize) {
            this.fsize = fsize;
        }

        public int getFsize() {
            return fsize;
        }
    }
}
