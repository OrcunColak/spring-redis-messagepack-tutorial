package com.colak.springtutorial.stringvalue.config;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;

public class MessagePackRedisSerializer<T> implements RedisSerializer<T> {

    private final Class<T> type;

    public MessagePackRedisSerializer(Class<T> type) {
        this.type = type;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }

        try {
            return MessagePackMapper.serialize(t);
        } catch (IOException exception) {
            throw new SerializationException("Could not serialize", exception);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return MessagePackMapper.deserialize(bytes, type);
        } catch (IOException exception) {
            throw new SerializationException("Could not deserialize", exception);
        }
    }
}

