package com.shushanfx.zkconfig.server.controller;

import com.shushanfx.zkconfig.server.bean.ResultInfo;
import com.shushanfx.zkconfig.server.zookeeper.ZConfig;
import com.shushanfx.zkconfig.server.zookeeper.ZKConfigClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by shushanfx on 17/六月/11.
 */
@RequestMapping("/zconfig")
@Controller
public class ZkController {
    @Autowired
    private ZKConfigClient client = null;

    @ResponseBody
    @RequestMapping("/list")
    public ResultInfo<List<ZConfig>> list(String name){
        ResultInfo<List<ZConfig>> resultInfo = ResultInfo.newSuccess();
        resultInfo.setData(client.getZConfigList(name));
        return resultInfo;
    }

    @RequestMapping("/save/info")
    @ResponseBody
    public ResultInfo saveInfo(String name, String description,
                               @RequestParam(defaultValue = "properties") String type,
                               @RequestParam(defaultValue = "shushanfx") String username){
        client.saveInfo(name, description, type, username);
        return ResultInfo.newSuccess();
    }

    @RequestMapping("/save/content")
    @ResponseBody
    public ResultInfo saveContent(String name, String content, @RequestParam(defaultValue = "shushanfx") String username){
        client.saveContent(name, content, username);
        return ResultInfo.newSuccess();
    }

    @RequestMapping("/get/content/{name}")
    @ResponseBody
    public ResultInfo<ZConfig> getContent1(@PathVariable String name) {
        return getContent(name);
    }

    @RequestMapping("/get/content")
    @ResponseBody
    public ResultInfo<ZConfig> getContent(String name) {
        ZConfig config = client.getContent(name);
        ResultInfo<ZConfig> ret = null;
        if(config != null){
            ret = ResultInfo.newSuccess();
            ret.setData(config);
        }
        else{
            ret = ResultInfo.newFailure();
            ret.setMessage("Can not found config.");
        }
        return ret;
    }

    @RequestMapping("/delete/{name}")
    @ResponseBody
    public ResultInfo delete(@PathVariable String name){
        client.delete(name);
        return ResultInfo.newSuccess();
    }

    @RequestMapping("/edit/info")
    public String editInfo(Model model){
        model.addAttribute("edit", "add");
        return "zconfig/edit";
    }

    @RequestMapping("/edit/info/{name}")
    public String editInfo(@PathVariable String name, Model model){
        ZConfig node = client.getInfo(name);
        model.addAttribute("edit", "edit");
        model.addAttribute("node", node);
        model.addAttribute("name", name);
        return "zconfig/edit";
    }

    @RequestMapping("/edit/content/{name}")
    public String editContent(@PathVariable String name, Model model){
        ZConfig node = client.getContent(name);
        model.addAttribute("node", node);
        model.addAttribute("name", name);
        return "zconfig/content";
    }

    @RequestMapping("/index")
    public String index(String name, Model model){
        model.addAttribute("name", name);
        return "zconfig/index";
    }
}
