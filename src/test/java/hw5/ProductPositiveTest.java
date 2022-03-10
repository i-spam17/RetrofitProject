package hw5;

import com.github.javafaker.Faker;
import hw5.api.ProductService;
import hw5.dto.response.JsonCategory;
import hw5.dto.response.JsonProduct;
import hw5.utils.Helpers;
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
    static long productID;
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
        Assertions.assertTrue(Helpers.isProductExist(productID));
    }

    @SneakyThrows
    @Test
    void updateProductTest() {
        int idCat = (int) (Math.random() * 100);
        long idProd = (long) (Math.random() * 100);

        JsonCategory createCategory = Helpers.createCategory(idCat, "title_Cat");
        JsonProduct createProduct = Helpers.createProduct(idProd, "title_Prod", 333, createCategory.getId());

        JsonProduct modifyProduct = new JsonProduct()
                .withId(createProduct.getId())
                .withTitle("modified title")
                .withPrice(567567)
                .withCategoryTitle("Furniture");

        Response<JsonProduct> resp = productService
                .updateProduct(modifyProduct)
                .execute();
        assertThat(resp.isSuccessful(), CoreMatchers.is(true));
        assert resp.body() != null;
        assertThat(resp.body().getId(), equalTo(idProd));
        assertThat(resp.body().getTitle(), equalTo("modified title"));
        assertThat(resp.body().getPrice(), equalTo(567567));
        assertThat(resp.body().getCategoryTitle(), equalTo("Furniture"));

        Assertions.assertEquals(Helpers.getProductEntity(idProd), modifyProduct);

        Helpers.deletedProduct(idProd);
        Helpers.deletedCategory(idCat);
    }

    @SneakyThrows
    @Test
    void getSpecificProductTest() {
        Response<JsonProduct> resp = productService.getSpecificProduct(productID).execute();
        assertThat(resp.isSuccessful(), CoreMatchers.is(true));
        assert resp.body() != null;
        assertThat(resp.body().getId(), equalTo(productID));
        assertThat(resp.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
        assertThat(resp.body().getPrice(), equalTo(product.getPrice()));
        assertThat(resp.body().getTitle(), equalTo(product.getTitle()));

        Assertions.assertTrue(Helpers.isProductExist(productID));
    }

    @SneakyThrows
    @AfterAll
    static void tearDown() {
        Response<Void> resp = productService.deleteProduct(productID).execute();
        Assertions.assertEquals(200, resp.code());
        Assertions.assertFalse(Helpers.isProductExist(productID));
    }
}
