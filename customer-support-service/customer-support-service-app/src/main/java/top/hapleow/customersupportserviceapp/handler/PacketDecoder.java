package top.hapleow.customersupportserviceapp.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 魔数 4字节
        ByteBuf magic = in.readBytes(4);

        // 命令 4字节
        int command = in.readInt();

        // 内容长度 4字节
        int length = in.readInt();

        // 消息内容
        ByteBuf body = in.readBytes(length);

    }
}
