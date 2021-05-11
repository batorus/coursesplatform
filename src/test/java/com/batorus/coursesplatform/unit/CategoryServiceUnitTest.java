package com.batorus.coursesplatform.unit;

import com.batorus.coursesplatform.exception.ResourceNotFoundException;
import com.batorus.coursesplatform.model.Category;
import com.batorus.coursesplatform.repositories.CategoryRepository;
import com.batorus.coursesplatform.repositories.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryServiceUnitTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Captor
    ArgumentCaptor<Category> categoryArgumentCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindCategory_ResourceNotFoundException() throws Exception {

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException exc = assertThrows(ResourceNotFoundException.class, ()->categoryService.find(1L));

        assertTrue(exc.getMessage().contains("Category with categoryId: " + 1 + " not found!"));
    }

    @Test
    public void itShouldFindCategoryById() throws Exception {

        Category category = new Category("the title", "the description");
        when(categoryRepository.findById(123L)).thenReturn(Optional.of(category));

        Category resultCategory = categoryService.find(123L);

        assertThat(resultCategory.getTitle()).isEqualTo(category.getTitle());
        assertThat(resultCategory.getDescription()).isEqualTo(category.getDescription());
    }

    @Test
    public void itShouldDeleteCategoryById() throws Exception {

        Category category = new Category("the title", "the description");

        when(categoryRepository.findById(123L)).thenReturn(Optional.of(category));

        categoryService.delete(123L);

        //verify(categoryRepository, times(1)).delete(any(Category.class));
        verify(categoryRepository, times(1)).delete(categoryArgumentCaptor.capture());
        assertThat(categoryArgumentCaptor.getValue().getTitle()).isEqualTo(category.getTitle());
        assertThat(categoryArgumentCaptor.getValue().getDescription()).isEqualTo(category.getDescription());
    }
}
