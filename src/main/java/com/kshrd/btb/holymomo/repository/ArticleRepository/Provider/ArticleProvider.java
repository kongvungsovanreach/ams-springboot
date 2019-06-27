package com.kshrd.btb.holymomo.repository.ArticleRepository.Provider;

import com.kshrd.btb.holymomo.repository.model.Article;
import com.kshrd.btb.holymomo.utility.Filter;
import com.kshrd.btb.holymomo.utility.Paging;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class ArticleProvider {
    public String add(Article article){
        return new SQL(){{
            INSERT_INTO("tb_articles");
            if (article.getThumbnail() != null){
                VALUES("title,category_id,author,description,thumbnail","#{title},#{category.id}, #{author}, #{description}, #{thumbnail}");
            }else {
                VALUES("title,category_id,author,description,thumbnail","#{title},#{category.id}, #{author}, #{description}, 'democover.png'");
            }
        }}.toString();
    }

    public String delete(Integer id){
        return new SQL(){{
            DELETE_FROM("tb_articles");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String deleteAll(){
        return new SQL(){{
            DELETE_FROM("tb_articles");
        }}.toString();
    }

    public String update(){
        return new SQL(){{
            UPDATE("tb_articles");
            SET("title=#{title}, category_id=#{category.id}, author=#{author},description=#{description},thumbnail=#{thumbnail}");
            WHERE("id = #{id}");

        }}.toString();
    }

    public String findById(Integer id){
        return new SQL(){{
            SELECT("a.id as article_id,a.title,a.category_id,a.author,a.description,a.thumbnail,c.title as category_title");
            FROM("tb_articles as a");
            INNER_JOIN("tb_categories as c ON a.category_id = c.id");
            WHERE("a.id = #{id}");
        }}.toString();
    }

    public String findAll(){
        return new SQL(){{
            SELECT("a.id as article_id,a.title,a.category_id,a.author,a.description,a.thumbnail,c.title as category_title");
            FROM("tb_articles as a");
            INNER_JOIN("tb_categories as c ON a.category_id = c.id");
        }}.toString();
    }

    public String viewPagination(@Param("paging") Paging paging, @Param("filter") Filter filter){
        return new SQL(){{
            SELECT("a.id as article_id,a.title,a.category_id,a.author,a.description,a.thumbnail,c.title as category_title");
            FROM("tb_articles as a");
            INNER_JOIN("tb_categories as c ON a.category_id = c.id");
            if (filter.getTitle() != null){
                WHERE("a.title ilike '%'||#{filter.title}||'%'");
            }
            if (filter.getCateId() != 0){
                WHERE("a.category_id = #{filter.cateId}");
            }
            ORDER_BY(" a.id limit #{paging.limit} OFFSET (#{paging.currentPage}-1)*#{paging.limit}");
        }}.toString();
    }
    public String findTotalRecordForPagination(@Param("filter") Filter filter){
        return new SQL(){{
            SELECT("COUNT(*)");
            FROM("tb_articles as a");
            INNER_JOIN("tb_categories as c ON a.category_id = c.id");
            if (filter.getTitle() != null){
                WHERE("a.title ilike '%'||#{filter.title}||'%'");
            }
            if (filter.getCateId() != 0){
                WHERE("a.category_id = #{filter.cateId}");
            }
        }}.toString();
    }
}
