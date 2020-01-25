package com.example.jesil.brainimprovestudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

public class ForgetPasswordActivity extends AppCompatActivity {

    Toolbar mToolbarForgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mToolbarForgetPassword = findViewById(R.id.toolbar_forgotPassword);

        mToolbarForgetPassword.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
