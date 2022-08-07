package com.lin.infrastructure.commons.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author linzihao
 */
public class LongToStringSerializer extends JsonSerializer<Long> {
    @Override
    public void serialize(Long value, JsonGenerator jgen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            jgen.writeNull();
            return;
        }
        jgen.writeString(String.valueOf(value));
    }
}
