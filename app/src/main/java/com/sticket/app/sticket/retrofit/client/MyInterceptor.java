package com.sticket.app.sticket.retrofit.client;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyInterceptor implements Interceptor {
    private final String TOKEN_TYPE = "Bearer ";
    private String token;

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();

        if (this.token != null) {
            Log.e("MyInterceptor", "token: "+ token);
            request = chain.request().newBuilder()
                    .addHeader("Authorization", TOKEN_TYPE + this.token)
                    .method(chain.request().method(), chain.request().body())
                    .build();
        }

        Response response = chain.proceed(request);

        try {
            Log.i("okhttp", String.format("Sending request %s on %s\n%s\n%s\n%s",
                    request.url(), chain.connection(), request.headers(), request.body(), request.header("Content-Type")));

            Log.i("okhttp", String.format("Received response for %s in %n%s",
                    response.body(), response.headers()));
        } catch (Exception e) {
            Log.e("okhttp", "Error: " + e);
        }
        return response;
    }


    public void setToken(String token) {
        this.token = token;
    }
}