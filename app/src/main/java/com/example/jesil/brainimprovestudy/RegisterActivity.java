package com.example.jesil.brainimprovestudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    Button mButtonRegister2;
    TextInputEditText mTextInputEditTextUserNameRegister;
    TextInputEditText mTextInputEditTextEmailRegister;
    TextInputEditText mTextInputEditTextPasswordRegister;

    String userNameRegisterInput;
    String emailRegisterInput;
    String PasswordRegisterInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mTextInputEditTextUserNameRegister = findViewById(R.id.username_register_page);
        mTextInputEditTextEmailRegister = findViewById(R.id.email_register_page);
        mTextInputEditTextPasswordRegister = findViewById(R.id.password_register_page);

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
                PasswordRegisterInput.length() == 0) {

            mTextInputEditTextUserNameRegister.setError("Please enter a Username to proceed");
            mTextInputEditTextEmailRegister.setError("Please enter an Email to proceed");
            mTextInputEditTextPasswordRegister.setError("Please enter a password");
        }
        /*else if (userNameRegisterInput.isEmpty() ||
                emailRegisterInput.isEmpty() ||
                PasswordRegisterInput.isEmpty()) {

            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "checkingFields: if statement called");
        }*/
        else if (PasswordRegisterInput.length() < 6){
            mTextInputEditTextPasswordRegister.setError("Password Should contain at Least 6 Character");
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
