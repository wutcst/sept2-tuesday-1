package cn.edu.whut.sept.zuul.service;

import cn.edu.whut.sept.zuul.domain.Result;
import cn.edu.whut.sept.zuul.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {

   Result login(User u);

   Result register(User u);

   Result changePasswd(User u);

   Result userList();

   Result changeUser(User u);

   Result deleteUser(User u);
}
