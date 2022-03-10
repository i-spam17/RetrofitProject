package hw5;

import hw5.api.CategoryService;
import hw5.dto.response.Json404Error;
import hw5.utils.RetrofitUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import retrofit2.Response;

import java.util.stream.Stream;

public class CategoryNegativeTest {
    static CategoryService categoryService;

    @BeforeAll
    static void initialService() {
        categoryService = RetrofitUtil.getRetrofit().create(CategoryService.class);
    }

    private static Stream<Arguments> getCategoryNegativeTestData() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of(" "),
                Arguments.of((Object) null),
                Arguments.of(-2147483648),
                Arguments.of(2147483647),
                Arguments.of(0),
                Arguments.of("тEст123_!;"));
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("getCategoryNegativeTestData")
    void getCategoryNegativeTest(Object obj) {
        Response<Json404Error> resp = categoryService.getCategory(obj).execute();
        Assertions.assertEquals(404, resp.code());
    }
}
