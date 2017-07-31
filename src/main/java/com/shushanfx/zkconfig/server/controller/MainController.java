package com.shushanfx.zkconfig.server.controller;

import com.shushanfx.zkconfig.server.zookeeper.ZKConfigClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by shushanfx on 17/六月/10.
 */
@Controller
public class MainController {
    @Autowired
    ZKConfigClient zkConfigClient = null;

    @RequestMapping(path = {"/", "/index"})
    String index(Model model){
        model.addAttribute("config", zkConfigClient.getConfigInfo());
        return "index";
    }

    @ExceptionHandler
    void exception(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(500, "系统异常，稍后再试！");
    }
}
