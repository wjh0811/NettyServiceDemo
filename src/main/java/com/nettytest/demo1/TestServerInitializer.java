package com.nettytest.demo1;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @program: nettyTest
 * @description: 初始化
 * @author: liu yan
 * @create: 2019-04-17 10:35
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel ch) throws Exception {
        /**
        *@Description: channel创建后，该方法就创建执行
        *@Name: initChannel
        *@Param: [ch]
        *@return: void
        *@Author: Cloud.
        *@date: 2019/4/17
        */

        ChannelPipeline pipeline = ch.pipeline();
        //多例模式
        //对于web的请求和响应进行编解码
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        pipeline.addLast("testHttpServerHander",new TestHttpServerHander());



    }
}
