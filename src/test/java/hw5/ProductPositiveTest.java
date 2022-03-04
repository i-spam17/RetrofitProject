package hw5;

import com.github.javafaker.Faker;
import hw5.api.ProductService;
import hw5.dto.response.Json404Error;
import hw5.dto.response.JsonProduct;
import hw5.utils.RetrofitUtil;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ProductPositiveTest {
    static ProductService productService;
    static Faker faker;
    static Response<JsonProduct> respCreateProduct;
    static int productID;
    static JsonProduct product;

    @SneakyThrows
    @BeforeAll
    static void initialService() {
        productService = RetrofitUtil.getRetrofit().create(ProductService.class);
        faker = new Faker();

        product = new JsonProduct()
                .withId(null)
                .withTitle(faker.harryPotter().location())
                .withPrice((int) (Math.random() * 1000))
                .withCategoryTitle("Furniture");

        respCreateProduct = productService.createProduct(product).execute();
        assert respCreateProduct.body() != null;
        productID = respCreateProduct.body().getId();
    }

    @SneakyThrows
    @Test
    void getProductsListTest() {
        Response<List<JsonProduct>> resp = productService.getListOfProducts().execute();
        Assertions.assertEquals(200, resp.code());
    }

    @SneakyThrows
    @Test
    void createProductTest() {
        assertThat(respCreateProduct.isSuccessful(), CoreMatchers.is(true));
        assert respCreateProduct.body() != null;
        assertThat(respCreateProduct.body().getId(), equalTo(productID));
    }

    @SneakyThrows
    @Test
    void updateProductTest() {
        Response<JsonProduct> resp = productService
                .updateProduct(new JsonProduct()
                        .withId(productID)
                        .withTitle("modified title")
                        .withPrice(888)
                        .withCategoryTitle("Furniture"))
                .execute();
        assertThat(resp.isSuccessful(), CoreMatchers.is(true));
        assert resp.body() != null;
        assertThat(resp.body().getId(), equalTo(productID));
        assertThat(resp.body().getTitle(), equalTo("modified title"));
        assertThat(resp.body().getPrice(), equalTo(888));
        assertThat(resp.body().getCategoryTitle(), equalTo("Furniture"));
    }

    @SneakyThrows
    @Test
    void getSpecificProduct() {
        Response<JsonProduct> resp = productService.getSpecificProduct(productID).execute();
        assertThat(resp.isSuccessful(), CoreMatchers.is(true));
        assert resp.body() != null;
        assertThat(resp.body().getId(), equalTo(productID));
        assertThat(resp.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
        assertThat(resp.body().getPrice(), equalTo(product.getPrice()));
        assertThat(resp.body().getTitle(), equalTo(product.getTitle()));
    }

    @SneakyThrows
    @AfterAll
    static void tearDown() {
        Response<Void> resp = productService.deleteProduct(productID).execute();
        Assertions.assertEquals(200, resp.code());

        Response<Json404Error> respCheckDelete = productService.checkDeleteGetSpecificProduct(productID).execute();
        Assertions.assertEquals(404, respCheckDelete.code());
    }
}
