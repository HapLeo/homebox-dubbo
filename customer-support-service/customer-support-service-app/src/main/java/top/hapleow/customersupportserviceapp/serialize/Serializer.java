package top.hapleow.customersupportserviceapp.serialize;

/**
 * 序列化算法接口
 */
public interface Serializer {

    Byte JSON_SERIALIZER = 1;

    /**
     * 默认的序列化算法
     */
    Serializer DEFAULT = new JsonSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
