package com.xinxin.wotplus.model;

/**
 * 类型与国家 新版页面的动态接口返回类
 *
 * @author zhang.xx
 * @date 2016年10月15日13:55:56
 */

public class TypesAndCountryNewVO {

    /**
     * status : ok
     * data : {"results":{"vehicles_by_types":{"heavyTank":{"localized_name":"重型坦克","mastery_badges_count":17,"wins_count":7742,"vehicles_count":17,"battles_count":10566,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/heavyTank.png"},"ATSPG":{"localized_name":"自行反坦克炮","mastery_badges_count":20,"wins_count":6946,"vehicles_count":27,"battles_count":10151,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/AT-SPG.png"},"mediumTank":{"localized_name":"中型坦克","mastery_badges_count":24,"wins_count":21376,"vehicles_count":26,"battles_count":28702,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/mediumTank.png"},"lightTank":{"localized_name":"轻型坦克","mastery_badges_count":17,"wins_count":2298,"vehicles_count":19,"battles_count":3386,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/lightTank.png"},"SPG":{"localized_name":"自行火炮","mastery_badges_count":15,"wins_count":3392,"vehicles_count":18,"battles_count":5482,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/SPG.png"}},"vehicles_by_nations":{"china":{"localized_name":"C系","battles_count":2838},"usa":{"localized_name":"M系","battles_count":14632},"czech":{"localized_name":"J系","battles_count":2401},"france":{"localized_name":"F系","battles_count":8615},"ussr":{"localized_name":"S系","battles_count":13297},"germany":{"localized_name":"D系","battles_count":12802},"uk":{"localized_name":"Y系","battles_count":3667},"japan":{"localized_name":"R系","battles_count":35}}}}
     */

