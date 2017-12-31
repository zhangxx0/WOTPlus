package com.xinxin.wotplus.model.kongzhong;

import java.io.Serializable;

/**
 * Created by xinxin on 2018/1/1.
 */

public class Statistics implements Serializable {


    /**
     * status : ok
     * data : {"tiers":{"tier3":{"battles_count":488,"wins_count":268,"master_count":21,"battles_count_percent":1.74,"wins_count_percent":54.92},"tier8":{"battles_count":4214,"wins_count":2311,"master_count":28,"battles_count_percent":15.06,"wins_count_percent":54.84},"tier2":{"battles_count":943,"wins_count":576,"master_count":23,"battles_count_percent":3.37,"wins_count_percent":61.08},"tier5":{"battles_count":4289,"wins_count":2381,"master_count":27,"battles_count_percent":15.33,"wins_count_percent":55.51},"tier10":{"battles_count":4155,"wins_count":2179,"master_count":21,"battles_count_percent":14.85,"wins_count_percent":52.44},"tier9":{"battles_count":5499,"wins_count":2990,"master_count":22,"battles_count_percent":19.65,"wins_count_percent":54.37},"tier7":{"battles_count":4122,"wins_count":2193,"master_count":29,"battles_count_percent":14.73,"wins_count_percent":53.2},"tier6":{"battles_count":3221,"wins_count":1786,"master_count":25,"battles_count_percent":11.51,"wins_count_percent":55.45},"tier4":{"battles_count":885,"wins_count":478,"master_count":21,"battles_count_percent":3.16,"wins_count_percent":54.01},"tier1":{"battles_count":167,"wins_count":107,"master_count":9,"battles_count_percent":0.6,"wins_count_percent":64.07}},"nations":{"ussr":{"battles_count":3546,"wins_count":2040,"master_count":37,"battles_count_percent":12.67,"wins_count_percent":57.53},"usa":{"battles_count":4800,"wins_count":2721,"master_count":45,"battles_count_percent":17.15,"wins_count_percent":56.69},"germany":{"battles_count":12879,"wins_count":6779,"master_count":69,"battles_count_percent":46.03,"wins_count_percent":52.64},"china":{"battles_count":3457,"wins_count":1930,"master_count":23,"battles_count_percent":12.35,"wins_count_percent":55.83},"uk":{"battles_count":930,"wins_count":522,"master_count":26,"battles_count_percent":3.32,"wins_count_percent":56.13},"france":{"battles_count":1737,"wins_count":916,"master_count":12,"battles_count_percent":6.21,"wins_count_percent":52.73},"japan":{"battles_count":1,"wins_count":1,"master_count":1,"battles_count_percent":0,"wins_count_percent":100},"czech":{"battles_count":105,"wins_count":65,"master_count":4,"battles_count_percent":0.38,"wins_count_percent":61.9},"sweden":{"battles_count":528,"wins_count":295,"master_count":9,"battles_count_percent":1.89,"wins_count_percent":55.87}},"types":{"lightTank":{"battles_count":5633,"wins_count":3090,"master_count":62,"battles_count_percent":20.13,"wins_count_percent":54.86},"heavyTank":{"battles_count":7900,"wins_count":4312,"master_count":32,"battles_count_percent":28.23,"wins_count_percent":54.58},"mediumTank":{"battles_count":6957,"wins_count":3822,"master_count":60,"battles_count_percent":24.86,"wins_count_percent":54.94},"ATSPG":{"battles_count":4334,"wins_count":2409,"master_count":50,"battles_count_percent":15.49,"wins_count_percent":55.58},"SPG":{"battles_count":3159,"wins_count":1636,"master_count":22,"battles_count_percent":11.29,"wins_count_percent":51.79}},"master_levels":{"master2":26,"master4":128,"master3":56,"master1":16},"battles_count":27983,"wins_count":15269,"losses_count":12205,"survived_battles":10643,"capture_points":49230,"dropped_capture_points":32253,"frags_max":11,"xp_max":4567,"damage_max":8226,"damage_assisted_avg":285,"wins_count_percent":54.56,"losses_count_percent":43.62,"survived_battles_percent":38.03,"xp_amount_avg":637,"damage_dealt_avg":1299,"damage_received_avg":874,"spotted_count_avg":1.25,"frags_count_avg":1.15,"stun_num_avg":7,"damage_assisted_stun_avg":507,"frags_count_coefficient":1.85,"damage_coefficient":1.49,"damage_blocked_coefficient":0.42,"master_level_counts":{"master2":26,"master4":128,"master3":56,"master1":16}}
     */

