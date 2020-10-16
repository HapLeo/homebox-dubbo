package top.hapleow.customersupportserviceapp.model;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.Data;
import top.hapleow.customersupportserviceapp.serialize.Serializer;

/**
 * 应用层协议包抽象
 */
@Data
public abstract class Packet {

    /**
     * 魔数 4字节
     */
    private static final int MAGIC_NUMBER = 0x12345678;

    /**
     * 版本号 1字节
     */
    private Byte version = 1;

    /**
     * 序列化算法 1字节
     */
    private Byte serializer;

    /**
     * 指令 1字节
     */
    private Byte command;

    /**
     * 内容长度 4字节
     */
    private Integer lenth;

    /**
     * 内容
     */
    private String content;



    public ByteBuf encode(Packet packet) {

        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(packet.getSerializer());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }


}
