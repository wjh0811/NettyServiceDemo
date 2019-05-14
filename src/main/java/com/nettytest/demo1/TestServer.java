package com.nettytest.demo1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {


    public static void main(String[] args) throws Exception{
        //定义两个线程组进行死循环,两个事件循环组
        //boss组不断地从客户端接收连接，不作处理，只是把连接转给worker组
        //服务器死循环， Tomcat服务器也有死循环
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //netty提供的启动相关的代码
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //定义一个通道和处理器
            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                       .childHandler(new TestServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }





    }
}
