package com.security.example.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.security.example.demo.mapper.UserMapper;
import com.security.example.demo.model.User;
import com.security.example.demo.model.vo.PageInfoVO;
import com.security.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 周泽
 * @date Create in 9:57 2019/8/22
 * @Description {@link com.security.example.demo.service.UserService} 实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Long insert(User entity){
        return userMapper.insert(entity);
    }

    @Override
    public Long update(User entity){
        return userMapper.update(entity);
    }

    @Override
    public Long deleteByPrimary(Long id,String phoneNo){
        return userMapper.deleteByPrimary(id, phoneNo);
    }

    @Override
    public User getByPrimary(Long id,String phoneNo){
        return userMapper.getByPrimary(id, phoneNo);
    }

    @Override
    public List<User> getAll(User entity){
        return userMapper.getAll(entity);
    }

    @Override
    public User getOne(User entity){
        return userMapper.getOne(entity);
    }

    @Override
    public PageInfoVO<User> getAllPage(int pageNum,
                                       int pageSize, User entity) {
        PageInfoVO<User> pageInfoVO = new PageInfoVO<User>();
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = this.getAll(entity);
        PageInfo<User> page = new PageInfo<User>(list);
        pageInfoVO.setList(page.getList());
        pageInfoVO.setPageNum(page.getPageNum());
        pageInfoVO.setPages(page.getPages());
        pageInfoVO.setPageSize(page.getPageSize());
        pageInfoVO.setTotal(page.getTotal());
        return pageInfoVO;
    }

    @Override
    public User findUserByPhoneNo(String phoneNo) {
        return userMapper.findUserByPhoneNo(phoneNo);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

}
