package com.batorus.coursesplatform.restcontrollers;

import com.batorus.coursesplatform.model.Lesson;
import com.batorus.coursesplatform.repositories.CourseService;
import com.batorus.coursesplatform.repositories.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses/{courseId}/lessons")
    public List<Lesson> getAllAction(@PathVariable(value = "courseId") Long courseId) {

        return lessonService.getAllLessonsByCourseId(courseId);
    }

    @PostMapping("/courses/{courseId}/lessons")
    public Lesson createAction(@PathVariable Long courseId,
                               @Valid @RequestBody Lesson lesson) {

        return lessonService.save(courseId, lesson);
    }

    @PostMapping("/courses/{courseId}/lessons/savebatch")
    public List<Lesson> saveAllAction(@PathVariable Long courseId,
                                      @RequestBody List<Lesson> lessonList) {

        return lessonService.saveAll(courseId, lessonList);
    }

    @PutMapping("/courses/{courseId}/lessons/{lessonId}")
    public Lesson updateAction(@PathVariable Long courseId,
                               @PathVariable Long lessonId,
                               @Valid @RequestBody Lesson lesson) {

        return lessonService.update(courseId, lessonId, lesson);
    }

    @GetMapping("/lessons/{lessonId}")
    public Lesson getOneAction(@PathVariable Long lessonId) {

        return lessonService.find(lessonId);
    }

    @DeleteMapping("/lessons/{lessonId}")
    public ResponseEntity<String> deleteAction(@PathVariable Long lessonId) {

        lessonService.delete(lessonId);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }
}
