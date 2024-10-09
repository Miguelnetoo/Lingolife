package com.example.lingolife.view.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lingolife.utils.SessionManager;
import com.example.lingolife.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SessionManager sessionManager = new SessionManager(this);
        if (!sessionManager.isLoggedIn()) {
            // Se o usuário não estiver logado, redireciona para a MainActivity
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finaliza a ProfileActivity
        }

        setContentView(R.layout.activity_profile);
        // Continue a lógica do perfil aqui
    }
}
