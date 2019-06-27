package com.kshrd.btb.holymomo.service.ArticleService;

import com.kshrd.btb.holymomo.repository.model.Article;
import com.kshrd.btb.holymomo.utility.Filter;
import com.kshrd.btb.holymomo.utility.Paging;
import java.util.List;

public interface ArticleService {
    boolean add(Article article);
    boolean delete(int id);
    boolean deleteAll();
    boolean update(Article article);
    Article findOneById(int id);
    List<Article> findAll();
    List<Article> viewPagination(Paging paging, Filter filter);
    Integer findTotalRecordForPagination(Filter filter);
    Integer nextArticleId();
}
