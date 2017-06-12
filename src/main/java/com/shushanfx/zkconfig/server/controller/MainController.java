package com.shushanfx.zkconfig.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
