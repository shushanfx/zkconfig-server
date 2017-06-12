package com.shushanfx.zkconfig.server.controller;

import com.shushanfx.zkconfig.server.bean.ResultInfo;
import com.shushanfx.zkconfig.server.zookeeper.ZNode;
import com.shushanfx.zkconfig.server.zookeeper.ZNodeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by shushanfx on 17/六月/11.
 */
@RequestMapping("/znode")
@Controller
public class ZkController {
    @Autowired
    private ZNodeClient client = null;

    @ResponseBody
    @RequestMapping("/list")
    public ResultInfo<List<ZNode>> list(String name){
        ResultInfo<List<ZNode>> resultInfo = ResultInfo.newSuccess();
        resultInfo.setData(client.getList(name));
        return resultInfo;
    }

    @RequestMapping("/save/info")
    @ResponseBody
    public ResultInfo saveInfo(String name, String description, @RequestParam(defaultValue = "shushanfx") String username){
        client.saveInfo(name, description, username);
        return ResultInfo.newSuccess();
    }

    @RequestMapping("/save/content")
    @ResponseBody
    public ResultInfo saveContent(String name, String content, @RequestParam(defaultValue = "shushanfx") String username){
        client.saveContent(name, content, username);
        return ResultInfo.newSuccess();
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
        return "znode/edit";
    }

    @RequestMapping("/edit/info/{name}")
    public String editInfo(@PathVariable String name, Model model){
        ZNode node = client.getInfo(name);
        model.addAttribute("edit", "edit");
        model.addAttribute("node", node);
        model.addAttribute("name", name);
        return "znode/edit";
    }

    @RequestMapping("/edit/content/{name}")
    public String editContent(@PathVariable String name, Model model){
        ZNode node = client.getContent(name);
        model.addAttribute("node", node);
        model.addAttribute("name", name);
        return "znode/content";
    }

    @RequestMapping("/index")
    public String index(String name, Model model){
        model.addAttribute("name", name);
        return "znode/index";
    }
}
