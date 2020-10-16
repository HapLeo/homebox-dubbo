package top.hapleow.customersupportserviceapp.service;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import top.hapleow.customersupportserviceapp.handler.FirstClientHandler;

/**
 * 使用Netty创建服务端
 */
public class NettyClient {


    public static void main(String[] args) {

        String host = "localhost";
        int port = 8000;

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println("客户端初始化中...");
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                });
        bootstrap.connect(host, port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("连接成功！");
                } else {
                    System.out.println("连接失败！");
                }
            }
        });
        bootstrap.attr(AttributeKey.newInstance("clientAttr"), "clientAttrValue");
    }

}
