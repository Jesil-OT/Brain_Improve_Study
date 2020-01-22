package com.example.jesil.brainimprovestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    Button mButtonRegister2;
    TextInputEditText mTextInputEditTextEmailRegister;
    TextInputEditText mTextInputEditTextPasswordRegister;
    TextInputEditText mTextInputEditTextConfirmPasswordRegister;

    String emailRegisterInput;
    String passwordRegisterInput;
    String confirmPasswordregisterInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mTextInputEditTextEmailRegister = findViewById(R.id.email_register_page);
        mTextInputEditTextPasswordRegister = findViewById(R.id.password_register_page);
        mTextInputEditTextConfirmPasswordRegister = findViewById(R.id.confirmPassword_register_page);

        mButtonRegister2 = findViewById(R.id.button_register2);
        mButtonRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkingFields();
            }
        });
    }

    public void checkingFields() {
        emailRegisterInput = mTextInputEditTextEmailRegister.getText().toString().trim();
        passwordRegisterInput = mTextInputEditTextPasswordRegister.getText().toString().trim();
        confirmPasswordregisterInput = mTextInputEditTextConfirmPasswordRegister.getText().toString().trim();

        if (emailRegisterInput.length() == 0 &&
                passwordRegisterInput.length() == 0 &&
                confirmPasswordregisterInput.length() == 0) {

            mTextInputEditTextEmailRegister.setError("Please enter an Email to proceed");
            mTextInputEditTextPasswordRegister.setError("Please enter a password");
            mTextInputEditTextConfirmPasswordRegister.setError("Please enter a password");
        }
        if (emailRegisterInput.isEmpty() ||
                passwordRegisterInput.isEmpty() ||
                confirmPasswordregisterInput.isEmpty()) {

            Toast.makeText(this, "please fill in all", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "checkingFields: if statement called");
        }

        else {
            Intent intent = new Intent(RegisterActivity.this, SignInActvity.class);
            Toast.makeText(this, "You just registered", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_in_left);
            finish();
        }

    }

   /* private void forEmptyString() {
        if (TextUtils.equals(passwordRegisterInput, confirmPasswordregisterInput)) {
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
