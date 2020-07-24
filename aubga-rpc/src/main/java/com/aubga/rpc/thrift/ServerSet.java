package com.aubga.rpc.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.*;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;

import java.net.ServerSocket;

public class ServerSet {

    //TSimpleServer,异常未作优化处理
    //单线程服务器,标准的阻塞式I/O
    public void simpleServer() throws Exception {
        ServerSocket serverSocket = new ServerSocket(ServerConfig.SERVER_PORT);
        TServerSocket serverTransport = new TServerSocket(serverSocket);
        HelloWorldService.Processor processor =
                new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();

        TSimpleServer.Args tArgs = new TSimpleServer.Args(serverTransport);
        tArgs.processor(processor);
        tArgs.protocolFactory(protocolFactory);
        // 简单的单线程服务模型 一般用于测试
        TServer tServer = new TSimpleServer(tArgs);
        System.out.println("Running Simple Server");
        tServer.serve();
    }

    /**
     * 多线程服务器,标准的阻塞式I/O
     * One Thread Per Connection
     * @throws Exception
     */
    public void threadpoolServer() throws Exception {
        ServerSocket serverSocket = new ServerSocket(ServerConfig.SERVER_PORT);
        TServerSocket serverTransport = new TServerSocket(serverSocket);
        HelloWorldService.Processor<HelloWorldService.Iface> processor =
                new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());

        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
        TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(serverTransport);
        ttpsArgs.processor(processor);
        ttpsArgs.protocolFactory(protocolFactory);

        // 线程池服务模型 使用标准的阻塞式IO 预先创建一组线程处理请求
        TServer ttpsServer = new TThreadPoolServer(ttpsArgs);
        System.out.println("Running ThreadPool Server");
        ttpsServer.serve();

    }

  /**
   * TNonblockingServer模式也是单线程工作，但是采用NIO的模式，借助Channel/Selector机制, 采用IO事件模型来处理。
   * 所有的socket都被注册到selector中，在一个线程中通过seletor循环监控所有的socket。
   * 每次selector循环结束时，处理所有的处于就绪状态的socket，对于有数据到来的socket进行数据读取操作，对于有数据发送的socket则进行数据发送操作，对于监听socket则产生一个新业务socket并将其注册到selector上。
   *
   * @throws Exception
   */
  public void nonblockingServer() throws Exception {
        TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
        TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(ServerConfig.SERVER_PORT);

        TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(tnbSocketTransport);
        tnbArgs.processor(tprocessor);
        tnbArgs.transportFactory(new TFramedTransport.Factory());
        tnbArgs.protocolFactory(new TCompactProtocol.Factory());

        // 使用非阻塞式IO服务端和客户端需要指定TFramedTransport数据传输的方式
        TServer server = new TNonblockingServer(tnbArgs);
        System.out.println("Running Non-blocking Server");
        server.serve();

    }

    /**
     * 半同步半异步服务器端,基于非阻塞式IO读写和多线程工作任务处理
     * @throws Exception
     */
    public void thsHaServer() throws Exception {
        TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(ServerConfig.SERVER_PORT);
        TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
        // 半同步半异步
        THsHaServer.Args thhsArgs = new THsHaServer.Args(tnbSocketTransport);
        thhsArgs.processor(tprocessor);
        thhsArgs.transportFactory(new TFramedTransport.Factory());
        thhsArgs.protocolFactory(new TBinaryProtocol.Factory());

        TServer server = new THsHaServer(thhsArgs);
        System.out.println("Running HsHa Server");
        server.serve();
    }

    /**
     * 多线程选择器服务器,对ThsHaServer在异步IO模型上进行增强
     * @throws Exception
     */
    public void threadedSelectorServer() throws Exception {
        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(ServerConfig.SERVER_PORT);
        TProcessor processor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
        // 多线程半同步半异步
        TThreadedSelectorServer.Args ttssArgs = new TThreadedSelectorServer.Args(serverSocket);
        ttssArgs.processor(processor);
        ttssArgs.protocolFactory(new TBinaryProtocol.Factory());
        // 使用非阻塞式IO时 服务端和客户端都需要指定数据传输方式为TFramedTransport
        ttssArgs.transportFactory(new TFramedTransport.Factory());

        // 多线程半同步半异步的服务模型
        TThreadedSelectorServer server = new TThreadedSelectorServer(ttssArgs);
        System.out.println("Running ThreadedSelector Server");
        server.serve();

    }

    //注释式运行,不然端口冲突
    public static void main(String[] args) throws Exception {
        ServerSet ss = new ServerSet();
        //单线程,标准阻塞式IO
        ss.simpleServer();

        //多线程,标准阻塞式IO
        ss.threadpoolServer();

        //多线程服务器,使用非阻塞式IO
        ss.nonblockingServer();

        //半同步半异步,非阻塞式IO读写与多线程工作任务处理
        ss.thsHaServer();

        //多线程选择服务器,对THsHaServer在异步IO模型上进行增强
        ss.threadedSelectorServer();
    }
}
