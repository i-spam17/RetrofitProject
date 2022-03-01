package hw5;

import com.github.javafaker.Faker;
import hw5.dto.JsonModelResponse;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ProductsAPITest {
    static ProductService productService;
    static JsonModelResponse modifiedProduct;
    static Faker faker;
    static int productID;

    static JsonModelResponse testProduct;
    static int testProductID;
    static String testProductTitle;
    static int testProductPrice;

    @SneakyThrows
    @BeforeAll
    static void initialService() {
        productService = RetrofitUtil.getRetrofit();
        faker = new Faker();

        //get test data
        testProduct = new JsonModelResponse()
                .withId(null)
                .withTitle("Test title")
                .withPrice(100)
                .withCategoryTitle("Furniture");
        Response<JsonModelResponse> resp = productService.createProduct(testProduct).execute();
        assert resp.body() != null;
        testProductID = resp.body().getId();
        testProductTitle = resp.body().getTitle();
        testProductPrice = resp.body().getPrice();

        modifiedProduct = new JsonModelResponse()
                .withId(testProductID)
                .withTitle("modified title")
                .withPrice(888)
                .withCategoryTitle("Furniture");
    }

    @BeforeEach
    void setUp() {
    }

    @SneakyThrows
    @Test
    void getProductsPositiveTest() {
        Response<List<JsonModelResponse>> resp = productService.getProducts().execute();
        assertThat(resp.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @Test
    void getSpecificProduct() {
        Response<JsonModelResponse> resp = productService.returnSpecificProduct(19114).execute();
        assertThat(resp.isSuccessful(), CoreMatchers.is(true));
        assert resp.body() != null;
        assertThat(resp.body().getId(), equalTo(19114));
    }

    @SneakyThrows
    @Test
    void createProductPositiveTest() {
        Response<JsonModelResponse> resp = productService
                .createProduct(new JsonModelResponse()
                        .withId(null)
                        .withTitle(faker.harryPotter().location())
                        .withPrice((int) (Math.random() * 1000))
                        .withCategoryTitle("Furniture"))
                .execute();
        assert resp.body() != null;
        productID = resp.body().getId();
        assertThat(resp.isSuccessful(), CoreMatchers.is(true));
        assertThat(resp.body().getId(), equalTo(productID));
    }

    @SneakyThrows
    @Test
    void modifyProductPositiveTest() {
        Response<JsonModelResponse> resp = productService.modifyProduct(modifiedProduct).execute();
        assertThat(resp.isSuccessful(), CoreMatchers.is(true));
        assert resp.body() != null;
        assertThat(resp.body().getId(), equalTo(testProductID));
        assertThat(resp.body().getTitle(), equalTo("modified title"));
        assertThat(resp.body().getPrice(), equalTo(888));
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
