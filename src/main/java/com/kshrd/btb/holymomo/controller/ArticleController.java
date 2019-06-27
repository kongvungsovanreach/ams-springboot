package com.kshrd.btb.holymomo.controller;

import com.kshrd.btb.holymomo.repository.model.Article;
import com.kshrd.btb.holymomo.repository.model.Category;
import com.kshrd.btb.holymomo.service.ArticleService.ArticleService;
import com.kshrd.btb.holymomo.service.ArticleService.CategoryService;
import com.kshrd.btb.holymomo.service.ArticleService.FileUploadService;
import com.kshrd.btb.holymomo.service.ArticleServiceImp;
import com.kshrd.btb.holymomo.utility.Filter;
import com.kshrd.btb.holymomo.utility.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.Random;

@Controller
public class ArticleController {
    //Dependency injection block
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FileUploadService fileUploadService;
    private Filter filter;

    //Homepage controller
    @GetMapping(value = {"/","/index","/home"})
    public String homePage(ModelMap modelMap, @ModelAttribute Filter filter){
        Paging paging = new Paging();
        paging.setCurrentPage(1);
        this.filter = filter;
        paging.setTotalRecord(articleService.findTotalRecordForPagination(filter));
        modelMap.addAttribute("articles",articleService.viewPagination(paging, filter));
        modelMap.addAttribute("articleCount",articleService.findTotalRecordForPagination(filter));
        modelMap.addAttribute("categories", categoryService.findAll());
        modelMap.addAttribute("paging",paging);
        modelMap.addAttribute("filter", this.filter);
        return "index";
    }

    //Add form article controller
    @GetMapping("/add")
    public String addForm(ModelMap modelMap){
        Article article = new Article();
        article.setId(articleService.nextArticleId());
        modelMap.addAttribute("article", article);
        modelMap.addAttribute("categories",categoryService.findAll());
        modelMap.addAttribute("page", ArticleServiceImp.page);
        return "add";
    }

    //Add form article controller test pagination
    @GetMapping("/addloop")
    public String addForm(@RequestParam int loop, ModelMap modelMap){
        categoryService.add(new Category(1, "Romanticism"));
        categoryService.add(new Category(2, "Novelism"));
        categoryService.add(new Category(3, "Dramalism"));
        for (int i=0;i<loop;i++){
            Random rand = new Random();
            int randomNum = 8 + rand.nextInt((10 - 8) + 1);
            System.out.println(randomNum);
            Article article = new Article(articleService.nextArticleId(),"Love is in the air", categoryService.findById(randomNum),"Kong vungsovanreach","This is the most popular book that was just kidding","democover.png");
            articleService.add(article);
            System.out.println("sdfsd");
        }
        Article article = new Article();
        article.setId(articleService.nextArticleId());
        modelMap.addAttribute("article", article);
        modelMap.addAttribute("categories",categoryService.findAll());
        return "redirect:/";
    }

    //Add article form action controller
    @PostMapping("/add")
    public String addArticleAction(@Valid @ModelAttribute Article article, BindingResult bindingResult,@RequestParam("image") MultipartFile file, ModelMap modelMap){
        if (bindingResult.hasErrors()){
            if (!bindingResult.hasFieldErrors("category.title")){
                modelMap.addAttribute("categories", categoryService.findAll());
                return "add";
            }
        }
        if (!(file.getOriginalFilename().isEmpty())){
            article.setThumbnail(fileUploadService.uploadFile(file));
        }
        article.setCategory(categoryService.findById(article.getCategory().getId()));
        articleService.add(article);
        return "redirect:/";
    }

    //View one article controller
    @GetMapping("/view/{id}")
    public String viewOne(@PathVariable int id, ModelMap modelMap){
        modelMap.addAttribute("article",articleService.findOneById(id));
        modelMap.addAttribute("page", ArticleServiceImp.page);
        return "view";
    }

    //Update form article controller
    @GetMapping("/update/{id}")
    public String updateForm(ModelMap modelMap,
                             @PathVariable int id){
        modelMap.addAttribute("article", articleService.findOneById(id));
        modelMap.addAttribute("org.springframework.validation.BindingResult.article",modelMap.get("errorObject"));
        modelMap.addAttribute("categories",categoryService.findAll());
        modelMap.addAttribute("page", ArticleServiceImp.page);
        return "update";
    }

    //Update article action
    @PostMapping("/update")
    public String updateAction(@Valid @ModelAttribute Article article,
                               BindingResult bindingResult,
                               RedirectAttributes attributes,
                               @RequestParam("image") MultipartFile file,
                               ModelMap modelMap){
        if (bindingResult.hasErrors()){
            if (!bindingResult.hasFieldErrors("category.title")){
                attributes.addFlashAttribute("errorObject",bindingResult);
                modelMap.addAttribute("categories",categoryService.findAll());
                return "redirect:/update/"+article.getId();
            }
        }
        if (!(file.getOriginalFilename().isEmpty())){
            article.setThumbnail(fileUploadService.uploadFile(file));
        }
        articleService.update(article);
        return "redirect:/viewall?page="+ ArticleServiceImp.page;
    }

    //Delete article action
    @GetMapping("/delete/{id}")
    public String deleteArticle(@PathVariable int id){
        articleService.delete(id);
        return "redirect:/viewall?page="+ ArticleServiceImp.page;
    }
    @GetMapping("/delete-all")
    public String deleteAllArticle(){
        articleService.deleteAll();
        categoryService.deleteAll();
        return "redirect:/";
    }

    //Pagination controller
    @GetMapping("/viewall")
    public String viewPagination(ModelMap modelMap,@RequestParam(required = false) Integer page, @ModelAttribute Filter filter){
        Paging paging = new Paging();
        paging.setCurrentPage((page==null)?1:page);
        if (filter.getTitle()!=null || filter.getCateId()!=0) this.filter = filter;
        paging.setTotalRecord(articleService.findTotalRecordForPagination(this.filter));
        modelMap.addAttribute("articles",articleService.viewPagination(paging,this.filter));
        modelMap.addAttribute("articleCount",articleService.findTotalRecordForPagination(this.filter));
        modelMap.addAttribute("categories", categoryService.findAll());
        modelMap.addAttribute("paging", paging);
        modelMap.addAttribute("filter", this.filter);
        return "index";
    }
}
