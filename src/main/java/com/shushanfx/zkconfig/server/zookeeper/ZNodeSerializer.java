package com.shushanfx.zkconfig.server.zookeeper;

import com.shushanfx.zkconfig.server.util.JSONUtils;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.UnsupportedEncodingException;

/**
 * Created by shushanfx on 17/六月/24.
 */
public class ZNodeSerializer implements ZkSerializer {

    @Override
    public byte[] serialize(Object data) throws ZkMarshallingError {
        String value = null;
        if(data instanceof String
                || data instanceof Number
                || data instanceof Boolean){
            value = data.toString();
        }
        else if(data instanceof Object){
            value = JSONUtils.toString(data);
        }
        if(value!=null){
            try {
                return value.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new ZkMarshallingError(e);
            }
        }

        return new byte[0];
    }

    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        if(bytes!=null){
            try {
                return new String(bytes, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new ZkMarshallingError(e);
            }
        }
        return null;


    }
}
