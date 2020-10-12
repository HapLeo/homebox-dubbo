package top.hapleow.common.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class NIOServer {

    public static void main(String[] args) {

        // 服务器的引导
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        // 创建bossGroup
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();

        // 创建workerGroup
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        // 将bossGroup 和 workerGroup 交给引导类
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                }).bind(9000);
    }
}
