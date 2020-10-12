package top.hapleow.common.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.Charset;

public class NIORobotServer {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf = (ByteBuf) msg;
                                String clientMsg = byteBuf.toString(Charset.forName("utf-8"));
                                System.out.println(clientMsg);

                                String returnMsg = "你发送的消息为：" + clientMsg;
                                if (clientMsg.contains("你好")){
                                    returnMsg = "你好，在的亲 :)";
                                }
                                if (clientMsg.contains("价格")){
                                    returnMsg = "这款价格不能优惠了哦，这个是折后价呢亲";
                                }
                                if (clientMsg.contains("好的")){
                                    returnMsg = "请问还有问题需要咨询吗，没有的话这边就给您结束服务咯 ：）";
                                }
                                if (clientMsg.contains("再见")){
                                    ctx.channel().close();
                                    System.out.println("服务已结束");
                                }

                                byte[] bytes = returnMsg.getBytes(Charset.forName("utf-8"));
                                ByteBuf buffer = ctx.channel().alloc().buffer();
                                buffer.writeBytes(bytes);
                                ctx.channel().writeAndFlush(buffer);
                            }
                        });
                    }
                }).bind(9000);

    }
}
