package com.batorus.coursesplatform.repositories;

import com.batorus.coursesplatform.model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CategoryServiceTest {

    @Autowired
    CategoryService underTest;

    @AfterEach
    void tearDown() {

        underTest.deleteAll();
    }

    @Test
    void itChecksThatCategoryIsSaved() {
        // given
        Category category = new Category();

        category.setTitle("Title post 1");
        category.setDescription("Description post 1");

        // when
        Category resultCategory = underTest.save(category);

        // then
        assertThat(resultCategory.getTitle()).isEqualTo(category.getTitle());
        assertThat(resultCategory.getDescription()).isEqualTo(category.getDescription());
    }

    @Test
    void itChecksThatCategoryIsUpdated() {
        // given
        Category category = new Category();

        category.setTitle("Title post 1");
        category.setDescription("Description post 1");

        // when
        Category resultCategory = underTest.save(category);

        Category updateCategory = new Category();
        updateCategory.setTitle("Title post updated");
        updateCategory.setDescription("Description post updated");

        Category resultUpdateCategory = underTest.update(resultCategory.getId(), updateCategory);
        // then
        assertThat(resultUpdateCategory.getTitle()).isEqualTo(updateCategory.getTitle());
        assertThat(resultUpdateCategory.getDescription()).isEqualTo(updateCategory.getDescription());
    }

    @Test
    void itSavesAllCategories() {
        // given
        Category category1 = new Category();

        category1.setTitle("Title post 1");
        category1.setDescription("Description post 1");

        Category category2 = new Category();

        category2.setTitle("Title post 2");
        category2.setDescription("Description post 2");

        List<Category> listOfCategs = Arrays.asList(category1, category2);

        // when
        List<Category> resultCategories = underTest.saveAll(listOfCategs);

        // then
        assertThat(resultCategories.size()).isEqualTo(2);
    }

    @Test
    void itRetrievesAllCategories() {
        // given
        Category category1 = new Category();

        category1.setTitle("Title post 1");
        category1.setDescription("Description post 1");

        Category category2 = new Category();

        category2.setTitle("Title post 2");
        category2.setDescription("Description post 2");

        List<Category> listOfCategs = Arrays.asList(category1, category2);

        underTest.saveAll(listOfCategs);
        // when

        List<Category> resultCategories = underTest.getAllCategories();

        // then
        assertThat(resultCategories.size()).isEqualTo(2);
    }

    @Test
    void itRetrievesTheRightRecordById() {
        // given
        Category category = new Category();

        category.setTitle("Title post 1");
        category.setDescription("Description post 1");

        // when
        //this is already tested
        Category resultCategory = underTest.save(category);

        Category resultFindCategory = underTest.find(resultCategory.getId());
        // then
        assertThat(resultFindCategory.getTitle()).isEqualTo(category.getTitle());
        assertThat(resultFindCategory.getDescription()).isEqualTo(category.getDescription());
    }
}
