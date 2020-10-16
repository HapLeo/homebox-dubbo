package top.hapleow.customersupportserviceapp.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channel active(客户端连接成功) ...");
        ByteBuf buffer = ctx.alloc().buffer();
        String message = "hello,I am clientA.";
        byte[] bytes = message.getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);
        ctx.channel().writeAndFlush(buffer);
        System.out.println("消息发送：" + message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        String responseMsg = byteBuf.toString(Charset.forName("utf-8"));
        System.out.println("消息接收：" + responseMsg);
    }
}
