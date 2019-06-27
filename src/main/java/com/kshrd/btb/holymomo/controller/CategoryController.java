package com.kshrd.btb.holymomo.controller;

import com.kshrd.btb.holymomo.repository.CategoryRepositoryImp;
import com.kshrd.btb.holymomo.repository.model.Category;
import com.kshrd.btb.holymomo.service.ArticleService.CategoryService;
import com.kshrd.btb.holymomo.service.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class CategoryController {
    //Dependency injection block
    @Autowired
    private CategoryService categoryService;

    //Controller mapping block
    @GetMapping("/category")
    public String getCategories(ModelMap modelMap){
        modelMap.addAttribute("categories",categoryService.findAll());
        return "category/index";
    }

    //Add category return form
    @GetMapping("/category/add")
    public String addCategory(ModelMap modelMap){
        Category category = new Category();
        category.setId(categoryService.nextCategoryId());
        modelMap.addAttribute("category", category);
        return "category/add";
    }

    //Add category action
    @PostMapping("/category/add")
    public String addCategoryAction(@Valid @ModelAttribute Category category, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "category/add";
        }
        categoryService.add(category);
        return "redirect:/category";
    }

    //Update category form
    @GetMapping("/category/update/{id}")
    public String updateForm(@PathVariable int id, ModelMap modelMap){
        modelMap.addAttribute("category", categoryService.findById(id));
        return "/category/update";
    }

    //Update category action
    @PostMapping("/category/update")
    public String updateAction(@Valid @ModelAttribute Category category, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "category/update";
        }
        categoryService.update(category);
        return "redirect:/category";
    }

    //Delete category action
    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable int id){
            categoryService.delete(id);
        return "redirect:/category";
    }
}
