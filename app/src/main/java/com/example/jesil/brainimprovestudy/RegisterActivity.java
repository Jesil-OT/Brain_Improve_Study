package com.example.jesil.brainimprovestudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    TextInputEditText mTextInputEditTextUserNameRegister, mTextInputEditTextEmailRegister, mTextInputEditTextPasswordRegister;

    Button mButtonRegister2;
    String userNameRegisterInput,emailRegisterInput, PasswordRegisterInput;
    private FirebaseAuth mFirebaseAuth;
    private ProgressBar mProgressBarRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       mFirebaseAuth  = FirebaseAuth.getInstance();

        mTextInputEditTextUserNameRegister = findViewById(R.id.username_register_page);
        mTextInputEditTextEmailRegister = findViewById(R.id.email_register_page);
        mTextInputEditTextPasswordRegister = findViewById(R.id.password_register_page);
        mProgressBarRegister = findViewById(R.id.progressBar_register);

        mButtonRegister2 = findViewById(R.id.button_register2);
        mButtonRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkingFields();
            }
        });

    }



    public void checkingFields() {
        userNameRegisterInput = mTextInputEditTextUserNameRegister.getText().toString().trim();
        emailRegisterInput = mTextInputEditTextEmailRegister.getText().toString().trim();
        PasswordRegisterInput = mTextInputEditTextPasswordRegister.getText().toString().trim();

        if (userNameRegisterInput.length() == 0 &&
                emailRegisterInput.length() == 0 &&
                PasswordRegisterInput.length() == 0 ) {

            mTextInputEditTextUserNameRegister.setError("Please enter a Username to proceed");
            mTextInputEditTextEmailRegister.setError("Please enter an Email to proceed");
            mTextInputEditTextPasswordRegister.setError("Please enter a password");

            mTextInputEditTextUserNameRegister.requestFocus();
            mTextInputEditTextEmailRegister.requestFocus();
            mTextInputEditTextPasswordRegister.requestFocus();
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(emailRegisterInput).matches()){
            mTextInputEditTextEmailRegister.setError("Please enter a valid Email");
            mTextInputEditTextEmailRegister.requestFocus();
        }
        /*else if (userNameRegisterInput.isEmpty() ||
                emailRegisterInput.isEmpty() ||
                PasswordRegisterInput.isEmpty()) {

            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "checkingFields: if statement called");
        }*/
        else if (PasswordRegisterInput.length() < 6 ){
            mTextInputEditTextPasswordRegister.setError("Minimum length of password should be 6");
            mTextInputEditTextPasswordRegister.requestFocus();

        }
        else {
            Register();
            mProgressBarRegister.setVisibility(View.VISIBLE);
            mButtonRegister2.setVisibility(View.GONE);
        }

    }

    private void Register() {
        mFirebaseAuth.createUserWithEmailAndPassword(emailRegisterInput, PasswordRegisterInput)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    mFirebaseAuth.getCurrentUser();
                    Intent intent = new Intent(RegisterActivity.this, SignInActvity.class);
                    Toast.makeText(RegisterActivity.this, "You just registered", Toast.LENGTH_SHORT).show();
                    mProgressBarRegister.setVisibility(View.GONE);
                    mButtonRegister2.setVisibility(View.VISIBLE);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_in_left);
                    finish();
                }
                else{

                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(RegisterActivity.this, "You are already Registered", Toast.LENGTH_SHORT).show();
                        mProgressBarRegister.setVisibility(View.GONE);
                        mButtonRegister2.setVisibility(View.VISIBLE);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Authentication failed, Network unavailable please try again.", Toast.LENGTH_SHORT).show();
                        mProgressBarRegister.setVisibility(View.GONE);
                        mButtonRegister2.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

   /* private void forEmptyString() {
        if (TextUtils.equals(emailRegisterInput, PasswordRegisterInput)) {
            Intent intent = new Intent(RegisterActivity.this, SignInActvity.class);
            Toast.makeText(this, "You just registered", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_in_left);
            finish();
        } else {
            Log.d(TAG, "forEmptyStrings:  else statement called");
            Toast.makeText(this, "Password Error, Please check your password", Toast.LENGTH_SHORT).show();
        }
    }
    */
}
