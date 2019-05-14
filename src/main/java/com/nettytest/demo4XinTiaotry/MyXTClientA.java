package com.nettytest.demo4XinTiaotry;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @program: nettytest
 * @description: 客户端A
 * @author: Cloud.
 * @create: 2019-04-18 10:00
 */
public class MyXTClientA {
    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors).channel(NioSocketChannel.class).handler(new MyXTClientAInitializer());

            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
            //死循环，不断地读取控制台输入的内容
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            for (; ; ) {
                channel.writeAndFlush(br.readLine() + "\r\n");
            }

        }finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
