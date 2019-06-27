package com.kshrd.btb.holymomo.repository.ArticleRepository;

import com.kshrd.btb.holymomo.repository.model.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository {
    @Insert("INSERT INTO tb_categories(title) VALUES(#{title})")
    void add(Category category);

    @Delete("DELETE FROM tb_categories WHERE id = #{id}")
    void delete(int id);

    @Delete("DELETE FROM tb_categories")
    void deleteAll();

    @Update("UPDATE tb_categories SET title = #{title} WHERE id = #{id}")
    void update(Category category);

    @Select("SELECT * FROM tb_categories where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title")
    })
    Category findById(int id);

    @Select("SELECT * FROM tb_categories ORDER BY id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title")
    })
    List<Category> findAll();
//    @Select("SELECT last_value from tb_categories_id_seq")
    @Select("SELECT max(id) from tb_categories")
    Integer nextCategoryId();
















//    void add(Category category);
//    void delete(int id);
//    void update(Category category);
//    Category findById(int id);
//    List<Category> findAll();
}
