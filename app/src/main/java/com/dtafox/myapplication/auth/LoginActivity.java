package com.dtafox.myapplication.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dtafox.myapplication.MainActivity;
import com.dtafox.myapplication.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

/** Activity responsible for user authentication using FirebaseAuth */
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        // Attempt sign in when login button is clicked
        binding.loginButton.setOnClickListener(v -> signIn());
    }

    private void signIn() {
        String email = binding.emailInput.getText().toString();
        String pass = binding.passwordInput.getText().toString();
        auth.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener(result -> openMain())
                .addOnFailureListener(e -> Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show());
    }

    /** Launch the task list activity when authentication succeeds */
    private void openMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
