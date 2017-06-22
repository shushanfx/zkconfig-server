package com.shushanfx.zkconfig.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by dengjianxin on 2017/6/20.
 */
public class JSONUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    public static  <T> T toObject(String content, Class<T> cls){
        try {
            return mapper.readValue(content, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  <T> String toString(T object){
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{}";
    }
}