    private String status;
    /**
     * tiers : {"tier3":{"battles_count":488,"wins_count":268,"master_count":21,"battles_count_percent":1.74,"wins_count_percent":54.92},"tier8":{"battles_count":4214,"wins_count":2311,"master_count":28,"battles_count_percent":15.06,"wins_count_percent":54.84},"tier2":{"battles_count":943,"wins_count":576,"master_count":23,"battles_count_percent":3.37,"wins_count_percent":61.08},"tier5":{"battles_count":4289,"wins_count":2381,"master_count":27,"battles_count_percent":15.33,"wins_count_percent":55.51},"tier10":{"battles_count":4155,"wins_count":2179,"master_count":21,"battles_count_percent":14.85,"wins_count_percent":52.44},"tier9":{"battles_count":5499,"wins_count":2990,"master_count":22,"battles_count_percent":19.65,"wins_count_percent":54.37},"tier7":{"battles_count":4122,"wins_count":2193,"master_count":29,"battles_count_percent":14.73,"wins_count_percent":53.2},"tier6":{"battles_count":3221,"wins_count":1786,"master_count":25,"battles_count_percent":11.51,"wins_count_percent":55.45},"tier4":{"battles_count":885,"wins_count":478,"master_count":21,"battles_count_percent":3.16,"wins_count_percent":54.01},"tier1":{"battles_count":167,"wins_count":107,"master_count":9,"battles_count_percent":0.6,"wins_count_percent":64.07}}
     * nations : {"ussr":{"battles_count":3546,"wins_count":2040,"master_count":37,"battles_count_percent":12.67,"wins_count_percent":57.53},"usa":{"battles_count":4800,"wins_count":2721,"master_count":45,"battles_count_percent":17.15,"wins_count_percent":56.69},"germany":{"battles_count":12879,"wins_count":6779,"master_count":69,"battles_count_percent":46.03,"wins_count_percent":52.64},"china":{"battles_count":3457,"wins_count":1930,"master_count":23,"battles_count_percent":12.35,"wins_count_percent":55.83},"uk":{"battles_count":930,"wins_count":522,"master_count":26,"battles_count_percent":3.32,"wins_count_percent":56.13},"france":{"battles_count":1737,"wins_count":916,"master_count":12,"battles_count_percent":6.21,"wins_count_percent":52.73},"japan":{"battles_count":1,"wins_count":1,"master_count":1,"battles_count_percent":0,"wins_count_percent":100},"czech":{"battles_count":105,"wins_count":65,"master_count":4,"battles_count_percent":0.38,"wins_count_percent":61.9},"sweden":{"battles_count":528,"wins_count":295,"master_count":9,"battles_count_percent":1.89,"wins_count_percent":55.87}}
     * types : {"lightTank":{"battles_count":5633,"wins_count":3090,"master_count":62,"battles_count_percent":20.13,"wins_count_percent":54.86},"heavyTank":{"battles_count":7900,"wins_count":4312,"master_count":32,"battles_count_percent":28.23,"wins_count_percent":54.58},"mediumTank":{"battles_count":6957,"wins_count":3822,"master_count":60,"battles_count_percent":24.86,"wins_count_percent":54.94},"ATSPG":{"battles_count":4334,"wins_count":2409,"master_count":50,"battles_count_percent":15.49,"wins_count_percent":55.58},"SPG":{"battles_count":3159,"wins_count":1636,"master_count":22,"battles_count_percent":11.29,"wins_count_percent":51.79}}
     * master_levels : {"master2":26,"master4":128,"master3":56,"master1":16}
     * battles_count : 27983
     * wins_count : 15269
     * losses_count : 12205
     * survived_battles : 10643
     * capture_points : 49230
     * dropped_capture_points : 32253
     * frags_max : 11
     * xp_max : 4567
     * damage_max : 8226
     * damage_assisted_avg : 285
     * wins_count_percent : 54.56
     * losses_count_percent : 43.62
     * survived_battles_percent : 38.03
     * xp_amount_avg : 637
     * damage_dealt_avg : 1299
     * damage_received_avg : 874
     * spotted_count_avg : 1.25
     * frags_count_avg : 1.15
     * stun_num_avg : 7
     * damage_assisted_stun_avg : 507
     * frags_count_coefficient : 1.85
     * damage_coefficient : 1.49
     * damage_blocked_coefficient : 0.42
     * master_level_counts : {"master2":26,"master4":128,"master3":56,"master1":16}
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
         * tier3 : {"battles_count":488,"wins_count":268,"master_count":21,"battles_count_percent":1.74,"wins_count_percent":54.92}
         * tier8 : {"battles_count":4214,"wins_count":2311,"master_count":28,"battles_count_percent":15.06,"wins_count_percent":54.84}
         * tier2 : {"battles_count":943,"wins_count":576,"master_count":23,"battles_count_percent":3.37,"wins_count_percent":61.08}
         * tier5 : {"battles_count":4289,"wins_count":2381,"master_count":27,"battles_count_percent":15.33,"wins_count_percent":55.51}
         * tier10 : {"battles_count":4155,"wins_count":2179,"master_count":21,"battles_count_percent":14.85,"wins_count_percent":52.44}
         * tier9 : {"battles_count":5499,"wins_count":2990,"master_count":22,"battles_count_percent":19.65,"wins_count_percent":54.37}
         * tier7 : {"battles_count":4122,"wins_count":2193,"master_count":29,"battles_count_percent":14.73,"wins_count_percent":53.2}
         * tier6 : {"battles_count":3221,"wins_count":1786,"master_count":25,"battles_count_percent":11.51,"wins_count_percent":55.45}
         * tier4 : {"battles_count":885,"wins_count":478,"master_count":21,"battles_count_percent":3.16,"wins_count_percent":54.01}
         * tier1 : {"battles_count":167,"wins_count":107,"master_count":9,"battles_count_percent":0.6,"wins_count_percent":64.07}
         */

