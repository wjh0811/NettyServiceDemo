package com.nettytest.demo4XinTiaotry;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @program: nettytest
 * @description:
 * @author: Cloud.
 * @create: 2019-04-18 10:48
 */
public class MyXTServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //在一段时间内，既没有读，也没有写，就会触发方法
        //加入空闲状态处理器
        pipeline.addLast(new IdleStateHandler(3,4,6, TimeUnit.SECONDS));
//      //自定义的处理器
        pipeline.addLast(new MyXTServerHandler());
    }
}
