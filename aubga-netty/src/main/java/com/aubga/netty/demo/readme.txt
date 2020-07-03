Netty简介
Netty是一个基于JAVA NIO 类库的异步通信框架，它的架构特点是：异步非阻塞、基于事件驱动、高性能、高可靠性和高可定制性。换句话说，Netty是一个NIO框架，使用它可以简单快速地开发网络应用程序，比如客户端和服务端的协议。Netty大大简化了网络程序的开发过程比如TCP和UDP的 Socket的开发。Netty 已逐渐成为 Java NIO 编程的首选框架。

什么是物联网？
nio通信框架

物联网主要运用到netty哪些特性
a、TCP长连接
b、能够和各种序列化框架完美整合

为什么要使用netty，相对于其他通信框架mina有哪些优点
a、API使用简单，开发门槛低
b、功能强大，预置了多种编解码功能，支持多种主流协议
c、社区活跃，版本更新快
d、技术稳定可靠，如：elasticsearch、spark、dubbo、motan等开源框架底层通信采用的是netty

1、Netty服务端编写
首先，Netty通过ServerBootstrap启动服务端代码，需要四步：
第一步，定义两个线程组，用来处理客户端通道的accept和读写事件
第二步，绑定服务端通道NioServerSocketChannel
第三步，给读写事件的线程通道绑定handle，处理具体的业务逻辑
第四步，绑定监听