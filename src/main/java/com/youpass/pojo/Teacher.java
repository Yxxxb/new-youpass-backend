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
    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Question> questionSet = new HashSet<>();


    public Teacher() {
    }

    public Teacher(TeacherId id, String name, String password, String email, String location, Set<Course> courseSet, Set<Question> questionSet) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.location = location;
        this.courseSet = courseSet;
        this.questionSet = questionSet;
    }

    public Teacher(TeacherId id, String name, String password, String email, String location) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.location = location;
    }

    public Teacher(TeacherId id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public TeacherId getId() {
        return id;
    }

    public Set<Course> getCourseSet() {
        return courseSet;
    }

    public Set<Question> getQuestionSet() {
        return questionSet;
    }
}
