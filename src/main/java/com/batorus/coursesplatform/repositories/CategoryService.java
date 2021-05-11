package com.batorus.coursesplatform.repositories;

import com.batorus.coursesplatform.exception.ResourceNotFoundException;
import com.batorus.coursesplatform.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public Page<Category> getAllCategories(Pageable pageable) {

        return categoryRepository.findAll(pageable);
    }

    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Long categoryId, Category category) {

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (!categoryOptional.isPresent())
            throw new ResourceNotFoundException("Category with categoryId: " + categoryId + " not found!");

        Category cat = categoryOptional.get();
        cat.setTitle(category.getTitle());
        cat.setDescription(category.getDescription());

        return categoryRepository.save(cat);
    }

    public Category find(Long categoryId) {
        //return postRepository.findById(postId).get();

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (!categoryOptional.isPresent())
            throw new ResourceNotFoundException("Category with categoryId: " + categoryId + " not found!");

        return categoryOptional.get();
    }

    public List<Category> saveAll(List<Category> categoryList) {
        return categoryRepository.saveAll(categoryList);
    }


    public void delete(Long categoryId) {

//            return postRepository.findById(postId).map(post -> {
//                postRepository.delete(post);
//                return ResponseEntity.ok().build();
//            }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (!categoryOptional.isPresent())
            throw new ResourceNotFoundException("Category with categoryId: " + categoryId + " not found!");

        categoryRepository.delete(categoryOptional.get());

    }

    public void deleteAll(){
        categoryRepository.deleteAll();
    }
}
