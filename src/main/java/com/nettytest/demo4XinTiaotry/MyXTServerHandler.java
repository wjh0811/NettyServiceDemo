package com.nettytest.demo4XinTiaotry;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @program: nettytest
 * @description:
 * @author: Cloud.
 * @create: 2019-04-18 10:56
 */
public class MyXTServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
       //   检测空闲状态的事件。
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType = null;
            switch (event.state()) {
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;

            }
            System.out.println(ctx.channel().remoteAddress()+"-----超时事件：  "+eventType);
            ctx.channel().close();

        }
    }
}
