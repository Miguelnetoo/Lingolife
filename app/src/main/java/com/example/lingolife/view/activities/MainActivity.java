package com.example.lingolife.view.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lingolife.R;
import com.example.lingolife.utils.SessionManager;

public class MainActivity extends AppCompatActivity {

    private float dX, dY;
    private int lastAction;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializa o gerenciador de sessão
        SessionManager sessionManager = new SessionManager(this);

        // Verifica se o usuário está logado
        if (sessionManager.isLoggedIn()) {
            // Se o usuário já está logado, redireciona para a tela de perfil
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish(); // Finaliza a MainActivity para que o usuário não possa voltar
            return;
        }

        // Se o usuário não está logado, exibe a tela de login/registro
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Configura a barra de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencia dos botões
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        ImageView bannerImage = findViewById(R.id.banner_image); // Referência ao ImageView que será móvel

        // Ação do botão de Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Ação do botão de Registro
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        // Configura o OnTouchListener para o banner_image
        bannerImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        // Obter a posição inicial
                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();
                        lastAction = MotionEvent.ACTION_DOWN;
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        // Mover a view com base nos dados do toque
                        view.setX(event.getRawX() + dX);
                        view.setY(event.getRawY() + dY);
                        lastAction = MotionEvent.ACTION_MOVE;
                        return true;

                    case MotionEvent.ACTION_UP:
                        // Soltar a view
                        if (lastAction == MotionEvent.ACTION_DOWN) {
                            // Caso seja apenas um clique, você pode realizar uma ação
                        }
                        return true;

                    default:
                        return false;
                }
            }
        });
    }
}
