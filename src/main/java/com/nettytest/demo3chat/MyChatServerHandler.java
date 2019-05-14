package com.nettytest.demo3chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @program: nettyTest
 * @description: 服务器聊天处理器
 * @author: Cloud.
 * @create: 2019-04-17 17:50
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {
    //定义全局的channel组，用来存储连接成功生成的channel
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        /*业务分析：
             三个客户端A,B,C上线聊天；
             用户上线后服务器打印上线，并且告诉其他已经上线的用户，该用户已经上线；
             A发送消息（广播消息），B,C都收到消息；*/
        Channel channel = ctx.channel();
           channelGroup.forEach(ch ->{
               if (channel != ch) {
                   ch.writeAndFlush(channel.remoteAddress() + "发送的消息：" + msg+ "\n");
               } else {
                   ch.writeAndFlush("自己要发送的消息为：" + msg + "\n");
               }
           });


    }

    //当连接建立的时候，tcp建立，handleradded自动调用
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //向channelgroup中的每一个channel发送一条消息。
        channelGroup.writeAndFlush("服务器通知：" + channel.remoteAddress() + "上线\n");

        channelGroup.add(channel);

    }

    //当连接失去的时候，handlerremoved自动调用
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //向channelgroup中的每一个channel发送一条消息。
        channelGroup.writeAndFlush("服务器通知：" + channel.remoteAddress() + "-----下线\n");
        //断开连接，netty自动调用remove方法，无需手工移除
        channelGroup.remove(channel);
    }

    //当连接处于活动状态（即刚连接成功后）的时候，channelActive自动调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+"-----上线了\n");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+"-----下线了\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
