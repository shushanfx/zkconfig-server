package com.shushanfx.zkconfig.server.controller;

import com.shushanfx.zkconfig.server.bean.ResultInfo;
import com.shushanfx.zkconfig.server.zookeeper.ZNodeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dengjianxin on 2017/6/13.
 */
@Controller
@RequestMapping("/connection")
public class ConnectionClient {
    @Autowired
    private ZNodeClient client = null;

    public @RequestMapping("/index")
    String index(){
        return "connection/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public ResultInfo list(String path, String ip){
        ResultInfo info = ResultInfo.newSuccess();
        info.setData(client.getConnectionList(path, ip));
        return info;
    }
}
