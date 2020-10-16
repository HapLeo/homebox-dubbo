package top.hapleow.customersupportserviceapp.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;


public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 接收到客户端的消息时触发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("消息接收：" + byteBuf.toString(Charset.forName("utf-8")));
        String responseMsg = "你好，我是NettyServer.";
        byte[] msgBytes = responseMsg.getBytes(Charset.forName("utf-8"));
        ByteBuf buffer = ctx.channel().alloc().buffer();
        buffer.writeBytes(msgBytes);
        ctx.channel().writeAndFlush(buffer);
        System.out.println("消息发送：" + responseMsg);
    }
}
