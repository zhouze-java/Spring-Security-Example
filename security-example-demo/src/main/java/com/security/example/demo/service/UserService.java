package com.security.example.demo.service;

import com.security.example.demo.model.User;
import com.security.example.demo.model.vo.PageInfoVO;

import java.util.List;

/**
 * @author 周泽
 * @date Create in 9:51 2019/8/22
 * @Description 用户Service
 */
public interface UserService {
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
    Long deleteByPrimary(Long id,String phoneNo);
    /**
     * desc:根据主键获取数据:USER.<br/>
     * descSql =  SELECT * FROM USER WHERE ID = #{id,jdbcType=BIGINT} AND PHONE_NO = #{phoneNo,jdbcType=CHAR}
     * @param id id
     * @param phoneNo phoneNo
     * @return User
     */
    User getByPrimary(Long id,String phoneNo);
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
     * desc:分页查询全部
     *
     * @param pageNum  页码
     * @param pageSize 条数
     * @param entity     entity
     * @return PageInfoVO<User>
     */
    PageInfoVO<User> getAllPage(int pageNum,
                                       int pageSize, User entity);

    /**
     * 根据用户手机号去获取用户
     * @param phoneNo
     * @return
     */
    User findUserByPhoneNo(String phoneNo);

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    User getUserById(Long id);
}
