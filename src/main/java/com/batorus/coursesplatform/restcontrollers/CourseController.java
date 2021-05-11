package com.batorus.coursesplatform.restcontrollers;

import com.batorus.coursesplatform.model.Course;
import com.batorus.coursesplatform.repositories.CategoryService;
import com.batorus.coursesplatform.repositories.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CourseController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CourseService courseService;


    @GetMapping("/categories/{categoryId}/courses")
    public List<Course> getAllCoursesByCategoryIdAction(@PathVariable(value = "categoryId") Long categoryId) {

        return courseService.getAllCoursesByCategoryId(categoryId);
    }

    @PostMapping("/categories/{categoryId}/courses")
    public Course createCourseAction(@PathVariable Long categoryId,
                                     @Valid @RequestBody Course course) {

        return courseService.save(categoryId, course);
    }


    @PutMapping("/categories/{categoryId}/courses/{courseId}")
    public Course updateCourseAction(@PathVariable Long categoryId,
                                     @PathVariable Long courseId,
                                     @Valid @RequestBody Course course) {

        return courseService.update(categoryId, courseId, course);
    }

    @GetMapping("/courses/{courseId}")
    public Course getOneCourseAction(@PathVariable Long courseId) {

        return courseService.find(courseId);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<String> deleteCourseAction(@PathVariable Long courseId) {

        courseService.delete(courseId);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }
}
