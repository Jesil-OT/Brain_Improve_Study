package com.example.jesil.brainimprovestudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaCodec;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignInActvity extends AppCompatActivity {

    private static final String TAG = "SignInActvity";
    TextInputEditText mTextInputEditTextEmailSigninPage, mTextInputEditTextPasswordSigninPage ;

    Button mButtonSignIn2;
    String emailSignInput;
    String passwordSignInput;
    ProgressBar mProgressBarSignIn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_actvity);
        mTextInputEditTextEmailSigninPage = findViewById(R.id.email_signin_page);
        mTextInputEditTextPasswordSigninPage = findViewById(R.id.password_signin_page);
        mProgressBarSignIn = findViewById(R.id.progressBar_signIn);

        mAuth = FirebaseAuth.getInstance();

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
        emailSignInput = mTextInputEditTextEmailSigninPage.getText().toString().trim();
        passwordSignInput = mTextInputEditTextPasswordSigninPage.getText().toString().trim();

        if (emailSignInput.length() == 0 && passwordSignInput.length() == 0) {
            mTextInputEditTextEmailSigninPage.setError("Please enter an Email to proceed");
            mTextInputEditTextPasswordSigninPage.setError("Please enter a password");
            mTextInputEditTextEmailSigninPage.requestFocus();
            mTextInputEditTextPasswordSigninPage.requestFocus();

        }
        else if (passwordSignInput.length() <= 6){
            mTextInputEditTextPasswordSigninPage.setError("Minimum length of password should be 6");
            mTextInputEditTextPasswordSigninPage.requestFocus();
        }
       /* else if (TextUtils.isEmpty(emailSignInput) || TextUtils.isEmpty(passwordSignInput)){
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }*/

       else if (!Patterns.EMAIL_ADDRESS.matcher(emailSignInput).matches()){
           mTextInputEditTextEmailSigninPage.setError("Please enter a valid Email");
           mTextInputEditTextEmailSigninPage.requestFocus();
       }
       else {
            signIn();
            mProgressBarSignIn.setVisibility(View.VISIBLE);
            mButtonSignIn2.setVisibility(View.GONE);
       }

    }

    private void signIn() {
        mAuth.signInWithEmailAndPassword(emailSignInput, passwordSignInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(SignInActvity.this,HomeActivity.class);
                    Toast.makeText(SignInActvity.this, "SignIn Successfully ", Toast.LENGTH_SHORT).show();
                    mProgressBarSignIn.setVisibility(View.GONE);
                    mButtonSignIn2.setVisibility(View.VISIBLE);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_in_left);
                    finish();
                }
                else{
                    Toast.makeText(SignInActvity.this, "Network unavailable please try again.", Toast.LENGTH_SHORT).show();
                    mProgressBarSignIn.setVisibility(View.GONE);
                    mButtonSignIn2.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
