package com.nettytest.demo2socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @program: nettyTest
 * @description: 自己定义的处理器
 * @author: Cloud.
 * @create: 2019-04-17 16:14
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("客户端地址:"+ctx.channel().remoteAddress());
        System.out.println("来自于客户端的数据为"+msg);
        ctx.channel().writeAndFlush(""+UUID.randomUUID());


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
