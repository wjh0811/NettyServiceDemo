package com.nettytest.demo4XinTiaotry;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: nettytest
 * @description: 聊天客户端A的处理器
 * @author: Cloud.
 * @create: 2019-04-18 10:06
 */
public class MyXTClientAHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}
