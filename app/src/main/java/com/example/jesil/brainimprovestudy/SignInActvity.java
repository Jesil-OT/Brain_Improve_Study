package com.example.jesil.brainimprovestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SignInActvity extends AppCompatActivity {

    private static final String TAG = "SignInActvity";

    Button mButtonSignIn2;
    TextInputEditText mTextInputEditTextEmailSigninPage;
    TextInputEditText mTextInputEditTextPasswordSigninPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_actvity);
        mTextInputEditTextEmailSigninPage = findViewById(R.id.email_signin_page);
        mTextInputEditTextPasswordSigninPage = findViewById(R.id.password_signin_page);

        mButtonSignIn2 = findViewById(R.id.button_sign_in2);
        mButtonSignIn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: button is clicked");
                setErrorCode();
            }
        });

    }

    public void setErrorCode() {
        String emailSignInput = mTextInputEditTextEmailSigninPage.getText().toString().trim();
        String passwordSignInput = mTextInputEditTextPasswordSigninPage.getText().toString().trim();

        if (emailSignInput.length() == 0 && passwordSignInput.length() == 0) {
            mTextInputEditTextEmailSigninPage.setError("Please enter an Email to proceed");
            mTextInputEditTextPasswordSigninPage.setError("Please enter a password");
        }
        else if (passwordSignInput.length() >= 6){
            mTextInputEditTextPasswordSigninPage.setError("Password must not be Less 6 Character");
            mTextInputEditTextPasswordSigninPage.setError("Password must not be Greater 6 Character");
        }

        else {
            Intent intent = new Intent(SignInActvity.this,HomeActivity.class);
            Toast.makeText(this, "Sign in Successfully ", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_in_left);
            finish();
        }

    }
}
