package com.example.lingolife.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lingolife.R;
import com.example.lingolife.utils.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnBack; // Declaração do botão de voltar
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicialização dos componentes de entrada
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnBack = findViewById(R.id.btnBack); // Inicializa o botão de voltar

        // Inicializa o SessionManager
        sessionManager = new SessionManager(this);

        // Verifica se o usuário já está logado
        if (sessionManager.isLoggedIn()) {
            // Se já estiver logado, redireciona para a tela de perfil
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish(); // Finaliza a LoginActivity
        }

        // Ação do botão de Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        // Ação do botão de Voltar
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redireciona para a MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finaliza a LoginActivity
            }
        });
    }

    private void performLogin() {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        // Aqui você deve implementar sua lógica de autenticação (ex: verificação com um banco de dados)
        // Simulando uma autenticação bem-sucedida
        if (username.equals("user") && password.equals("password")) {
            // Salva o estado de login no SessionManager
            sessionManager.setLogin(true);

            // Redireciona para a tela de perfil ou a próxima atividade
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish(); // Finaliza a LoginActivity
        } else {
            // Exibe mensagem de erro
            Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
        }
    }
}


