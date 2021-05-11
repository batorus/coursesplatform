package com.batorus.coursesplatform.restcontrollers;

import com.batorus.coursesplatform.model.Category;
import com.batorus.coursesplatform.repositories.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(path = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> listAction() {

        return categoryService.getAllCategories();
    }

    @PostMapping(path="/categories", consumes =  {
                                            MediaType.APPLICATION_JSON_VALUE
                                    },
                                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Category createAction(@Valid @RequestBody Category category) {

        return categoryService.save(category);
    }


    @PutMapping("/categories/{categoryId}")
    public Category updatePost(@PathVariable Long categoryId, @Valid @RequestBody Category category) {

        return categoryService.update(categoryId, category);
    }

    @GetMapping("/categories/{categoryId}")
    public Category getCategoryAction(@PathVariable Long categoryId) {

        return categoryService.find(categoryId);
    }

    @PostMapping("/categories/savebatch")
    public List<Category> saveAllCategoriesAction(@RequestBody List<Category> categoryList) {

        return categoryService.saveAll(categoryList);
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<String> deleteAction(@PathVariable Long categoryId) {

        categoryService.delete(categoryId);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }
}
