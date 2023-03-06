package com.example.dialogalisa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//@DirtiesContext
class LessonServiceTest {

   // @Autowired
    LessonService lessonService;


    //@Test
    public void getByDayTest() {
        lessonService.getByDayOfWeak(2);
    }
}