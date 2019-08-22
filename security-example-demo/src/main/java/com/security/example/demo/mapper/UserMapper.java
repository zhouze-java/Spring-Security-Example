/**
 *
 * Copyright 2017 ishangban.cn, Inc. All rights reserved.
 */
package com.security.example.demo.mapper;

import com.security.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table USER.
 * @author zhouze
 */
@Mapper
public interface UserMapper {
    
    /**
     * desc:插入表:USER.<br/>
     * descSql =  INSERT INTO USER 
     * @param entity entity
     * @return Long
     */
    Long insert(User entity);
    
    /**
     * desc:更新表:USER.<br/>
     * descSql =  UPDATE USER WHERE ID = #{id,jdbcType=BIGINT} AND PHONE_NO = #{phoneNo,jdbcType=CHAR}
     * @param entity entity
     * @return Long
     */
    Long update(User entity);
    
    /**
     * desc:根据主键删除数据:USER.<br/>
     * descSql =  DELETE FROM USER WHERE ID = #{id,jdbcType=BIGINT} AND PHONE_NO = #{phoneNo,jdbcType=CHAR}
     * @param id id
     * @param phoneNo phoneNo
     * @return Long
     */
    Long deleteByPrimary(@Param("id") Long id, @Param("phoneNo") String phoneNo);

    /**
     * desc:根据主键获取数据:USER.<br/>
     * descSql =  SELECT * FROM USER WHERE ID = #{id,jdbcType=BIGINT} AND PHONE_NO = #{phoneNo,jdbcType=CHAR}
     * @param id id
     * @param phoneNo phoneNo
     * @return User
     */
    User getByPrimary(@Param("id") Long id, @Param("phoneNo") String phoneNo);
    
    /**
     * desc:查询获取全部数据:USER.<br/>
     * descSql =  SELECT * FROM USER ORDER BY id DESC
     * @param entity entity
     * @return List<User>
     */
    List<User> getAll(User entity);
    
    /**
     * desc:查询获取第一条数据:USER.<br/>
     * descSql =  SELECT * FROM USER LIMIT 1
     * @param entity entity
     * @return User
     */
    User getOne(User entity);

    /**
     * 根据手机号获取用户信息
     * @param phoneNo
     * @return
     */
    User findUserByPhoneNo(@Param("phoneNo") String phoneNo);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User getUserById(@Param("id") Long id);
}
