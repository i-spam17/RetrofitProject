package hw5;

import io.qameta.allure.okhttp3.AllureOkHttp3;
import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@UtilityClass
public class RetrofitUtil {
//    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new PrettyLogger());
//    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public ProductService getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(); //new prettyLogger() ???
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new AllureOkHttp3()
//                        .setRequestTemplate("src/test/resources/tpl/http-request.ftl")
//                        .setResponseTemplate("src/test/resources/tpl/http-response.ftl"))
                )
                        .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConfigUtils.getBaseUrl())
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(ProductService.class);

//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
//                .addInterceptor(new AllureOkHttp3()
//                        .setRequestTemplate("src/test/resources/tpl/http-request.ftl")
//                        .setResponseTemplate("src/test/resources/tpl/http-response.ftl"))
//                .build();
//
//        return new Retrofit.Builder()
//                .baseUrl(ConfigUtils.getBaseUrl())
//                .addConverterFactory(JacksonConverterFactory.create())
//                .client(httpClient.build())
//                .build();
    }
}
