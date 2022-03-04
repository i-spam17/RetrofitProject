package hw5;

import hw5.api.ProductService;
import hw5.dto.response.Json400Error;
import hw5.utils.RetrofitUtil;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import retrofit2.Response;

import java.io.File;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;

public class ProductsNegativeTest {
    static ProductService productService;
    static int productID;

    static int testProductID;

    @SneakyThrows
    @BeforeAll
    static void initialService() {
        productService = RetrofitUtil.getRetrofit().create(ProductService.class);
    }

    //todo Ошибки 401,403,404 - не возникают, а ошибки 400,500 в документации не указаны..-> документацию API нужно править.
    @SneakyThrows
    @ParameterizedTest
    @ValueSource(strings =
            {"src/test/resources/createProductData/AddOneField.json",
            "src/test/resources/createProductData/ChangeNameField.json",
            "src/test/resources/createProductData/ChangeTypeField.json",
            "src/test/resources/createProductData/Empty.json",
            "src/test/resources/createProductData/NoOneField.json",
            "src/test/resources/createProductData/NullField.json"})
    void createProductTest(String path) {
        Response<ResponseBody> resp = productService
                .negativeCreateProduct(new File(path))
                .execute();
        Assertions.assertEquals(400, resp.code());
    }

    @SneakyThrows
    @ParameterizedTest
    @ValueSource(strings =
            {"src/test/resources/createProductData/AddOneField.json",
                    "src/test/resources/createProductData/ChangeNameField.json",
                    "src/test/resources/createProductData/ChangeTypeField.json",
                    "src/test/resources/createProductData/Empty.json",
                    "src/test/resources/createProductData/NoOneField.json",
                    "src/test/resources/createProductData/NullField.json"})
    void updateProductTest(String path) {
        Response<ResponseBody> resp = productService
                .negativeUpdateProduct(new File(path))
                .execute();
        Assertions.assertEquals(400, resp.code());
    }

    private static Stream<Arguments> getSpecificProductTestData() {
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
    @MethodSource ("getSpecificProductTestData")
    void getSpecificProductTest(Object obj) {
        Response<Json400Error> resp = productService.negativeGetSpecificProduct(obj).execute();
        Assertions.assertEquals(400, resp.code());
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource ("getSpecificProductTestData")
    void deleteSpecificProductTest(Object obj) {
        Response<Void> resp = productService.negativeDeleteProduct(obj).execute();
        Assertions.assertEquals(400, resp.code());
    }

    @SneakyThrows
    @AfterAll
    static void tearDown() {
        Response<Void> resp = productService.deleteProduct(productID).execute();
        assertThat(resp.isSuccessful(), CoreMatchers.is(true));

        resp = productService.deleteProduct(testProductID).execute();
        assertThat(resp.isSuccessful(), CoreMatchers.is(true));
    }
}
