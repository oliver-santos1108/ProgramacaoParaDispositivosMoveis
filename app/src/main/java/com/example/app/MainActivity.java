package com.example.app;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DbHelper dbHelper;
    EditText editText;
    Button button;
    ListView listView;

    SQLiteDatabase dataBase; //declara variável do tipo SQLiteDatabase

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBase = openOrCreateDatabase("notinahs", MODE_PRIVATE, null);
        dataBase.execSQL("CREATE TABLE IF NOT EXISTS notas " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo VARCHAR, conteudo VARCHAR)");
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        listView = findViewById(R.id.listView);
        button.setOnClickListener(view -> {
            String titulo = editText.getText().toString();
            ContentValues values = new ContentValues();
            values.put("titulo", titulo);

            int id = (int) dataBase.insert("notinhas", null, values);

            if (id > 0) {
                Toast.makeText(getApplicationContext(),
                        "Nota salva com sucesso",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Erro ao salvar nota",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void listarNotas() {
        dataBase.rawQuery("SELECT * FROM notas LIMIT ?", new String[]{"100"});

    }
}

