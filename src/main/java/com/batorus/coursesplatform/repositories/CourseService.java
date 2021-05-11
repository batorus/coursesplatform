package com.batorus.coursesplatform.repositories;

import com.batorus.coursesplatform.exception.ResourceNotFoundException;
import com.batorus.coursesplatform.model.Category;
import com.batorus.coursesplatform.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
   CourseRepository courseRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Course> getAllCoursesByCategoryId(Long categoryId) {

        return courseRepository.findByCategoryId(categoryId);
    }

    public Course save(Long categoryId, Course course) {

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (!categoryOptional.isPresent())
            throw new ResourceNotFoundException("Category with categoryId: " + categoryId + " not found!");

        course.setCategory(categoryOptional.get());

        return courseRepository.save(course);
    }

    public Course update(Long categoryId, Long courseId, Course course) {

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (!categoryOptional.isPresent())
            throw new ResourceNotFoundException("Category with categoryId: " + categoryId + " not found!");

        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (!courseOptional.isPresent())
            throw new ResourceNotFoundException("Course with courseId: " + courseId + " not found!");

        courseOptional.get().setCourseName(course.getCourseName());
        courseOptional.get().setCourseCode(course.getCourseCode());
        courseOptional.get().setCoursePrice(course.getCoursePrice());
        courseOptional.get().setCourseDescription(course.getCourseDescription());

        return courseRepository.save(courseOptional.get());

    }

    public Course find(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (!courseOptional.isPresent())
            throw new ResourceNotFoundException("Course with courseId: " + courseId + " not found!");

        return courseOptional.get();
    }

    public void delete(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (!courseOptional.isPresent())
            throw new ResourceNotFoundException("Course with courseId: " + courseId + " not found!");

        courseRepository.delete(courseOptional.get());
    }

}
