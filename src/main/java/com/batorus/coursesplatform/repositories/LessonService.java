package com.batorus.coursesplatform.repositories;

import com.batorus.coursesplatform.exception.ResourceNotFoundException;
import com.batorus.coursesplatform.model.Course;
import com.batorus.coursesplatform.model.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LessonService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    LessonRepository lessonRepository;


    public List<Lesson> getAllLessonsByCourseId(Long courseId) {

        return lessonRepository.findByCourseId(courseId);
    }


    public Lesson save(Long courseId, Lesson lesson) {

        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (!courseOptional.isPresent())
            throw new ResourceNotFoundException("Course with courseId: " + courseId + " not found!");

        lesson.setCourse(courseOptional.get());

        return lessonRepository.save(lesson);
    }

    public List<Lesson> saveAll(Long courseId, List<Lesson> lessonList) {

       return lessonList.stream().map(lesson->save(courseId, lesson)).collect(Collectors.toList());
    }



    public Lesson update(Long courseId, Long lessonId, Lesson lesson) {

        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (!courseOptional.isPresent())
            throw new ResourceNotFoundException("Course with courseId: " + courseId + " not found!");

        Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);

        if (!lessonOptional.isPresent())
            throw new ResourceNotFoundException("Lesson with lessonId: " + lessonId + " not found!");

        lessonOptional.get().setLessonName(lesson.getLessonName());
        lessonOptional.get().setLessonDescription(lesson.getLessonDescription());
        lessonOptional.get().setLessonContent(lesson.getLessonContent());

        return lessonRepository.save(lessonOptional.get());

    }


    public Lesson find(Long lessonId) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);

        if (!lessonOptional.isPresent())
            throw new ResourceNotFoundException("Lesson with lessonId: " + lessonId + " not found!");

        return lessonOptional.get();
    }

    public void delete(Long lessonId) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);

        if (!lessonOptional.isPresent())
            throw new ResourceNotFoundException("Lesson with lessonId: " + lessonId + " not found!");

        lessonRepository.delete(lessonOptional.get());
    }
}
