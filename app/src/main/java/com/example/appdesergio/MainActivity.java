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

    Button btnProximo, btnVoltar;
    EditText edmin, edmax;

    ImageView imageView;
    Integer[] imagens = new Integer[]{
            R.drawable.normal,
            R.drawable.abaixopeso,
            R.drawable.sobrepeso,
            R.drawable.obesidade1,
            R.drawable.obesidade2,
            R.drawable.obesidade3
    };

    int posicao = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnVoltar=findViewById(R.id.btnAnterior);
        btnProximo = findViewById(R.id.btnProximo);
        imageView =findViewById(R.id.imageView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnProximo.setOnClickListener(v ->{
            if(posicao == imagens.length-1) posicao = 0;
            posicao++;
            imageView.setImageResource(imagens[posicao]);
        });
        btnVoltar.setOnClickListener(v ->{
            posicao--;
            if(posicao < 0) posicao = imagens.length -1;
            imageView.setImageResource(imagens[posicao]);
        });
   }
}