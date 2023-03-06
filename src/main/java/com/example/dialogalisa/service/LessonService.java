package com.example.dialogalisa.service;

import com.example.dialogalisa.dto.model.Lesson;
import com.example.dialogalisa.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LessonService {

    @Autowired
    LessonRepository lessonRepository;

    public List<Lesson> getByDayOfWeak (int day) {
        List<Lesson> lessons = lessonRepository.findLessonsByDayOfWeak(day);
        for (Lesson l : lessons) {
            System.out.println(l);
        }
        return lessons;
    }

    public  Map<Integer, LinkedList<Lesson>> getByWeak() {
        Map<Integer, LinkedList<Lesson>> lessonMap = new HashMap<>();

        for (int i = 1; i < 6; i ++) {
            List<Lesson> lessons = getByDayOfWeak(i);
            LinkedList<Lesson> linkedListLesson = new LinkedList<>(
                    lessons.stream().sorted(Comparator.comparing(Lesson::getNumber)).collect(Collectors.toList()));
            lessonMap.put(i,linkedListLesson);
        }
        return lessonMap;
    }


    public List<Lesson> getLessonWithExampleByDayOfWeak(int dayOfWeak) {
        List<Lesson> lessons = lessonRepository.findLessonsByDayOfWeak(dayOfWeak);
        List<Lesson> withEx = new ArrayList<>();

        for (Lesson l : lessons) {
            if (l.getExampleRecommended() != null && !l.getExampleRecommended().isEmpty()){
                withEx.add(l);
            }

        }
        return withEx;
    }




}
