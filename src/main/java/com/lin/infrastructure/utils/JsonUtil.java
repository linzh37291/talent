package com.lin.infrastructure.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author linzihao
 */
@Slf4j
public class JsonUtil {

    public static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 驼峰转换
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        // 允许出现单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            throw new RuntimeException("JacksonUtil.readValue(String,Class<T>)执行异常", e);
        }
    }

    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            throw new RuntimeException("JacksonUtil.readValue(String,TypeReference<T>)执行异常", e);
        }
    }

    public static <T> T readValue(String jsonStr, Class<?> parametrized, Class<?>... parameterClasses) {
        JavaType type = objectMapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
        try {
            return objectMapper.readValue(jsonStr, type);
        } catch (IOException e) {
            throw new RuntimeException("JacksonUtil.readValue(String,Class<?>,Class...)执行异常", e);
        }
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.info("JacksonUtil.toJson执行异常:{0}", e);
            throw new RuntimeException("JacksonUtil.toJson执行异常", e);
        }
    }

    public static Map<String, Object> toMap(String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, new TypeReference<HashMap<String, Object>>() {
            });
        } catch (Exception e) {
            log.info("JacksonUtil.toMap(String)执行异常:{},{}", jsonStr, e);
            throw new RuntimeException("JacksonUtil.toMap(String)执行异常", e);
        }
    }

}
