package com.example.pial.newovilist;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    private EditText name,email,phone,password,confirm;
    RadioButton male,female;
    private String email_token,pass,username,gender,birth_date,mobile,alt_mobile,address;
    private final String BASE_URL = "http://59.152.107.174/proyojon/";
    private ApiInterface apiInterface;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        name= (EditText) findViewById(R.id.signUp_name);
        email= (EditText) findViewById(R.id.signUp_email);
        phone= (EditText) findViewById(R.id.signUp_phone);
        password= (EditText) findViewById(R.id.signup_password);
        confirm= (EditText) findViewById(R.id.signup_password_confirm);
        male= (RadioButton) findViewById(R.id.signUp_male);
        female= (RadioButton) findViewById(R.id.signUp_female);

        networkLibraryIntialize();

    }

    private void networkLibraryIntialize() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public void registration(View view) {

        if (name.getText().toString().isEmpty()||email.getText().toString().isEmpty()||phone.getText().toString().isEmpty()||password.getText().toString().isEmpty()||confirm.getText().toString().isEmpty()||(!male.isChecked()&&!female.isChecked()))
        {
            Toast.makeText(this, "Fill All The Field", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (password.getText().toString().equals(confirm.getText().toString()))
            {
                if (email.getText().toString().contains("@")&&email.getText().toString().contains(".com")) {
                    if (male.isChecked()) {
                        gender = male.getText().toString();
                    } else {
                        gender = female.getText().toString();
                    }
                    email_token = email.getText().toString();
                    pass = password.getText().toString();
                    username = name.getText().toString();
                    birth_date = "1/1/1970";
                    mobile = phone.getText().toString();
                    alt_mobile = "empty";
                    address = "empty";

                    progress = new ProgressDialog(this);
                    progress.setTitle("Loading");
                    progress.setMessage("Wait while loading...");
                    progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                    progress.show();


                    Call<Signin> signupCall = apiInterface.signUpPost(email_token, pass, username, gender, birth_date, mobile, alt_mobile, address);
                    signupCall.enqueue(new Callback<Signin>() {
                        @Override
                        public void onResponse(Call<Signin> call, Response<Signin> response) {
                            progress.dismiss();
                            Signin signin = response.body();
                            if (signin.getMessage().equals("success")) {
                                Toast.makeText(SignUpActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            } else if (signin.getMessage().equals("duplicate")) {
                                Toast.makeText(SignUpActivity.this, "Your Email already registered", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Something Wrong" + signin.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Signin> call, Throwable t) {

                            Toast.makeText(SignUpActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(this, "Wrong Email Format", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        finish();
    }

    public void gotoEmailLoginActivity(View view) {
        startActivity(new Intent(SignUpActivity.this,EmailLoginActivity.class));
        finish();
    }
}
