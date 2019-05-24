package com.taotao.controller;

import com.taotao.result.EasyUICatTree;
import com.taotao.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/item/cat")
public class TbItemCatController {
    @Autowired
    private TbItemCatService tbItemCatService;
    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUICatTree> getTbItemCatTree(@RequestParam(value = "id",defaultValue = "0") long id){
        return tbItemCatService.getTbItemCatTree(id);
    }
}
