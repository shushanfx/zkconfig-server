package com.shushanfx.zkconfig.server.util;

/**
 * Created by shushanfx on 2017/7/17.
 */
public class SizeUtils {
    public static final String getSizeString(int size){
        if(size <= 0){
            return "EMPTY";
        }
        else if(size < 1024){
            return String.format("%db", size);
        }
        else if(size < 1024 * 1024){
            return String.format("%.2fk", size / 1024.0);
        }
        else if(size < 1024 * 1024 * 1024){
            return String.format("%.2fM", size / 1024.0 / 1024.0);
        }
        else if(size < 1024 * 1024 * 1024 * 1024){
            return String.format("%.2fG", size / 1024.0 / 1024.0 / 1024.0);
        }
        return "TOO LARGE";
    }
}
