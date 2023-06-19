package cn.edu.whut.sept.zuul.service.impl;


import cn.edu.whut.sept.zuul.dao.UserDao;
import cn.edu.whut.sept.zuul.domain.Result;
import cn.edu.whut.sept.zuul.domain.User;
import cn.edu.whut.sept.zuul.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public Result login(User u) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.and(i->i.eq(User::getUserName,u.getUserName()).eq(User::getPasswd,u.getPasswd()));
        User one = this.getOne(queryWrapper);
        if(one==null){
            return Result.fail("用户名或密码错误");
        }else{
            HashMap<String, String> map = new HashMap<>();
            map.put("token",generateToken());
            return Result.success(map);
        }
    }

    @Override
    public Result register(User u) {
            //先查找用户名是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,u.getUserName());
        User one = this.getOne(queryWrapper);
        if(one==null){
            //不存在，将该用户插入数据库，注册成功
            boolean save = this.save(u);
            if(save){
                return Result.success(null);
            }else{
                return Result.fail("系统错误");
            }
        }else{
            //存在则提示用户名不可用，注册失败
            return Result.fail("用户名不可用");
        }
    }

    @Override
    public Result changePasswd(User u) {
        //根据用户名查找用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,u.getUserName());
        User one = this.getOne(queryWrapper);
        if(one==null){
            return Result.fail("用户不存在");
        }else{
            one.setPasswd(u.getPasswd());
            boolean update = this.updateById(one);
            if(update){
                return Result.success(null);
            }else{
                return Result.fail("系统错误");
            }
        }
    }

    @Override
    public Result userList() {
        List<User> list = this.list();
        return Result.success(list);
    }

    @Override
    public Result changeUser(User u) {
        //根据用户名查找用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,u.getUserName());
        User one = this.getOne(queryWrapper);
        if(one==null){
            return Result.fail("用户不存在");
        }else{
            one.setEmail(u.getEmail());
            boolean update = this.updateById(one);
            if(update){
                return Result.success(null);
            }else{
                return Result.fail("系统错误");
            }
        }
    }

    @Override
    public Result deleteUser(User u) {
        //根据用户名查找用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,u.getUserName());
        User one = this.getOne(queryWrapper);
        if(one==null){
            return Result.fail("用户不存在");
        }else{
            boolean delete = this.removeById(one);
            if(delete){
                return Result.success(null);
            }else{
                return Result.fail("系统错误");
            }
        }
    }

    private String generateToken(){
        String token = "Bear";
        int num = (int)(Math.random()*501);
        return token+num;
    }
}
