package hw5;

import hw5.dto.JsonModelResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ProductService {
    @GET("products")
    Call<List<JsonModelResponse>> getProducts();

    @POST("products")
    Call<JsonModelResponse> createProduct(@Body JsonModelResponse newProduct);

    @PUT("products")
    Call<JsonModelResponse> modifyProduct(@Body JsonModelResponse newProduct);

    @GET("products/{id}")
    Call<JsonModelResponse> returnSpecificProduct(@Path("id") int id);

    @DELETE("products/{id}")
    @Headers("accept: */*")
    Call<Void> deleteProduct(@Path("id") int id);
}
