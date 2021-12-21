package com.youpass;

import com.youpass.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NewYoupassBackendApplicationTests {

    @Autowired
    private AccountService accountService;

    @Test
    void contextLoads() {
        System.out.println(accountService.getAllInfo(1950000L));
    }

}
