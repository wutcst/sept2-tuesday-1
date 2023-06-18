package cn.edu.whut.sept.zuul.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;


@Data
public class User implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    @TableField("username")
    private String userName;
    @TableField("passwd")
    private String passwd;
    @TableField("email")
    private String email;
}
