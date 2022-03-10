package hw5;

import hw5.api.CategoryService;
import hw5.dto.response.JsonCategory;
import hw5.utils.Helpers;
import hw5.utils.RetrofitUtil;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;


import static org.hamcrest.MatcherAssert.assertThat;

public class CategoryPositiveTest {
    static CategoryService categoryService;

    @BeforeAll
    static void initialService() {
        categoryService = RetrofitUtil.getRetrofit().create(CategoryService.class);
    }

    @SneakyThrows
    @Test
    void getCategoryPositiveTest() {
        Response<JsonCategory> resp = categoryService.getCategory(100).execute();
        assertThat(resp.isSuccessful(), CoreMatchers.is(true));
        Assertions.assertTrue(Helpers.isCategoryExist(100));
    }
}