        private TiersBean tiers;
        /**
         * ussr : {"battles_count":3546,"wins_count":2040,"master_count":37,"battles_count_percent":12.67,"wins_count_percent":57.53}
         * usa : {"battles_count":4800,"wins_count":2721,"master_count":45,"battles_count_percent":17.15,"wins_count_percent":56.69}
         * germany : {"battles_count":12879,"wins_count":6779,"master_count":69,"battles_count_percent":46.03,"wins_count_percent":52.64}
         * china : {"battles_count":3457,"wins_count":1930,"master_count":23,"battles_count_percent":12.35,"wins_count_percent":55.83}
         * uk : {"battles_count":930,"wins_count":522,"master_count":26,"battles_count_percent":3.32,"wins_count_percent":56.13}
         * france : {"battles_count":1737,"wins_count":916,"master_count":12,"battles_count_percent":6.21,"wins_count_percent":52.73}
         * japan : {"battles_count":1,"wins_count":1,"master_count":1,"battles_count_percent":0,"wins_count_percent":100}
         * czech : {"battles_count":105,"wins_count":65,"master_count":4,"battles_count_percent":0.38,"wins_count_percent":61.9}
         * sweden : {"battles_count":528,"wins_count":295,"master_count":9,"battles_count_percent":1.89,"wins_count_percent":55.87}
         */

        private NationsBean nations;
        /**
         * lightTank : {"battles_count":5633,"wins_count":3090,"master_count":62,"battles_count_percent":20.13,"wins_count_percent":54.86}
         * heavyTank : {"battles_count":7900,"wins_count":4312,"master_count":32,"battles_count_percent":28.23,"wins_count_percent":54.58}
         * mediumTank : {"battles_count":6957,"wins_count":3822,"master_count":60,"battles_count_percent":24.86,"wins_count_percent":54.94}
         * ATSPG : {"battles_count":4334,"wins_count":2409,"master_count":50,"battles_count_percent":15.49,"wins_count_percent":55.58}
         * SPG : {"battles_count":3159,"wins_count":1636,"master_count":22,"battles_count_percent":11.29,"wins_count_percent":51.79}
         */

        private TypesBean types;
        /**
         * master2 : 26
         * master4 : 128
         * master3 : 56
         * master1 : 16
         */

