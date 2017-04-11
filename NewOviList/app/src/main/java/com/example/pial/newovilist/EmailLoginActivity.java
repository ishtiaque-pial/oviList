package com.example.pial.newovilist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmailLoginActivity extends AppCompatActivity {

    EditText username,password;
    private final String BASE_URL = "http://59.152.107.174/proyojon/";
    private ApiInterface apiInterface;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        username= (EditText) findViewById(R.id.login_email);
        password= (EditText) findViewById(R.id.login_password);
        progress=new ProgressDialog(this);

        networkLibraryIntialize();

    }



    private void networkLibraryIntialize() {

            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiInterface = retrofit.create(ApiInterface.class);


    }

    public void login(View view) {

        if (username.getText().toString().isEmpty()||password.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Fill All The Field", Toast.LENGTH_SHORT).show();

        }
        else {
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();

            Call<Signin> signinCall = apiInterface.loginPost(username.getText().toString(), password.getText().toString());
            signinCall.enqueue(new Callback<Signin>() {
                @Override
                public void onResponse(Call<Signin> call, Response<Signin> response) {
                    progress.dismiss();
                    Signin signin = response.body();
                    if (signin.getMessage().equals("success")) {
                        Toast.makeText(EmailLoginActivity.this, "Login ok", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EmailLoginActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Signin> call, Throwable t) {
                    progress.dismiss();
                    Toast.makeText(EmailLoginActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    public void gotoSignup(View view) {
        startActivity(new Intent(EmailLoginActivity.this,SignUpActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(EmailLoginActivity.this,LoginActivity.class));
        finish();
    }

    public void gotoFrogetPasswordActivity(View view) {
        startActivity(new Intent(EmailLoginActivity.this,ForgetPasswordActivity.class));
        finish();
    }
}
