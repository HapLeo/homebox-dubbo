package top.hapleow.common.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.Charset;
import java.util.Scanner;

public class NIORobotClient {

    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                ByteBuf buffer = ctx.channel().alloc().buffer();
                                String message = "商品编号: No100001";
                                byte[] bytes = message.getBytes(Charset.forName("utf-8"));
                                buffer.writeBytes(bytes);
                                ctx.channel().writeAndFlush(buffer);
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

                                ByteBuf byteBuf = (ByteBuf) msg;
                                System.out.println(byteBuf.toString(Charset.forName("utf-8")));
                                Scanner scanner = new Scanner(System.in);
                                String message = scanner.nextLine();
                                ByteBuf buffer = ctx.channel().alloc().buffer();
                                byte[] bytes = message.getBytes(Charset.forName("utf-8"));
                                buffer.writeBytes(bytes);
                                ctx.channel().writeAndFlush(buffer);


                            }
                        });
                    }
                }).connect("localhost", 9000).addListener(future -> {
                    if (future.isSuccess()){
                        System.out.println("连接成功！");
                    }else{
                        System.out.println("连接失败！");
                    }
        });


    }
}
