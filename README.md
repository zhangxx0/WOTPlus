# WOTPlus
_ _ _

## 前言

**WOTPlus** 是一款 **坦克世界**（World of Tanks）战绩查询APP，旨在为国服坦克世界玩家提供一个方便且专业而又不失优美的战绩查询APP；

作为一个坦克世界的老司机，我还是比较关心自己的战绩与排名，然而并没有一款好的APP来让我实时的掌握我的战绩动态；

与其等待，不如自己动手，且好几年过去了，并不见坑爹网（空中网）的APP有些许的进步，国服对这个好像不怎么重视，于是，在学习了半年左右的Android之后，我动手来做人生第一个开源安卓项目，我想用可以接触到的最先进的安卓技术来开发这款APP，因为我觉得这是一件很酷的事情，而，上班以来，好久没有做过很酷的事情了。

2016年6月26日 更新到 **0.9版本** ，新增XVM战绩查询功能；


## 截图

_ _ _ 

![](http://7xsvfv.com2.z0.glb.clouddn.com/wotplus_xiaoguo2.jpg)
![](http://7xsvfv.com2.z0.glb.clouddn.com/wotplus_xiaoguo1.jpg)
![](http://7xsvfv.com2.z0.glb.clouddn.com/wotplus_xiaoguo3.jpg)
![](http://7xsvfv.com2.z0.glb.clouddn.com/wotplus_xiaoguo4.jpg)
![](http://7xsvfv.com2.z0.glb.clouddn.com/wotplus_xiaoguo5.jpg)

_ _ _ 

![](http://7xsvfv.com1.z0.glb.clouddn.com/xvm_show0.jpg)
![](http://7xsvfv.com1.z0.glb.clouddn.com/xvm_show1.jpg)


## 功能简介

WOTPlus主要提供坦克世界战绩查询的功能，分为以下几个模块展示战绩信息：

- 概要信息
- 成就
- 统计
- 等级
- 战车

新增功能：（2016年6月26日）

- XVM战绩查询

## 技术阐述

WOTPlus遵循 **Material Design** 设计原则，使用最新的控件，诸如：

DrawerLayout、CoordinatorLayout、Toolbar、FloatingActionButton、NavigationView、RecyclerView、CardView等

**数据源**：由于坦克世界官网的战绩查询页面并没有暴露json接口，所以我使用的是获取战绩页面html并使用jsoup解析数据，实属无奈之举，也造成查询时耗时较长；

开源库：

* html解析：jsoup；
* json解析：gson；
* 网络请求：Volley；  
* 图片加载：Glide；
* 图表展示：MPAndroidChart；
* gif展示：android-gif-drawable；
* materialedittext -by 扔物线；
* 左滑回退：swipebacklayout；
* RecyclerView动画：recyclerview-animators；

第一版并没有使用架构诸如MVP、或者Rxjava等流行技术之类，别问我为什么，还没学会。。。
因此编写之初就有了重构的计划，初步构想是使用Rxjava+Retrofit进行第二个版本的重构

2016年6月26日 新技术的使用：

- RxJava+Retrofit
- ButterKnife
- Jpush


## 版本更新


* Fir.im：[http://fir.im/WOTPlus](http://fir.im/WOTPlus)
* 魅族应用中心：[ WOTPlus-魅族应用中心 ](http://app.meizu.com/apps/public/detail?package_name=com.xinxin.wotplus)
* 应用宝：[ WOTPlus-应用宝](http://android.myapp.com/myapp/detail.htm?apkName=com.xinxin.wotplus)



## TODO

- [x] 代码重构：RxJava + Retrofit；2016年5月21日03:31:21 完成基础的重构；
- [ ] fresco 替代 glide；
- [x] butter knife 使用；2016年6月26日 新页面使用该技术；


## 贴吧帖子

两个精品置顶帖：  
[05-06【原创】国服坦克世界战绩查询APP（安卓）WOTPlus上线了](http://tieba.baidu.com/p/4530207210)  
[06-26【原创】国服战绩查询APP WOTPlus 更新XVM查询](http://tieba.baidu.com/p/4634156452)

第一个版本0.6有2886的下载量；  
第二个版本0.9正在增长中，，，



## 关于作者

_ _ _

一说关于作者就想胡吹海吹一番，矜持，矜持，，，

作者是一个竞技水平比较低的玩家，说来惭愧，从小到大玩的比较好的游戏有两个，一个是《坦克世界》，另一个就是《扫雷》了；

然而，作者也是有一颗拯救世界的心的！

想深入了解作者的可以戳这个：[ Zhangxx的博客 | 关于 ](http://amx1390.com/about/)  

微博：[http://weibo.com/2112920937](http://weibo.com/2112920937)  
个人博客：[http://amx1390.com](http://amx1390.com)  


差点忘了，土豪要是想打赏我的话，可以扫下面的支付宝(不说了，先去搬砖了)：
![ Zhangxx的支付宝 ](http://7xti0t.com2.z0.glb.clouddn.com/zhifubao)







