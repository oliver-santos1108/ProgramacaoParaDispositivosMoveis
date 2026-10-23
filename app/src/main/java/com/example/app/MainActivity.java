package com.example.app;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
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
    SQLiteDatabase db; //declara variável do tipo SQLiteDatabase

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        listView = findViewById(R.id.listView);

        button.setOnClickListener(view ->{
            String titulo = editText.getText().toString();
            ContentValues cv = new ContentValues();
            cv.put("titulo", titulo);

            int status = (int) db.insert("notinhas", null, cv); //cast "(int)" - mudança explicita de tipo de variavel

            String msgStatus;
            if (status > 0) {
                        msgStatus="Nota salva com sucesso! "+status;
                        editText.setText("");
                        editText.requestFocus();

            } else {
                        msgStatus="Erro ao inserir a nota! "+status;
            }
            Toast.makeText(getApplicationContext(),msgStatus,Toast.LENGTH_LONG).show();
        });
        setup();
        listarNotas();
    }

    public void setup() {
        db = openOrCreateDatabase("bdnotas", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS notas " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo VARCHAR, txt VARCHAR)");

        listarNotas();

    }

    //aula 1 sqlite 16/10
    public void listarNotas() {
        Cursor cursor = db.rawQuery("SELECT * FROM notas LIMIT ?", new String[]{"100"});
        cursor.moveToFirst();
        ArrayList<String> notas = new ArrayList<>();
        while(!cursor.isAfterLast()){
            String s = cursor.getString((int)cursor.getColumnIndex("titulo")); //cast "(int)" - mudança explicita de tipo de variavel
            cursor.moveToNext();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                notas
        );
        listView.setAdapter(adapter);
    }
}