        private MasterLevelsBean master_levels;
        private int battles_count;
        private int wins_count;
        private int losses_count;
        private int survived_battles;
        private int capture_points;
        private int dropped_capture_points;
        private int frags_max;
        private int xp_max;
        private int damage_max;
        private int damage_assisted_avg;
        private double wins_count_percent;
        private double losses_count_percent;
        private double survived_battles_percent;
        private int xp_amount_avg;
        private int damage_dealt_avg;
        private int damage_received_avg;
        private double spotted_count_avg;
        private double frags_count_avg;
        private int stun_num_avg;
        private int damage_assisted_stun_avg;
        private double frags_count_coefficient;
        private double damage_coefficient;
        private double damage_blocked_coefficient;
        /**
         * master2 : 26
         * master4 : 128
         * master3 : 56
         * master1 : 16
         */

        private MasterLevelCountsBean master_level_counts;

        public TiersBean getTiers() {
            return tiers;
        }

        public void setTiers(TiersBean tiers) {
            this.tiers = tiers;
        }

        public NationsBean getNations() {
            return nations;
        }

        public void setNations(NationsBean nations) {
            this.nations = nations;
        }

        public TypesBean getTypes() {
            return types;
        }

        public void setTypes(TypesBean types) {
            this.types = types;
        }

        public MasterLevelsBean getMaster_levels() {
            return master_levels;
        }

        public void setMaster_levels(MasterLevelsBean master_levels) {
            this.master_levels = master_levels;
        }

        public int getBattles_count() {
            return battles_count;
        }

        public void setBattles_count(int battles_count) {
            this.battles_count = battles_count;
        }

        public int getWins_count() {
            return wins_count;
        }

        public void setWins_count(int wins_count) {
            this.wins_count = wins_count;
        }

        public int getLosses_count() {
            return losses_count;
        }

        public void setLosses_count(int losses_count) {
            this.losses_count = losses_count;
        }

        public int getSurvived_battles() {
            return survived_battles;
        }

        public void setSurvived_battles(int survived_battles) {
            this.survived_battles = survived_battles;
        }

        public int getCapture_points() {
            return capture_points;
        }

        public void setCapture_points(int capture_points) {
            this.capture_points = capture_points;
        }

        public int getDropped_capture_points() {
            return dropped_capture_points;
        }

        public void setDropped_capture_points(int dropped_capture_points) {
            this.dropped_capture_points = dropped_capture_points;
        }

        public int getFrags_max() {
            return frags_max;
        }

        public void setFrags_max(int frags_max) {
            this.frags_max = frags_max;
        }

        public int getXp_max() {
            return xp_max;
        }

        public void setXp_max(int xp_max) {
            this.xp_max = xp_max;
        }

        public int getDamage_max() {
            return damage_max;
        }

        public void setDamage_max(int damage_max) {
            this.damage_max = damage_max;
        }

        public int getDamage_assisted_avg() {
            return damage_assisted_avg;
        }

        public void setDamage_assisted_avg(int damage_assisted_avg) {
            this.damage_assisted_avg = damage_assisted_avg;
        }

        public double getWins_count_percent() {
            return wins_count_percent;
        }

        public void setWins_count_percent(double wins_count_percent) {
            this.wins_count_percent = wins_count_percent;
        }

        public double getLosses_count_percent() {
            return losses_count_percent;
        }

        public void setLosses_count_percent(double losses_count_percent) {
            this.losses_count_percent = losses_count_percent;
        }

        public double getSurvived_battles_percent() {
            return survived_battles_percent;
        }

        public void setSurvived_battles_percent(double survived_battles_percent) {
            this.survived_battles_percent = survived_battles_percent;
        }

        public int getXp_amount_avg() {
            return xp_amount_avg;
        }

        public void setXp_amount_avg(int xp_amount_avg) {
            this.xp_amount_avg = xp_amount_avg;
        }

        public int getDamage_dealt_avg() {
            return damage_dealt_avg;
        }

        public void setDamage_dealt_avg(int damage_dealt_avg) {
            this.damage_dealt_avg = damage_dealt_avg;
        }

        public int getDamage_received_avg() {
            return damage_received_avg;
        }

        public void setDamage_received_avg(int damage_received_avg) {
            this.damage_received_avg = damage_received_avg;
        }

        public double getSpotted_count_avg() {
            return spotted_count_avg;
        }

        public void setSpotted_count_avg(double spotted_count_avg) {
            this.spotted_count_avg = spotted_count_avg;
        }

