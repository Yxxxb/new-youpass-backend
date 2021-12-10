package com.youpass.config;

import com.youpass.dao.TestRepository;
import com.youpass.pojo.Teacher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TestConfig {
    // @Bean
    // CommandLineRunner commandLineRunner(
    // TestRepository testRepository
    // ){
    // return args -> {
    // Teacher teacher =new Teacher();
    // testRepository.saveAll(List.of(teacher));
    // };
    // }
}
