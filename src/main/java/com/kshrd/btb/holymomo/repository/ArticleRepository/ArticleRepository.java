package com.kshrd.btb.holymomo.repository.ArticleRepository;

import com.kshrd.btb.holymomo.repository.ArticleRepository.Provider.ArticleProvider;
import com.kshrd.btb.holymomo.repository.model.Article;
import com.kshrd.btb.holymomo.utility.Filter;
import com.kshrd.btb.holymomo.utility.Paging;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticleRepository {
    @InsertProvider(method = "add",type = ArticleProvider.class)
    boolean add(Article article);

    @DeleteProvider(method = "delete",type = ArticleProvider.class)
    boolean delete(int id);

    @DeleteProvider(method = "deleteAll",type = ArticleProvider.class)
    boolean deleteAll();

//    @Update("ALTER SEQUENCE tb_articles_id_seq RESTART WITH 1")
    @Delete("Delete from tb_articles")
    void clearSeqArticle();

//    @Update("ALTER SEQUENCE tb_categories_id_seq RESTART WITH 1")
    @Delete("Delete from tb_categories")
    void clearSeqCategory();

    @UpdateProvider(method = "update",type = ArticleProvider.class)
    boolean update(Article article);

    @SelectProvider(method = "findById",type = ArticleProvider.class)
    @Results({
            @Result(property = "id", column = "article_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "category.id", column = "category_id"),
            @Result(property = "category.title", column = "category_title"),
            @Result(property = "author", column = "author"),
            @Result(property = "description", column = "description"),
            @Result(property = "thumbnail", column = "thumbnail")
    })
    Article findOneById(int id);

    @SelectProvider(method = "findAll",type = ArticleProvider.class)
    @Results({
            @Result(property = "id", column = "article_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "category.id", column = "category_id"),
            @Result(property = "category.title", column = "category_title"),
            @Result(property = "author", column = "author"),
            @Result(property = "description", column = "description"),
            @Result(property = "thumbnail", column = "thumbnail")
    })
    List<Article> findAll();

    @SelectProvider(method = "viewPagination",type = ArticleProvider.class)
    @Results({
            @Result(property = "id", column = "article_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "category.id", column = "category_id"),
            @Result(property = "category.title", column = "category_title"),
            @Result(property = "author", column = "author"),
            @Result(property = "description", column = "description"),
            @Result(property = "thumbnail", column = "thumbnail")
    })
    List<Article> viewPagination(@Param("paging") Paging page,@Param("filter") Filter filter);

    @SelectProvider(method = "findTotalRecordForPagination", type = ArticleProvider.class)
    Integer findTotalRecordForPagination(@Param("filter") Filter filter);

    @Select("SELECT max(id) from tb_articles")
    Integer nextArticleId();
}
