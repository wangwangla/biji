# Android简介

- SDK的安装以及环境的搭建
  - 下载java的jdk
  - 双击安装包安装
  - 环境变量配置
    - JAVA_HOME配置，以及path配置
  - 下载SDK,配置环境变量，配置到tools。【待考证】
- Eclipse继承环境的搭建
  - 下载eclipse，并经其解压
  - 安装安卓插件
    - http://dl-ssl.google.com/android/eclipse
  - 配置eclipse的SDK
- 模拟器的创建
  - 创建模拟器，输入模拟器的名字以及参数设置
- 调试
  - Android一个强大的调试工具DDMS，除了可以使用Syso.println(),还可以使用Android.utils.Log,如果数据太多，可以使用过滤器进行。
- Android应用程序的项目结构
  - src:源码
  - gen：系统生成包
  - Android 7.1 ：Android jar
  - res:资源文件
- Android系统架构
  - Android由应用程序、应用程序框架、Android运行时、系统库、以及liunx内核。
    - 应用程序框架是开发的基础，开发大部分时间在此层，此层包含视图系统、活动管理器、通知管理器、内容提供者、窗口管理器、位置管理器、资源管理器 、电话管理器、包管理器。
      - 活动管理器：应用程序生命周期，以及返回导航栈
      - 窗口管理器：管理窗口程序
- Android运行时
  - 安卓运行时包括核心库和Dalvik虚拟机两部分
    - 核心库：Java所需调用函数函数和安卓核心库。
    - Dalvik：寄存器的Java虚拟机，其一开转换工具dx将字节码转换为dex格式
- 



