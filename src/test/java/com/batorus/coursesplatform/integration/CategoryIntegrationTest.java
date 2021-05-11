package com.batorus.coursesplatform.integration;


import com.batorus.coursesplatform.model.Category;
import com.batorus.coursesplatform.repositories.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CategoryIntegrationTest {

    public static final String ERROR_FOR_TITLE_EMPTY = "title must be between 2 and 100 characters long";
    public static final String ERROR_FOR_DESCRIPTION_EMPTY = "description must be between 2 and 250 characters long";

    public static final String ERROR_FOR_TITLE_NULL = "title may not be null!";
    public static final String ERROR_FOR_DESCRIPTION_NULL = "description may not be null!";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryService categoryService;


    @AfterEach
    public void resetDb() {
        categoryService.deleteAll();
    }

    @Test
    void ItCanSaveNewCategoryAndResponseOk() throws Exception {
        //given
        Category category = new Category();

        category.setTitle("Title post 1");
        category.setDescription("Description post 1");

        ResultActions resultActions = mockMvc
                .perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category)));
        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(category.getTitle())))
                .andExpect(jsonPath("$.description", is(category.getDescription())));


        List<Category> categories = categoryService.getAllCategories();
//        assertThat(posts).extracting(Post::getContent).containsOnly(post.getContent());
//        System.out.println(posts);
        assertThat(categories.size()).isEqualTo(1);
        //postRepository

    }



    @Test
    public void whenPostRequestCategories_thenCorrectResponse() throws Exception {
        String category = "{\"title\": \"Category 111 title\",\"description\": \"description here\"}";

        mockMvc.perform(post("/api/v1/categories")
                .content(category)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("Category 111 title")))
                .andExpect(jsonPath("$.description", is("description here")));
    }

    @Test
    public void whenPostRequestToCategoriesWithEmptyTitle_CorrectResponse() throws Exception {

        Category category = new Category();

        category.setTitle("");
        category.setDescription("Description post 1");


        ResultActions resultActions = mockMvc
                .perform(post("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)));
        // then
        resultActions.andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['validationErrors']['title']", is(CategoryIntegrationTest.ERROR_FOR_TITLE_EMPTY)));
    }

    @Test
    public void whenPostRequestToCategoriesWithEmptyDescription_CorrectResponse() throws Exception {

        String category = "{\"title\": \"title here\",\"description\": \"\"}";

        mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON).content(category))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['validationErrors']['description']", is(CategoryIntegrationTest.ERROR_FOR_DESCRIPTION_EMPTY)));
               // .andExpect(jsonPath("$.description", is("description here")));
    }

    @Test
    public void whenPostRequestToCategoriesWithEmptyTitleAndDescription_CorrectResponse() throws Exception {

        String category = "{\"title\": \"\",\"description\": \"\"}";

        mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON).content(category))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['validationErrors']['description']", is(CategoryIntegrationTest.ERROR_FOR_DESCRIPTION_EMPTY)))
                .andExpect(jsonPath("$['validationErrors']['title']", is(CategoryIntegrationTest.ERROR_FOR_TITLE_EMPTY)));
        // .andExpect(jsonPath("$.description", is("description here")));
    }

    @Test
    public void whenPostRequestToCategoriesWithNullTitle_CorrectResponse() throws Exception {

        String category = "{\"description\": \"description\"}";

        mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON).content(category))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['validationErrors']['title']", is(CategoryIntegrationTest.ERROR_FOR_TITLE_NULL)));
        // .andExpect(jsonPath("$.description", is("description here")));
    }

    @Test
    public void whenPostRequestToCategoriesWithNullDescription_CorrectResponse() throws Exception {

        String category = "{\"title\": \"title here\"}";

        mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON).content(category))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['validationErrors']['description']", is(CategoryIntegrationTest.ERROR_FOR_DESCRIPTION_NULL)));
        // .andExpect(jsonPath("$.description", is("description here")));
    }

    @Test
    public void whenPostRequestToCategoriesWithNullTitleAndDescription_CorrectResponse() throws Exception {

        String category = "{}";

        mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON).content(category))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['validationErrors']['description']", is(CategoryIntegrationTest.ERROR_FOR_DESCRIPTION_NULL)))
                .andExpect(jsonPath("$['validationErrors']['title']", is(CategoryIntegrationTest.ERROR_FOR_TITLE_NULL)));
        // .andExpect(jsonPath("$.description", is("description here")));
    }
}



