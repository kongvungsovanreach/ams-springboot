package com.kshrd.btb.holymomo.controller;


import com.kshrd.btb.holymomo.service.ArticleService.ArticleService;
import com.kshrd.btb.holymomo.utility.Filter;
import com.kshrd.btb.holymomo.utility.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ajax")
public class AjaxArticleController {
    @Autowired
    private ArticleService articleService;
    @GetMapping("/table")
    public String getTable(ModelMap modelMap, Paging paging, Filter filter){
        System.out.println(paging);
        modelMap.addAttribute("articles", articleService.viewPagination(paging,filter));
        return "ajax/ajax-table";
    }

    @GetMapping("/index")
    public String indexAjax(){

        return "ajax/index";
    }
}
