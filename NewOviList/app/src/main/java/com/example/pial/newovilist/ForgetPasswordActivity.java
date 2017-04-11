package com.example.pial.newovilist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ForgetPasswordActivity.this,EmailLoginActivity.class));
        finish();
    }
}
