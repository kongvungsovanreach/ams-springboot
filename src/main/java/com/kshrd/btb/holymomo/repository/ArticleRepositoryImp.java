package com.kshrd.btb.holymomo.repository;

import com.kshrd.btb.holymomo.repository.ArticleRepository.ArticleRepository;
import com.kshrd.btb.holymomo.repository.model.Article;
import com.kshrd.btb.holymomo.service.ArticleService.CategoryService;
import com.kshrd.btb.holymomo.utility.Filter;
import com.kshrd.btb.holymomo.utility.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepositoryImp implements ArticleRepository {
    //Dependency injection block
    @Autowired
    private CategoryService categoryService;
    @Value("${server.file.path}")
    private String SERVER_PATH;

    //Field declaration block
    public List<Article> articles = new ArrayList<>();

    //Override method
    @Override
    public boolean add(Article article) {
            if(article.getThumbnail() == null){
                article.setThumbnail("placeholder.jpeg");
            }
            articles.add(article);
        return true;
    }

    @Override
    public boolean delete(int id) {
        for(Article article : articles){
            if (article.getId() == id){
                articles.remove(article);
                break;
            }
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        articles.clear();
        return true;
    }

    @Override
    public void clearSeqArticle(){}

    @Override
    public void clearSeqCategory() {}

    @Override
    public boolean update(Article article) {
        for(Article art : articles){
            if(art.getId() == article.getId()){
                art.setTitle(article.getTitle());
                art.setAuthor(article.getAuthor());
                art.setDescription(article.getDescription());
                art.setCategory(categoryService.findById(article.getCategory().getId()));
                if (!(article.getThumbnail() == null)){
                    art.setThumbnail(article.getThumbnail());
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Article findOneById(int id) {
        for(Article article : articles){
            if (article.getId() == id){
                return article;
            }
        }
        return null;
    }

    @Override
    public List<Article> findAll() {
        return articles;
    }

    @Override
    public List<Article> viewPagination(Paging page, Filter filter) {
        List tempList;
        if (articles.size()>10){
            if (((articles.size()/page.getLimit())) < page.getCurrentPage()){
                tempList = articles.subList((page.getCurrentPage()-1)*page.getLimit(),articles.size());
            }else {
                tempList = articles.subList((page.getCurrentPage()-1)*page.getLimit(),((page.getCurrentPage()-1)*page.getLimit())+ page.getLimit());
            }
        }else {
            return articles;
        }
        return tempList;
    }

    @Override
    public Integer findTotalRecordForPagination(Filter filter) {
        return null;
    }

    @Override
    public Integer nextArticleId() {
        if (articles.isEmpty()){
            return 0;
        }else {
            return articles.get(articles.size()-1).getId();
        }
    }
}
