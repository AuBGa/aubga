package com.aubga.rpc.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author AuBGa
 */
public class ClientSet {

    public void simpleServerClient() throws Exception{
        TTransport transport = new TSocket(ServerConfig.SERVER_IP, ServerConfig.SERVER_PORT, ServerConfig.TIMEOUT);
        TProtocol protocol = new TBinaryProtocol(transport);
        HelloWorldService.Client client = new HelloWorldService.Client(protocol);
        transport.open();

        String result = client.say("Leo");
        System.out.println("Result =: " + result);
        transport.close();

    }

    public void threadpoolServerClient() throws Exception {
        TTransport transport = new TSocket(ServerConfig.SERVER_IP, ServerConfig.SERVER_PORT, ServerConfig.TIMEOUT);
        TProtocol protocol = new TBinaryProtocol(transport);
        HelloWorldService.Client client = new HelloWorldService.Client(protocol);

        transport.open();
        String result = client.say("ThreadPoolClient");
        System.out.println("Result =: " + result);
        transport.close();

    }

    public void nonblockingServerClient() throws Exception {
        TTransport transport = new TFramedTransport(new TSocket(ServerConfig.SERVER_IP, ServerConfig.SERVER_PORT, ServerConfig.TIMEOUT));
        // 协议要和服务端一致
        TProtocol protocol = new TCompactProtocol(transport);
        HelloWorldService.Client client = new HelloWorldService.Client(protocol);
        transport.open();

        String result = client.say("NonBlockingClient");
        System.out.println("Result =: " + result);
        transport.close();

    }

    //
    public void thsHaServerClient() throws  Exception {
        TTransport transport = new TFramedTransport(new TSocket(ServerConfig.SERVER_IP, ServerConfig.SERVER_PORT, ServerConfig.TIMEOUT));
        // 协议要和服务端一致
        TProtocol protocol = new TBinaryProtocol(transport);
        HelloWorldService.Client client = new HelloWorldService.Client(protocol);
        transport.open();

        String result = client.say("HsHaClient");
        System.out.println("Result =: " + result);
        transport.close();

    }


    public void threadedSelectorServer() throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread("Thread " + i) {
                @Override
                public void run() {
                    // 设置传输通道 对于非阻塞服务 需要使用TFramedTransport(用于将数据分块发送)
                    for (int j = 0; j < 10; j++) {
                        TTransport transport = null;
                        try {
                            transport = new TFramedTransport(new TSocket(ServerConfig.SERVER_IP, ServerConfig.SERVER_PORT, ServerConfig.TIMEOUT));
                            TProtocol protocol = new TBinaryProtocol(transport);
                            HelloWorldService.Client client = new HelloWorldService.Client(protocol);
                            transport.open();
                            String result = client.say("ThreadedSelector Client");
                            System.out.println("Result =: " + result);
                            transport.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            // 关闭传输通道
                            transport.close();
                        }
                    }
                }
            }.start();
        }
    }

    public static void main(String[] args) throws Exception {
        ClientSet cs = new ClientSet();
        cs.simpleServerClient();
    }
}
