package com.sticket.app.sticket.activities.sign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.sticket.app.sticket.R;
import com.sticket.app.sticket.activities.store.StoreActivity;
import com.sticket.app.sticket.retrofit.client.ApiClient;
import com.sticket.app.sticket.retrofit.client.ApiConfig;
import com.sticket.app.sticket.retrofit.dto.request.auth.SignInRequest;
import com.sticket.app.sticket.retrofit.dto.response.user.SignInResponse;
import com.sticket.app.sticket.util.Alert;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity {
    private static final int FOLDERPICKER_CODE = 123;
    public static final String EXTRA_USER = "USER";

    @BindView(R.id.edit_signin_email)
    EditText emailEdit;
    @BindView(R.id.edit_signin_password)
    EditText passwordEdit;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_signin_submit)
    void signinSubmit(View view) {
        final SignInRequest request = new SignInRequest();
        request.setUsername(emailEdit.getText().toString());
        request.setPassword(passwordEdit.getText().toString());

        ApiClient.getInstance().getAuthService()
                .getToken(Credentials.basic(ApiConfig.USER_NAME, ApiConfig.USER_SECRET),
                        request.getUsername(), request.getPassword(), request.getGrantType())
                .enqueue(new Callback<SignInResponse>() {
                    @Override
                    public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                        if (response.body() != null) {
                            Log.e("SIGNIN", response.body().toString());
                            Log.e("SIGNIN", response.body().getAccessToken());
                            ApiClient.getInstance().setToken(response.body().getAccessToken());
                            ApiClient.getInstance().setUserId(response.body().getUserId());

                            setResult(RESULT_OK);
                            finish();
                        } else {
                            try {
                                Log.e("SIGNIN", "errorBody : " + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Alert.makeText("로그인 중 에러 발생");
                        }
                    }

                    @Override
                    public void onFailure(Call<SignInResponse> call, Throwable t) {
                        Alert.makeText("로그인 중 네트워크 에러 발생");
                        Log.e("SIGNIN", "host : " + call.request().url().toString(), t);
                    }
                });
    }

    @OnClick(R.id.btn_signin_signup)
    void signupSubmit(View view) {
        Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}
