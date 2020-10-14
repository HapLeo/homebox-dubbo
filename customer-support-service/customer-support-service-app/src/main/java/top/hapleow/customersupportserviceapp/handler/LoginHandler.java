package top.hapleow.customersupportserviceapp.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.hapleow.customersupportserviceapp.model.LoginRequest;

public class LoginHandler extends SimpleChannelInboundHandler<LoginRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequest msg) throws Exception {
        System.out.println(msg);
    }
}
