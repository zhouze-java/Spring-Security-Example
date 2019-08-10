package com.security.example.core.config.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author 周泽
 * @date Create in 18:36 2019/8/10
 * @Description redis序列化
 */
public class RedisObjectSerializer  implements RedisSerializer<Object> {

    static final byte[] EMPTY_ARRAY = new byte[0];

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o == null) {
            return EMPTY_ARRAY;
        }

        ObjectOutputStream oos = null;
        ByteArrayOutputStream bos = null;

        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);

            oos.writeObject(o);
            byte[] byt = bos.toByteArray();

            return byt;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (isEmpty(bytes)) {
            return null;
        }

        ObjectInputStream ois = null;
        ByteArrayInputStream bis = null;

        bis = new ByteArrayInputStream(bytes);
        try {
            ois = new ObjectInputStream(bis);
            Object obj = ois.readObject();

            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }
}
