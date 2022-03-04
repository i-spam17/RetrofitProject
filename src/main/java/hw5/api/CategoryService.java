package hw5.api;

import hw5.dto.response.Json404Error;
import hw5.dto.response.JsonCategory;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {
    @GET("categories/{id}")
    Call<JsonCategory> getCategory(@Path("id") int id);

    @GET("categories/{id}")
    Call<Json404Error> getCategory(@Path("id") Object id);
}
