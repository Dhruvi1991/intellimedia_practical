package com.intellimedia.practical.auth.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.intellimedia.practical.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtSuccess;
    AppCompatButton btnSignOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        initUI();
        initListeners();
    }

    private void initUI() {
        txtSuccess = findViewById(R.id.txtSuccess);
        btnSignOut = findViewById(R.id.btnSignOut);
    }

    private void initListeners() {
        btnSignOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(ProfileActivity.this, SignInActivity.class));
        ProfileActivity.this.finish();
    }
}
