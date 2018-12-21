# 酒店管理系统
---

## 项目进度介绍
目前所有代码都已经写完了，但是程序Robust性较差，不支持并发，不适合实际使用，有个很严重的bug问题并未解决，就是`ShowStage`绑定数据出错，而且一旦出错，就会导致别的功能异常。

## 项目介绍
此项目是以五星级酒店的订房服务为参照，有不同的房间类型，客人需要先付现金后入住，除了面向前台服务员的操作界面，还能对清洁工提供了窗口，方便及时更新房间信息。

此项目用到相关技术、软件包有:
* javaFX
* Druid
* Dao设计模式
* MVC设计模式

## ui设计
ui设计一开始打算用Google的Material Design，但好像javaFX支持不太好呢

## 项目结构

主要项目结构如下
- src/
    - application (主要放置启动程序，以及各类窗口)
        - App(程序入口)
        - LoginStage(登陆界面)
        - CleanerStage(清洁工界面)
        - MainStage(前台界面)
        - BookStage
        - RegisterStage
        - LeaveStage
        - ShowStage(仍未开发完成)
    - application.toolkit(开发时抽象出来的重复使用到的组件)
    - control(逻辑层)
    - Log(日志记录)
    - model(数据层)
    - model.domain 
    - model.Dao
    - model.Dao.Impl