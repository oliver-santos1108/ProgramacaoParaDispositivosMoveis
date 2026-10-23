package com.example.app;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db; //declara variável do tipo SQLiteDatabase

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
        setup();
    }
    public void setup(){

        /*tudo dentro desse metodo eu fiz da minha cabeça, conferir com o professor depois*/

        //criar banco
        db = openOrCreateDatabase("escola", MODE_PRIVATE, null);
        //criar tabela notas
        db.execSQL("CREATE TABLE IF NOT EXISTS notas " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo VARCHAR, conteudo VARCHAR)");
        //criar tabela alunos
        db.execSQL("CREATE TABLE IF NOT EXISTS alunos " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR, cpf VARCHAR, anoNasc INT)");
    }

}



