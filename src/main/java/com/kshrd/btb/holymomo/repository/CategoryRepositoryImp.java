package com.kshrd.btb.holymomo.repository;

import com.kshrd.btb.holymomo.repository.ArticleRepository.CategoryRepository;
import com.kshrd.btb.holymomo.repository.model.Category;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepositoryImp implements CategoryRepository {
    List<Category> categories = new ArrayList<>();

    @Override
    public void add(Category category) {
        categories.add(category);
    }

    @Override
    public void delete(int id) {
        for(Category category: categories){
            if (category.getId() == id){
                categories.remove(category);
                return;
            }
        }
    }

    @Override
    public void deleteAll() {
        categories.clear();
    }

    @Override
    public void update(Category category) {
        for(Category cate : categories){
            if (cate.getId() == category.getId()){
                cate.setTitle(category.getTitle());
                return;
            }
        }
    }

    @Override
    public Category findById(int id) {
        for(Category category: categories){
            if (category.getId() == id){
                return category;
            }
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        return categories;
    }

    @Override
    public Integer nextCategoryId() {
        if (categories.isEmpty()){
            return 0;
        }else {
            return categories.get(categories.size()-1).getId();
        }
    }
}
