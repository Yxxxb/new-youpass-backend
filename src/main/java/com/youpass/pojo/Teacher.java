package com.youpass.pojo;

import com.youpass.pojo.pk.TeacherId;
import org.springframework.jdbc.core.SqlReturnType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Teacher implements Serializable {
    /**
     * Teacher里id自动生成一般不提供外部set方法
     * courseSet和questionSet是查数据库获得，不提供外部set方法
     * 其余信息有get和set方法
     */

    @EmbeddedId
    private TeacherId id;

    @Column(length = 16, name = "Name")
    private String name;
    @Column(length = 32, name = "Password")
    private String password;
    @Column(length = 32, name = "Email")
    private String email;
    @Column(length = 128 ,name="Location")
    private String location;

    //放弃关系的维护
    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Course> courseSet = new HashSet<>();

    //放弃关系的维护
    // @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    // private Set<Question> questionSet = new HashSet<>();


    public Teacher() {
    }
}
