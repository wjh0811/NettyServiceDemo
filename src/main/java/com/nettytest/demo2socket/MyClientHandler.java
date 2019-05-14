package com.nettytest.demo2socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @program: nettyTest
 * @description: 自己定义的客户端处理器
 * @author: Cloud.
 * @create: 2019-04-17 16:48
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务器的远程地址为："+ctx.channel().remoteAddress());
        System.out.println("来自于服务器的数据为："+msg);
        ctx.writeAndFlush(""+LocalDateTime.now());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("来自于客户端的问候！");
    }
}
