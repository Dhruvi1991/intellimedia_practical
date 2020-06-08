package com.intellimedia.practical.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.intellimedia.practical.R;
import com.intellimedia.practical.auth.presenter.SignInPresenter;
import com.intellimedia.practical.utils.Utilities;
import com.intellimedia.practical.utils.Validator;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editUsername, editPassword;
    AppCompatButton btnSignIn, btnSignUp;

    SignInPresenter signInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initUI();
        initListeners();
        signInPresenter = new SignInPresenter();
    }

    private void initUI() {
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
    }

    private void initListeners() {
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                validateFiledsAndLogin();
                break;

            case R.id.btnSignUp:
                openSignUpScreen();
                break;
        }
    }

    private void validateFiledsAndLogin() {
        if (Validator.isFieldBlank(editUsername.getText().toString())) {
            editUsername.setError(Utilities.getBlankErrorMessage(this, "Username"));
        } else if (Validator.containsSpace(editUsername.getText().toString())) {
            editUsername.setError(Utilities.getSpaceErrorMessage(this, "Username"));
        } else if (Validator.isFieldBlank(editPassword.getText().toString())) {
            editPassword.setError(Utilities.getBlankErrorMessage(this, "Password"));
        } else if (Validator.containsSpace(editPassword.getText().toString())) {
            editPassword.setError(Utilities.getSpaceErrorMessage(this, "Password"));
        } else if (!signInPresenter.isUserAvailable(editUsername.getText().toString())) {
            editUsername.setError(getString(R.string.errorUserNotFound));
        } else if (!signInPresenter.getUser(editUsername.getText().toString()).getPassword().equals(Utilities.md5(editPassword.getText().toString()))) {
            editPassword.setError(getString(R.string.errorIncorrectPassword));
        } else if (!signInPresenter.getUser(editUsername.getText().toString()).getPassword().equals(Utilities.md5(editPassword.getText().toString()))) {
            editPassword.setError(getString(R.string.errorIncorrectPassword));
        } else {
            startActivity(new Intent(SignInActivity.this, ProfileActivity.class));
            SignInActivity.this.finish();
        }
    }


    private void openSignUpScreen() {
        startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
