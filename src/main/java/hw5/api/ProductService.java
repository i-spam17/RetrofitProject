package hw5.api;

import hw5.dto.response.Json400Error;
import hw5.dto.response.Json404Error;
import hw5.dto.response.JsonProduct;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.io.File;
import java.util.List;

public interface ProductService {
    @GET("products")
    Call<List<JsonProduct>> getListOfProducts();

    @POST("products")
    Call<JsonProduct> createProduct(@Body JsonProduct newJsonProduct);

    @POST("products")
    Call<ResponseBody> negativeCreateProduct(@Body File newJsonProduct);

    @PUT("products")
    Call<JsonProduct> updateProduct(@Body JsonProduct newJsonProduct);

    @PUT("products")
    Call<ResponseBody> negativeUpdateProduct(@Body File  newJsonProduct);

    @GET("products/{id}")
    Call<JsonProduct> getSpecificProduct(@Path("id") int id);

    @GET("products/{id}")
    Call<Json400Error> negativeGetSpecificProduct(@Path("id") Object id);

    @GET("products/{id}")
    Call<Json404Error> checkDeleteGetSpecificProduct(@Path("id") Object id);

    @DELETE("products/{id}")
    @Headers("accept: */*")
    Call<Void> deleteProduct(@Path("id") int id);

    @DELETE("products/{id}")
    @Headers("accept: */*")
    Call<Void> negativeDeleteProduct(@Path("id") Object id);
}
