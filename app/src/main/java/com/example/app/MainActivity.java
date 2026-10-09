package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> nomes;
    Button button;

    EditText nome;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listView);
        button = findViewById(R.id.button);
        nome = findViewById(R.id.editText);
        nomes = new ArrayList<String>();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nomes);
        lv.setAdapter(adapter);

        button.setOnClickListener(v ->{
            String nome1 = nome.getText().toString();

            if(!nome1.isEmpty()){
                nomes.add(nome1);
                adapter.notifyDataSetChanged();
                Toast.makeText(this,"Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"Preencha o campo corretamente.", Toast.LENGTH_SHORT).show();
            }

        });

       lv.setOnItemClickListener((parent, view, position, id) -> {
           Toast.makeText(getApplicationContext(), nomes.get(position), Toast.LENGTH_LONG).show();
                }
        );
    }
}