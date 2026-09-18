package com.example.appdesergio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button b;
    EditText edmin, edmax;
    int posicao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Integer[] imagens = new Integer[]{
                R.drawable.normal,
                R.drawable.abaixopeso,
                R.drawable.obesidade1,
                R.drawable.obesidade2,
                R.drawable.obesidade3,
                R.drawable.sobrepeso
        };
        Button botaoVoltar,botaoAvancar;
        botaoVoltar = findViewById(R.id.btnAnterior);
        botaoAvancar = findViewById(R.id.btnProximo);
        ImageView imageView = findViewById(R.id.imageView);
        botaoAvancar.setOnClickListener(View v ->{
            posicao++;
            imageView.setImageResource(imagens)[posicao]);
        });
   }
}