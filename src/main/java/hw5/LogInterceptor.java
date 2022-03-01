package hw5;

import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import sun.rmi.runtime.Log;

import java.io.IOException;



//abstract class LoggingInterceptor implements Interceptor {
//    public void LogInterceptor(){
//        OkHttpClient client = new OkHttpClient();
//        client.interceptors().add(new Interceptor() {
//            @Override
//            public Response intercept(Interceptor.Chain chain) throws IOException {
//                Request request = chain.request();
//
//                long t1 = System.nanoTime();
//                logger.info(String.format("Sending request %s on %s%n%s",
//                        request.url(), chain.connection(), request.headers()));
//
//                Response response = chain.proceed(request);
//
//                long t2 = System.nanoTime();
//                logger.info(String.format("Received response for %s in %.1fms%n%s",
//                        response.request().url(), (t2 - t1) / 1e6d, response.headers()));
//
//                return response;
//
//            }
//        });
//    }



//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request request = chain.request();
//        long t1 = System.nanoTime();
//        logger.info(String.format("Sending request %s on %s%n%s",
//                request.url(), chain.connection(), request.headers()));
//
//        Response response = chain.proceed(request);
//
//        long t2 = System.nanoTime();
//        logger.info(String.format("Received response for %s in %.1fms%n%s",
//                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
//
//        return response;
//    }
//}
