package cn.edu.whut.sept.zuul.dao;

import cn.edu.whut.sept.zuul.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