        public double getFrags_count_avg() {
            return frags_count_avg;
        }

        public void setFrags_count_avg(double frags_count_avg) {
            this.frags_count_avg = frags_count_avg;
        }

        public int getStun_num_avg() {
            return stun_num_avg;
        }

        public void setStun_num_avg(int stun_num_avg) {
            this.stun_num_avg = stun_num_avg;
        }

        public int getDamage_assisted_stun_avg() {
            return damage_assisted_stun_avg;
        }

        public void setDamage_assisted_stun_avg(int damage_assisted_stun_avg) {
            this.damage_assisted_stun_avg = damage_assisted_stun_avg;
        }

        public double getFrags_count_coefficient() {
            return frags_count_coefficient;
        }

        public void setFrags_count_coefficient(double frags_count_coefficient) {
            this.frags_count_coefficient = frags_count_coefficient;
        }

        public double getDamage_coefficient() {
            return damage_coefficient;
        }

        public void setDamage_coefficient(double damage_coefficient) {
            this.damage_coefficient = damage_coefficient;
        }

        public double getDamage_blocked_coefficient() {
            return damage_blocked_coefficient;
        }

        public void setDamage_blocked_coefficient(double damage_blocked_coefficient) {
            this.damage_blocked_coefficient = damage_blocked_coefficient;
        }

        public MasterLevelCountsBean getMaster_level_counts() {
            return master_level_counts;
        }

        public void setMaster_level_counts(MasterLevelCountsBean master_level_counts) {
            this.master_level_counts = master_level_counts;
        }

        public static class TiersBean {
            /**
             * battles_count : 488
             * wins_count : 268
             * master_count : 21
             * battles_count_percent : 1.74
             * wins_count_percent : 54.92
             */

            private Tier3Bean tier3;
            /**
             * battles_count : 4214
             * wins_count : 2311
             * master_count : 28
             * battles_count_percent : 15.06
             * wins_count_percent : 54.84
             */

            private Tier8Bean tier8;
            /**
             * battles_count : 943
             * wins_count : 576
             * master_count : 23
             * battles_count_percent : 3.37
             * wins_count_percent : 61.08
             */

            private Tier2Bean tier2;
            /**
             * battles_count : 4289
             * wins_count : 2381
             * master_count : 27
             * battles_count_percent : 15.33
             * wins_count_percent : 55.51
             */

            private Tier5Bean tier5;
            /**
             * battles_count : 4155
             * wins_count : 2179
             * master_count : 21
             * battles_count_percent : 14.85
             * wins_count_percent : 52.44
             */

            private Tier10Bean tier10;
            /**
             * battles_count : 5499
             * wins_count : 2990
             * master_count : 22
             * battles_count_percent : 19.65
             * wins_count_percent : 54.37
             */

            private Tier9Bean tier9;
            /**
             * battles_count : 4122
             * wins_count : 2193
             * master_count : 29
             * battles_count_percent : 14.73
             * wins_count_percent : 53.2
             */

            private Tier7Bean tier7;
            /**
             * battles_count : 3221
             * wins_count : 1786
             * master_count : 25
             * battles_count_percent : 11.51
             * wins_count_percent : 55.45
             */

            private Tier6Bean tier6;
            /**
             * battles_count : 885
             * wins_count : 478
             * master_count : 21
             * battles_count_percent : 3.16
             * wins_count_percent : 54.01
             */

            private Tier4Bean tier4;
            /**
             * battles_count : 167
             * wins_count : 107
             * master_count : 9
             * battles_count_percent : 0.6
             * wins_count_percent : 64.07
             */

            private Tier1Bean tier1;

            public Tier3Bean getTier3() {
                return tier3;
            }

            public void setTier3(Tier3Bean tier3) {
                this.tier3 = tier3;
            }

            public Tier8Bean getTier8() {
                return tier8;
            }

            public void setTier8(Tier8Bean tier8) {
                this.tier8 = tier8;
            }

            public Tier2Bean getTier2() {
                return tier2;
            }

            public void setTier2(Tier2Bean tier2) {
                this.tier2 = tier2;
            }

            public Tier5Bean getTier5() {
                return tier5;
            }

            public void setTier5(Tier5Bean tier5) {
                this.tier5 = tier5;
            }

            public Tier10Bean getTier10() {
                return tier10;
            }