    private String status;
    /**
     * results : {"vehicles_by_types":{"heavyTank":{"localized_name":"重型坦克","mastery_badges_count":17,"wins_count":7742,"vehicles_count":17,"battles_count":10566,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/heavyTank.png"},"ATSPG":{"localized_name":"自行反坦克炮","mastery_badges_count":20,"wins_count":6946,"vehicles_count":27,"battles_count":10151,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/AT-SPG.png"},"mediumTank":{"localized_name":"中型坦克","mastery_badges_count":24,"wins_count":21376,"vehicles_count":26,"battles_count":28702,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/mediumTank.png"},"lightTank":{"localized_name":"轻型坦克","mastery_badges_count":17,"wins_count":2298,"vehicles_count":19,"battles_count":3386,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/lightTank.png"},"SPG":{"localized_name":"自行火炮","mastery_badges_count":15,"wins_count":3392,"vehicles_count":18,"battles_count":5482,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/SPG.png"}},"vehicles_by_nations":{"china":{"localized_name":"C系","battles_count":2838},"usa":{"localized_name":"M系","battles_count":14632},"czech":{"localized_name":"J系","battles_count":2401},"france":{"localized_name":"F系","battles_count":8615},"ussr":{"localized_name":"S系","battles_count":13297},"germany":{"localized_name":"D系","battles_count":12802},"uk":{"localized_name":"Y系","battles_count":3667},"japan":{"localized_name":"R系","battles_count":35}}}
     */

    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * vehicles_by_types : {"heavyTank":{"localized_name":"重型坦克","mastery_badges_count":17,"wins_count":7742,"vehicles_count":17,"battles_count":10566,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/heavyTank.png"},"ATSPG":{"localized_name":"自行反坦克炮","mastery_badges_count":20,"wins_count":6946,"vehicles_count":27,"battles_count":10151,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/AT-SPG.png"},"mediumTank":{"localized_name":"中型坦克","mastery_badges_count":24,"wins_count":21376,"vehicles_count":26,"battles_count":28702,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/mediumTank.png"},"lightTank":{"localized_name":"轻型坦克","mastery_badges_count":17,"wins_count":2298,"vehicles_count":19,"battles_count":3386,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/lightTank.png"},"SPG":{"localized_name":"自行火炮","mastery_badges_count":15,"wins_count":3392,"vehicles_count":18,"battles_count":5482,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/SPG.png"}}
         * vehicles_by_nations : {"china":{"localized_name":"C系","battles_count":2838},"usa":{"localized_name":"M系","battles_count":14632},"czech":{"localized_name":"J系","battles_count":2401},"france":{"localized_name":"F系","battles_count":8615},"ussr":{"localized_name":"S系","battles_count":13297},"germany":{"localized_name":"D系","battles_count":12802},"uk":{"localized_name":"Y系","battles_count":3667},"japan":{"localized_name":"R系","battles_count":35}}
         */

        private ResultsBean results;

        public ResultsBean getResults() {
            return results;
        }

        public void setResults(ResultsBean results) {
            this.results = results;
        }

        public static class ResultsBean {
            /**
             * heavyTank : {"localized_name":"重型坦克","mastery_badges_count":17,"wins_count":7742,"vehicles_count":17,"battles_count":10566,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/heavyTank.png"}
             * AT-SPG : {"localized_name":"自行反坦克炮","mastery_badges_count":20,"wins_count":6946,"vehicles_count":27,"battles_count":10151,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/AT-SPG.png"}
             * mediumTank : {"localized_name":"中型坦克","mastery_badges_count":24,"wins_count":21376,"vehicles_count":26,"battles_count":28702,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/mediumTank.png"}
             * lightTank : {"localized_name":"轻型坦克","mastery_badges_count":17,"wins_count":2298,"vehicles_count":19,"battles_count":3386,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/lightTank.png"}
             * SPG : {"localized_name":"自行火炮","mastery_badges_count":15,"wins_count":3392,"vehicles_count":18,"battles_count":5482,"icon":"//dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/SPG.png"}
             */

            private VehiclesByTypesBean vehicles_by_types;
            /**
             * china : {"localized_name":"C系","battles_count":2838}
             * usa : {"localized_name":"M系","battles_count":14632}
             * czech : {"localized_name":"J系","battles_count":2401}
             * france : {"localized_name":"F系","battles_count":8615}
             * ussr : {"localized_name":"S系","battles_count":13297}
             * germany : {"localized_name":"D系","battles_count":12802}
             * uk : {"localized_name":"Y系","battles_count":3667}
             * japan : {"localized_name":"R系","battles_count":35}
             */

            private VehiclesByNationsBean vehicles_by_nations;

            public VehiclesByTypesBean getVehicles_by_types() {
                return vehicles_by_types;
            }

            public void setVehicles_by_types(VehiclesByTypesBean vehicles_by_types) {
                this.vehicles_by_types = vehicles_by_types;
            }

            public VehiclesByNationsBean getVehicles_by_nations() {
                return vehicles_by_nations;
            }

            public void setVehicles_by_nations(VehiclesByNationsBean vehicles_by_nations) {
                this.vehicles_by_nations = vehicles_by_nations;
            }

            public static class VehiclesByTypesBean {
                /**
                 * localized_name : 重型坦克
                 * mastery_badges_count : 17
                 * wins_count : 7742
                 * vehicles_count : 17
                 * battles_count : 10566
                 * icon : //dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/heavyTank.png
                 */

                private HeavyTankBean heavyTank;
                /**
                 * localized_name : 自行反坦克炮
                 * mastery_badges_count : 20
                 * wins_count : 6946
                 * vehicles_count : 27
                 * battles_count : 10151
                 * icon : //dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/AT-SPG.png
                 */
                private ATSPGBean ATSPG;
                /**
                 * localized_name : 中型坦克
                 * mastery_badges_count : 24
                 * wins_count : 21376
                 * vehicles_count : 26
                 * battles_count : 28702
                 * icon : //dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/mediumTank.png
                 */

                private MediumTankBean mediumTank;
                /**
                 * localized_name : 轻型坦克
                 * mastery_badges_count : 17
                 * wins_count : 2298
                 * vehicles_count : 19
                 * battles_count : 3386
                 * icon : //dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/lightTank.png
                 */

                private LightTankBean lightTank;
                /**
                 * localized_name : 自行火炮
                 * mastery_badges_count : 15
                 * wins_count : 3392
                 * vehicles_count : 18
                 * battles_count : 5482
                 * icon : //dav-static-cnn.worldoftanks.cn//wotpcnn/wot/current/vehicleTypes/big/SPG.png
                 */

                private SPGBean SPG;

                public HeavyTankBean getHeavyTank() {
                    return heavyTank;
                }

                public void setHeavyTank(HeavyTankBean heavyTank) {
                    this.heavyTank = heavyTank;
                }

                public ATSPGBean getATSPG() {
                    return ATSPG;
                }

                public void setATSPG(ATSPGBean ATSPG) {
                    this.ATSPG = ATSPG;
                }

                public MediumTankBean getMediumTank() {
                    return mediumTank;
                }

                public void setMediumTank(MediumTankBean mediumTank) {
                    this.mediumTank = mediumTank;
                }

                public LightTankBean getLightTank() {
                    return lightTank;
                }

                public void setLightTank(LightTankBean lightTank) {
                    this.lightTank = lightTank;
                }

                public SPGBean getSPG() {
                    return SPG;
                }

                public void setSPG(SPGBean SPG) {
                    this.SPG = SPG;
                }

                public static class HeavyTankBean {
                    private String localized_name;
                    private int mastery_badges_count;
                    private int wins_count;
                    private int vehicles_count;
                    private int battles_count;
                    private String icon;

                    public String getLocalized_name() {
                        return localized_name;
                    }

                    public void setLocalized_name(String localized_name) {
                        this.localized_name = localized_name;
                    }

                    public int getMastery_badges_count() {
                        return mastery_badges_count;
                    }

                    public void setMastery_badges_count(int mastery_badges_count) {
                        this.mastery_badges_count = mastery_badges_count;
                    }

                    public int getWins_count() {
                        return wins_count;
                    }

                    public void setWins_count(int wins_count) {
                        this.wins_count = wins_count;
                    }

                    public int getVehicles_count() {
                        return vehicles_count;
                    }

                    public void setVehicles_count(int vehicles_count) {
                        this.vehicles_count = vehicles_count;
                    }

                    public int getBattles_count() {
                        return battles_count;
                    }

                    public void setBattles_count(int battles_count) {
                        this.battles_count = battles_count;
                    }

                    public String getIcon() {
                        return icon;
                    }

                    public void setIcon(String icon) {
                        this.icon = icon;
                    }
                }

                public static class ATSPGBean {
                    private String localized_name;
                    private int mastery_badges_count;
                    private int wins_count;
                    private int vehicles_count;
                    private int battles_count;
                    private String icon;

                    public String getLocalized_name() {
                        return localized_name;
                    }

                    public void setLocalized_name(String localized_name) {
                        this.localized_name = localized_name;
                    }

                    public int getMastery_badges_count() {
                        return mastery_badges_count;
                    }

                    public void setMastery_badges_count(int mastery_badges_count) {
                        this.mastery_badges_count = mastery_badges_count;
                    }

                    public int getWins_count() {
                        return wins_count;
                    }

                    public void setWins_count(int wins_count) {
                        this.wins_count = wins_count;
                    }

                    public int getVehicles_count() {
                        return vehicles_count;
                    }

                    public void setVehicles_count(int vehicles_count) {
                        this.vehicles_count = vehicles_count;
                    }

                    public int getBattles_count() {
                        return battles_count;
                    }

                    public void setBattles_count(int battles_count) {
                        this.battles_count = battles_count;
                    }

                    public String getIcon() {
                        return icon;
                    }

                    public void setIcon(String icon) {
                        this.icon = icon;
                    }
                }

                public static class MediumTankBean {
                    private String localized_name;
                    private int mastery_badges_count;
                    private int wins_count;
                    private int vehicles_count;
                    private int battles_count;
                    private String icon;

                    public String getLocalized_name() {
                        return localized_name;
                    }

                    public void setLocalized_name(String localized_name) {
                        this.localized_name = localized_name;
                    }

                    public int getMastery_badges_count() {
                        return mastery_badges_count;
                    }

                    public void setMastery_badges_count(int mastery_badges_count) {
                        this.mastery_badges_count = mastery_badges_count;
                    }

                    public int getWins_count() {
                        return wins_count;
                    }

                    public void setWins_count(int wins_count) {
                        this.wins_count = wins_count;
                    }

                    public int getVehicles_count() {
                        return vehicles_count;
                    }

                    public void setVehicles_count(int vehicles_count) {
                        this.vehicles_count = vehicles_count;
                    }

                    public int getBattles_count() {
                        return battles_count;
                    }

                    public void setBattles_count(int battles_count) {
                        this.battles_count = battles_count;
                    }

                    public String getIcon() {
                        return icon;
                    }

                    public void setIcon(String icon) {
                        this.icon = icon;
                    }
                }

                public static class LightTankBean {
                    private String localized_name;
                    private int mastery_badges_count;
                    private int wins_count;
                    private int vehicles_count;
                    private int battles_count;
                    private String icon;

                    public String getLocalized_name() {
                        return localized_name;
                    }

                    public void setLocalized_name(String localized_name) {
                        this.localized_name = localized_name;
                    }

                    public int getMastery_badges_count() {
                        return mastery_badges_count;
                    }

                    public void setMastery_badges_count(int mastery_badges_count) {
                        this.mastery_badges_count = mastery_badges_count;
                    }

                    public int getWins_count() {
                        return wins_count;
                    }

                    public void setWins_count(int wins_count) {
                        this.wins_count = wins_count;
                    }

                    public int getVehicles_count() {
                        return vehicles_count;
                    }

                    public void setVehicles_count(int vehicles_count) {
                        this.vehicles_count = vehicles_count;
                    }

                    public int getBattles_count() {
                        return battles_count;
                    }

                    public void setBattles_count(int battles_count) {
                        this.battles_count = battles_count;
                    }

                    public String getIcon() {
                        return icon;
                    }

                    public void setIcon(String icon) {
                        this.icon = icon;
                    }
                }

                public static class SPGBean {
                    private String localized_name;
                    private int mastery_badges_count;
                    private int wins_count;
                    private int vehicles_count;
                    private int battles_count;
                    private String icon;

                    public String getLocalized_name() {
                        return localized_name;
                    }

                    public void setLocalized_name(String localized_name) {
                        this.localized_name = localized_name;
                    }

                    public int getMastery_badges_count() {
                        return mastery_badges_count;
                    }

                    public void setMastery_badges_count(int mastery_badges_count) {
                        this.mastery_badges_count = mastery_badges_count;
                    }

                    public int getWins_count() {
                        return wins_count;
                    }

                    public void setWins_count(int wins_count) {
                        this.wins_count = wins_count;
                    }

                    public int getVehicles_count() {
                        return vehicles_count;
                    }

                    public void setVehicles_count(int vehicles_count) {
                        this.vehicles_count = vehicles_count;
                    }

                    public int getBattles_count() {
                        return battles_count;
                    }

                    public void setBattles_count(int battles_count) {
                        this.battles_count = battles_count;
                    }

                    public String getIcon() {
                        return icon;
                    }

                    public void setIcon(String icon) {
                        this.icon = icon;
                    }
                }
            }

            public static class VehiclesByNationsBean {
                /**
                 * localized_name : C系
                 * battles_count : 2838
                 */

                private ChinaBean china;
                /**
                 * localized_name : M系
                 * battles_count : 14632
                 */

                private UsaBean usa;
                /**
                 * localized_name : J系
                 * battles_count : 2401
                 */

                private CzechBean czech;
                /**
                 * localized_name : F系
                 * battles_count : 8615
                 */

                private FranceBean france;
                /**
                 * localized_name : S系
                 * battles_count : 13297
                 */

                private UssrBean ussr;
                /**
                 * localized_name : D系
                 * battles_count : 12802
                 */

                private GermanyBean germany;
                /**
                 * localized_name : Y系
                 * battles_count : 3667
                 */

                private UkBean uk;
                /**
                 * localized_name : R系
                 * battles_count : 35
                 */

                private JapanBean japan;

                public ChinaBean getChina() {
                    return china;
                }

                public void setChina(ChinaBean china) {
                    this.china = china;
                }

                public UsaBean getUsa() {
                    return usa;
                }

                public void setUsa(UsaBean usa) {
                    this.usa = usa;
                }

                public CzechBean getCzech() {
                    return czech;
                }

                public void setCzech(CzechBean czech) {
                    this.czech = czech;
                }

                public FranceBean getFrance() {
                    return france;
                }

                public void setFrance(FranceBean france) {
                    this.france = france;
                }

                public UssrBean getUssr() {
                    return ussr;
                }

                public void setUssr(UssrBean ussr) {
                    this.ussr = ussr;
                }

                public GermanyBean getGermany() {
                    return germany;
                }

                public void setGermany(GermanyBean germany) {
                    this.germany = germany;
                }

                public UkBean getUk() {
                    return uk;
                }

                public void setUk(UkBean uk) {
                    this.uk = uk;
                }

                public JapanBean getJapan() {
                    return japan;
                }

                public void setJapan(JapanBean japan) {
                    this.japan = japan;
                }

                public static class ChinaBean {
                    private String localized_name;
                    private int battles_count;

                    public String getLocalized_name() {
                        return localized_name;
                    }

                    public void setLocalized_name(String localized_name) {
                        this.localized_name = localized_name;
                    }

                    public int getBattles_count() {
                        return battles_count;
                    }

                    public void setBattles_count(int battles_count) {
                        this.battles_count = battles_count;
                    }
                }

                public static class UsaBean {
                    private String localized_name;
                    private int battles_count;

                    public String getLocalized_name() {
                        return localized_name;
                    }

                    public void setLocalized_name(String localized_name) {
                        this.localized_name = localized_name;
                    }

                    public int getBattles_count() {
                        return battles_count;
                    }

                    public void setBattles_count(int battles_count) {
                        this.battles_count = battles_count;
                    }
                }

                public static class CzechBean {
                    private String localized_name;
                    private int battles_count;

                    public String getLocalized_name() {
                        return localized_name;
                    }

                    public void setLocalized_name(String localized_name) {
                        this.localized_name = localized_name;
                    }

                    public int getBattles_count() {
                        return battles_count;
                    }

                    public void setBattles_count(int battles_count) {
                        this.battles_count = battles_count;
                    }
                }

                public static class FranceBean {
                    private String localized_name;
                    private int battles_count;

                    public String getLocalized_name() {
                        return localized_name;
                    }

                    public void setLocalized_name(String localized_name) {
                        this.localized_name = localized_name;
                    }

                    public int getBattles_count() {
                        return battles_count;
                    }

                    public void setBattles_count(int battles_count) {
                        this.battles_count = battles_count;
                    }
                }

                public static class UssrBean {
                    private String localized_name;
                    private int battles_count;

                    public String getLocalized_name() {
                        return localized_name;
                    }

                    public void setLocalized_name(String localized_name) {
                        this.localized_name = localized_name;
                    }

                    public int getBattles_count() {
                        return battles_count;
                    }

                    public void setBattles_count(int battles_count) {
                        this.battles_count = battles_count;
                    }
                }

                public static class GermanyBean {
                    private String localized_name;
                    private int battles_count;

                    public String getLocalized_name() {
                        return localized_name;
                    }

                    public void setLocalized_name(String localized_name) {
                        this.localized_name = localized_name;
                    }

                    public int getBattles_count() {
                        return battles_count;
                    }

                    public void setBattles_count(int battles_count) {
                        this.battles_count = battles_count;
                    }
                }

                public static class UkBean {
                    private String localized_name;
                    private int battles_count;

                    public String getLocalized_name() {
                        return localized_name;
                    }

                    public void setLocalized_name(String localized_name) {
                        this.localized_name = localized_name;
                    }

                    public int getBattles_count() {
                        return battles_count;
                    }

                    public void setBattles_count(int battles_count) {
                        this.battles_count = battles_count;
                    }
                }

                public static class JapanBean {
                    private String localized_name;
                    private int battles_count;

                    public String getLocalized_name() {
                        return localized_name;
                    }

                    public void setLocalized_name(String localized_name) {
                        this.localized_name = localized_name;
                    }

                    public int getBattles_count() {
                        return battles_count;
                    }

                    public void setBattles_count(int battles_count) {
                        this.battles_count = battles_count;
                    }
                }
            }
        }
    }
}
