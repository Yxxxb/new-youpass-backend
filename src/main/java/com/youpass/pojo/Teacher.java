package com.youpass.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youpass.pojo.pk.TeacherId;
import org.springframework.data.annotation.Transient;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.transaction.annotation.Transactional;

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
    @JsonIgnore
    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Course> courseSet = new HashSet<>();

    public Teacher() {
        id = new TeacherId();
    }

    public static Teacher.Builder Builder(){
        return new Builder();
    }

    public static class Builder{
        private TeacherId id;
        private String name;
        private String password;
        private String email;
        private String location;

        Builder(){
            id = new TeacherId();
        }

        public Builder setId(TeacherId id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;

        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Teacher build(){
            var teacher = new Teacher();
            teacher.id = id;
            teacher.name = name;
            teacher.password = password;
            teacher.email = email;
            teacher.location = location;
            return teacher;
        }
    }

    public TeacherId getId() {
        return id;
    }

    public void setId(TeacherId id) {
        this.id = id;
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

    @Transient
    public Set<Course> getCourseSet() {
        return courseSet;
    }
    public void setCourseSet(Set<Course> courseSet) {
        this.courseSet = courseSet;
    }

}
