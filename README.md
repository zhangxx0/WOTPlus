# WOTPlus
_ _ _

###前言

WOTPlus是一款坦克世界（World of Tanks）战绩查询软件，旨在为国服坦克世界玩家提供一个方便且专业而又不失优美的战绩查询APP；

作为一个坦克世界的老司机，我还是比较关心自己的战绩与排名，然而并没有一款好的app来让我实时的掌握我的战绩动态；

与其等待，不如自己动手，且好几年过去了，并不见wot的app有些许的进步，国服对这个好像不怎么重视，于是，在学习了半年左右的Android之后，我动手来做人生第一个开源安卓项目，我想用可以接触到的最先进的安卓技术来开发这款APP，因为我觉得这是一件很酷的事情，而，上班以来，好久没有做过很酷的事情了。

###截图




###功能简介

WOTPlus主要提供坦克世界战绩查询的功能，分为以下几个模块展示战绩信息：

- 概要信息
- 成就
- 统计
- 等级
- 战车

###技术阐述

WOTPlus遵循**Material Design**设计原则，使用最新的控件，诸如：

DrawerLayout、CoordinatorLayout、Toolbar、FloatingActionButton、NavigationView、RecyclerView、CardView等

**数据源**：由于坦克世界官网的战绩查询页面并没有暴露json接口，所以我使用的是获取战绩页面html并使用jsoup解析数据，实属无奈之举，也造成查询时耗时较长；

网络请求：Volley；

图片加载：Glide；

图表展示：MPAndroidChart；

版本更新：Fir；

第一版并没有使用架构诸如MVP、或者Rxjava等流行技术之类，别问我为什么，还没学会。。。
因此编写之初就有了重构的计划，初步构想是使用Rxjava+Retrofit进行第二个版本的重构


