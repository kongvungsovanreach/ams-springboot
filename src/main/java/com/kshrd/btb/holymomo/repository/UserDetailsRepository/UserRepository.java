package com.kshrd.btb.holymomo.repository.UserDetailsRepository;

import com.kshrd.btb.holymomo.repository.model.Role;
import com.kshrd.btb.holymomo.repository.model.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  {
    @Select("SELECT * FROM tb_users where username = #{username}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "username",column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "gender",column = "gender"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id", many = @Many(select = "findRolesByUserId"))
    })
    User findByUsername(String username);

    @Select("SELECT * FROM tb_roles as r INNER JOIN tb_user_roles as u on u.role_id = r.id WHERE u.user_id = #{id}")
    List<Role> findRolesByUserId(int id);
}
