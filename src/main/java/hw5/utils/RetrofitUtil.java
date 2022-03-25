package hw5.utils;

import io.qameta.allure.okhttp3.AllureOkHttp3;
import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@UtilityClass
public class RetrofitUtil {
    PrettyLogger interceptor = new PrettyLogger();
    OkHttpClient client;

    public Retrofit getRetrofit() {
        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addInterceptor(new AllureOkHttp3())
                .build();

        return new Retrofit.Builder()
                .baseUrl(ConfigUtils.getBaseUrl())
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}
