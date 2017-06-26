package com.shushanfx.zkconfig.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shushanfx on 17/六月/10.
 */
@Controller
public class MainController {
    @RequestMapping(path = {"/", "/index"})
    String index(){
        return "index";
    }

    @ExceptionHandler
    void exception(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(500, "系统异常，稍后再试！");
    }
}
