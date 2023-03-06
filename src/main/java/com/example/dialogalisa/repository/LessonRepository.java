package com.example.dialogalisa.repository;

import com.example.dialogalisa.dto.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Transactional
    List<Lesson> findLessonsByDayOfWeak(int dayOfWeak);


    Optional<Lesson> findByTitleAndAndDayOfWeak(String title, int dayOfWeak);
}
