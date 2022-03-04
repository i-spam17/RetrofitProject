package hw5.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.*;

import java.io.IOException;

public class PrettyLogger implements Interceptor {
    ObjectMapper mapper = new ObjectMapper();
    Logger logger = LoggerFactory.getLogger(PrettyLogger.class);

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        logger.info(String.format("Sending request %s on %n%s%n%s%n%s",
                request.url(), chain.connection(), request.headers(),
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request.body())));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        assert response.body() != null;
        logger.info(String.format("Received response for %s in %.1fms%n%s%n%s%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers(), response.code(),
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.peekBody(2048).string())));

        return response;


    }
}
