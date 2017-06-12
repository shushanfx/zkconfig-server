package com.shushanfx.zkconfig.server;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by shushanfx on 17/六月/10.
 */
@Configurable
@EnableAutoConfiguration
@ComponentScan("com.shushanfx.zkconfig.server")
public class MainEntry {
    public static void main(String[] args) {
        SpringApplication.run(MainEntry.class, args);
    }
}
