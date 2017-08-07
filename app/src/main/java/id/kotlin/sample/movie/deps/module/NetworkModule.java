package id.kotlin.sample.movie.deps.module;

import android.content.Context;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.kotlin.sample.movie.data.remote.Api;
import id.kotlin.sample.movie.utils.Constants;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class NetworkModule {

    private static final int MAX_IDLE_CONNECTIONS = 15;
    private static final long KEEP_ALIVE_DURATION = 30 * 1000;
    private static final long CACHE_SIZE = 30 * 1024 * 1024;

    private final Context context;

    public NetworkModule(final Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    protected Api provideApi(final Cache cache,
                             final ConnectionPool connectionPool) {
        final Retrofit retrofit = new Retrofit.Builder()
                                              .client(getOkHttpClient(cache, connectionPool))
                                              .baseUrl(Constants.BASE_URL)
                                              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                              .addConverterFactory(JacksonConverterFactory.create(getObjectMapper()))
                                              .build();

        return retrofit.create(Api.class);
    }

    @Provides
    @Singleton
    protected Cache provideCache() {
        return new Cache(context.getExternalCacheDir(), CACHE_SIZE);
    }

    @Provides
    @Singleton
    protected ConnectionPool provideConnectionPool() {
        return new ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALIVE_DURATION, TimeUnit.SECONDS);
    }

    private OkHttpClient getOkHttpClient(final Cache cache,
                                         final ConnectionPool connectionPool) {
        final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                               .cache(cache)
                               .connectTimeout(30, TimeUnit.SECONDS)
                               .readTimeout(15, TimeUnit.SECONDS)
                               .writeTimeout(15, TimeUnit.SECONDS)
                               .retryOnConnectionFailure(true)
                               .addInterceptor(getInterceptor())
                               .addInterceptor(httpLoggingInterceptor)
                               .connectionPool(connectionPool)
                               .build();
    }

    private Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(final Chain chain) throws IOException {
                final Request request = chain.request();
                final Request.Builder builder = request.newBuilder();
                builder.addHeader("Content-Type", "application/json");

                return chain.proceed(builder.build());
            }
        };
    }

    private ObjectMapper getObjectMapper() {
        return new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                                 .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                                 .configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    }
}