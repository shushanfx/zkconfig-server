package com.shushanfx.zkconfig.server.controller;

import com.shushanfx.zkconfig.server.bean.ResultInfo;
import com.shushanfx.zkconfig.server.zookeeper.ZNodeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by dengjianxin on 2017/7/17.
 */
@RequestMapping("/znode/history")
@Controller
public class ZkHistoryController {
    @Autowired
    private ZNodeClient client = null;

    @RequestMapping("/{name}")
    public String history(@PathVariable String name, Model model){
        return "history/index";
    }

    @RequestMapping("/list/{name}")
    @ResponseBody
    public ResultInfo list(@PathVariable String name){
        ResultInfo info = ResultInfo.newSuccess();
        try{
            info.setData(client.listHistory(name));
        } catch (Exception e){
            e.printStackTrace();
            info = ResultInfo.newFailure();
        }

        return info;
    }

    @RequestMapping("/delete/{name}/{id}")
    @ResponseBody
    public ResultInfo remove(@PathVariable String name, @PathVariable String id){
        ResultInfo info = null;
        try{
            boolean b = client.deleteHistory(name, id);
            if(b){
                info = ResultInfo.newSuccess();
            }
        } catch (Exception e){
            info = null;
            e.printStackTrace();
        }
        if(info==null){
            info = ResultInfo.newFailure();
        }
        return info;
    }

    @RequestMapping("/view/{name}/{id}")
    public String view(@PathVariable String name, @PathVariable String id, Model model){
        model.addAttribute("name", name);
        model.addAttribute("id", id);
        model.addAttribute("node", client.getInfo(name));
        model.addAttribute("nodeContent", client.getContent(name));
        model.addAttribute("history", client.getHistory(name, id));
        return "history/view";
    }
}