            public void setTier10(Tier10Bean tier10) {
                this.tier10 = tier10;
            }

            public Tier9Bean getTier9() {
                return tier9;
            }

            public void setTier9(Tier9Bean tier9) {
                this.tier9 = tier9;
            }

            public Tier7Bean getTier7() {
                return tier7;
            }

            public void setTier7(Tier7Bean tier7) {
                this.tier7 = tier7;
            }

            public Tier6Bean getTier6() {
                return tier6;
            }

            public void setTier6(Tier6Bean tier6) {
                this.tier6 = tier6;
            }

            public Tier4Bean getTier4() {
                return tier4;
            }

            public void setTier4(Tier4Bean tier4) {
                this.tier4 = tier4;
            }

            public Tier1Bean getTier1() {
                return tier1;
            }

            public void setTier1(Tier1Bean tier1) {
                this.tier1 = tier1;
            }

            public static class Tier3Bean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class Tier8Bean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class Tier2Bean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class Tier5Bean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class Tier10Bean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class Tier9Bean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class Tier7Bean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class Tier6Bean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class Tier4Bean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class Tier1Bean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }
        }

        public static class NationsBean {
            /**
             * battles_count : 3546
             * wins_count : 2040
             * master_count : 37
             * battles_count_percent : 12.67
             * wins_count_percent : 57.53
             */

            private UssrBean ussr;
            /**
             * battles_count : 4800
             * wins_count : 2721
             * master_count : 45
             * battles_count_percent : 17.15
             * wins_count_percent : 56.69
             */

            private UsaBean usa;
            /**
             * battles_count : 12879
             * wins_count : 6779
             * master_count : 69
             * battles_count_percent : 46.03
             * wins_count_percent : 52.64
             */

            private GermanyBean germany;
            /**
             * battles_count : 3457
             * wins_count : 1930
             * master_count : 23
             * battles_count_percent : 12.35
             * wins_count_percent : 55.83
             */

            private ChinaBean china;
            /**
             * battles_count : 930
             * wins_count : 522
             * master_count : 26
             * battles_count_percent : 3.32
             * wins_count_percent : 56.13
             */

            private UkBean uk;
            /**
             * battles_count : 1737
             * wins_count : 916
             * master_count : 12
             * battles_count_percent : 6.21
             * wins_count_percent : 52.73
             */

            private FranceBean france;
            /**
             * battles_count : 1
             * wins_count : 1
             * master_count : 1
             * battles_count_percent : 0
             * wins_count_percent : 100
             */

            private JapanBean japan;
            /**
             * battles_count : 105
             * wins_count : 65
             * master_count : 4
             * battles_count_percent : 0.38
             * wins_count_percent : 61.9
             */

            private CzechBean czech;
            /**
             * battles_count : 528
             * wins_count : 295
             * master_count : 9
             * battles_count_percent : 1.89
             * wins_count_percent : 55.87
             */

            private SwedenBean sweden;

            public UssrBean getUssr() {
                return ussr;
            }

            public void setUssr(UssrBean ussr) {
                this.ussr = ussr;
            }

            public UsaBean getUsa() {
                return usa;
            }

            public void setUsa(UsaBean usa) {
                this.usa = usa;
            }

            public GermanyBean getGermany() {
                return germany;
            }

            public void setGermany(GermanyBean germany) {
                this.germany = germany;
            }

            public ChinaBean getChina() {
                return china;
            }

            public void setChina(ChinaBean china) {
                this.china = china;
            }

            public UkBean getUk() {
                return uk;
            }

            public void setUk(UkBean uk) {
                this.uk = uk;
            }

            public FranceBean getFrance() {
                return france;
            }

            public void setFrance(FranceBean france) {
                this.france = france;
            }

            public JapanBean getJapan() {
                return japan;
            }

            public void setJapan(JapanBean japan) {
                this.japan = japan;
            }

            public CzechBean getCzech() {
                return czech;
            }

            public void setCzech(CzechBean czech) {
                this.czech = czech;
            }

            public SwedenBean getSweden() {
                return sweden;
            }

            public void setSweden(SwedenBean sweden) {
                this.sweden = sweden;
            }

            public static class UssrBean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class UsaBean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class GermanyBean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class ChinaBean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class UkBean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class FranceBean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class JapanBean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private int battles_count_percent;
                private int wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public int getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(int battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public int getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(int wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class CzechBean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class SwedenBean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }
        }

        public static class TypesBean {
            /**
             * battles_count : 5633
             * wins_count : 3090
             * master_count : 62
             * battles_count_percent : 20.13
             * wins_count_percent : 54.86
             */

            private LightTankBean lightTank;
            /**
             * battles_count : 7900
             * wins_count : 4312
             * master_count : 32
             * battles_count_percent : 28.23
             * wins_count_percent : 54.58
             */

            private HeavyTankBean heavyTank;
            /**
             * battles_count : 6957
             * wins_count : 3822
             * master_count : 60
             * battles_count_percent : 24.86
             * wins_count_percent : 54.94
             */

            private MediumTankBean mediumTank;
            /**
             * battles_count : 4334
             * wins_count : 2409
             * master_count : 50
             * battles_count_percent : 15.49
             * wins_count_percent : 55.58
             */

            private ATSPGBean ATSPG;
            /**
             * battles_count : 3159
             * wins_count : 1636
             * master_count : 22
             * battles_count_percent : 11.29
             * wins_count_percent : 51.79
             */

            private SPGBean SPG;

            public LightTankBean getLightTank() {
                return lightTank;
            }

            public void setLightTank(LightTankBean lightTank) {
                this.lightTank = lightTank;
            }

            public HeavyTankBean getHeavyTank() {
                return heavyTank;
            }

            public void setHeavyTank(HeavyTankBean heavyTank) {
                this.heavyTank = heavyTank;
            }

            public MediumTankBean getMediumTank() {
                return mediumTank;
            }

            public void setMediumTank(MediumTankBean mediumTank) {
                this.mediumTank = mediumTank;
            }

            public ATSPGBean getATSPG() {
                return ATSPG;
            }

            public void setATSPG(ATSPGBean ATSPG) {
                this.ATSPG = ATSPG;
            }

            public SPGBean getSPG() {
                return SPG;
            }

            public void setSPG(SPGBean SPG) {
                this.SPG = SPG;
            }

            public static class LightTankBean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class HeavyTankBean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class MediumTankBean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class ATSPGBean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }

            public static class SPGBean {
                private int battles_count;
                private int wins_count;
                private int master_count;
                private double battles_count_percent;
                private double wins_count_percent;

                public int getBattles_count() {
                    return battles_count;
                }

                public void setBattles_count(int battles_count) {
                    this.battles_count = battles_count;
                }

                public int getWins_count() {
                    return wins_count;
                }

                public void setWins_count(int wins_count) {
                    this.wins_count = wins_count;
                }

                public int getMaster_count() {
                    return master_count;
                }

                public void setMaster_count(int master_count) {
                    this.master_count = master_count;
                }

                public double getBattles_count_percent() {
                    return battles_count_percent;
                }

                public void setBattles_count_percent(double battles_count_percent) {
                    this.battles_count_percent = battles_count_percent;
                }

                public double getWins_count_percent() {
                    return wins_count_percent;
                }

                public void setWins_count_percent(double wins_count_percent) {
                    this.wins_count_percent = wins_count_percent;
                }
            }
        }

        public static class MasterLevelsBean {
            private int master2;
            private int master4;
            private int master3;
            private int master1;

            public int getMaster2() {
                return master2;
            }

            public void setMaster2(int master2) {
                this.master2 = master2;
            }

            public int getMaster4() {
                return master4;
            }

            public void setMaster4(int master4) {
                this.master4 = master4;
            }

            public int getMaster3() {
                return master3;
            }

            public void setMaster3(int master3) {
                this.master3 = master3;
            }

            public int getMaster1() {
                return master1;
            }

            public void setMaster1(int master1) {
                this.master1 = master1;
            }
        }

        public static class MasterLevelCountsBean {
            private int master2;
            private int master4;
            private int master3;
            private int master1;

            public int getMaster2() {
                return master2;
            }

            public void setMaster2(int master2) {
                this.master2 = master2;
            }

            public int getMaster4() {
                return master4;
            }

            public void setMaster4(int master4) {
                this.master4 = master4;
            }

            public int getMaster3() {
                return master3;
            }

            public void setMaster3(int master3) {
                this.master3 = master3;
            }

            public int getMaster1() {
                return master1;
            }

            public void setMaster1(int master1) {
                this.master1 = master1;
            }
        }
    }
}
