package com.aubga.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
	
    protected void initChannel(SocketChannel sc) {
        ChannelPipeline pipeline = sc.pipeline();
        //处理http消息的编解码
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        
       // pipeline.addLast("httpResponseEndcoder", new HttpResponseEncoder());
       // pipeline.addLast("HttpRequestDecoder", new HttpRequestDecoder());
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
        //添加自定义的ChannelHandler
        pipeline.addLast("httpServerHandler", new HttpServerChannelHandler());
  
    }
}