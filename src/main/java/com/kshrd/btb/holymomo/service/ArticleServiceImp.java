package com.kshrd.btb.holymomo.service;

import com.kshrd.btb.holymomo.repository.ArticleRepository.ArticleRepository;
import com.kshrd.btb.holymomo.repository.model.Article;
import com.kshrd.btb.holymomo.service.ArticleService.ArticleService;
import com.kshrd.btb.holymomo.utility.Filter;
import com.kshrd.btb.holymomo.utility.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleServiceImp implements ArticleService {
    //Dependency injection block
    @Autowired
    private ArticleRepository articleRepository;
    public static int page;

    //Override method
    @Override
    public boolean add(Article article) {
        return articleRepository.add(article);
    }

    @Override
    public boolean delete(int id) {
        return articleRepository.delete(id);
    }

    @Override
    public boolean deleteAll() {
        articleRepository.clearSeqArticle();
        articleRepository.clearSeqCategory();
        return articleRepository.deleteAll();
    }

    @Override
    public boolean update(Article article) {
        if (article.getThumbnail() == null){
            article.setThumbnail(findOneById(article.getId()).getThumbnail());
        }
        return articleRepository.update(article);
    }

    @Override
    public Article findOneById(int id) {
        return articleRepository.findOneById(id);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> viewPagination(Paging paging, Filter filter) {
        page = paging.getCurrentPage();
        return articleRepository.viewPagination(paging, filter);
    }

    @Override
    public Integer findTotalRecordForPagination(Filter filter) {
        return articleRepository.findTotalRecordForPagination(filter);
    }

    @Override
    public Integer nextArticleId() {
        if (articleRepository.nextArticleId() == null && findAll().isEmpty()){
            return 1;
        }else {
            return articleRepository.nextArticleId() + 1;
        }
    }
}
