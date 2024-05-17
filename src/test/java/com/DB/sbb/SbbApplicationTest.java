package com.DB.sbb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(Extension.class)
public class SbbApplicationTest {

    @Test
    @ResponseBody
    public void test() {
        System.out.println("HELLO WORLD");
    }
}