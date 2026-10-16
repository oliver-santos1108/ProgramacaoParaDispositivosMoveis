package com.example.app;

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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTitulo;
    private EditText editTextTexto;
    private ListView listView;

    private DbHelper dbHelper;
    private ArrayList<Nota> listaNotas;

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

        dbHelper = new DbHelper(this);

        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextTexto = findViewById(R.id.editTextTexto);
        Button button = findViewById(R.id.button);
        listView = findViewById(R.id.listView);

        atualizarLista();

        button.setOnClickListener(v -> {
            String tituloInput = editTextTitulo.getText().toString().trim();
            String textoInput = editTextTexto.getText().toString().trim();

            if (tituloInput.isEmpty() && textoInput.isEmpty()) {
                Toast.makeText(this, "Preencha os campos corretamente.", Toast.LENGTH_SHORT).show();
                return;
            }

            String titulo = tituloInput.isEmpty() ? textoInput : tituloInput;
            String texto = textoInput.isEmpty() ? tituloInput : textoInput;

            long id = dbHelper.inserirNota(titulo, texto);
            if (id != -1) {
                Toast.makeText(this, "Nota cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                editTextTitulo.setText("");
                editTextTexto.setText("");
                atualizarLista();
            } else {
                Toast.makeText(this, "Erro ao cadastrar nota.", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Nota nota = listaNotas.get(position);
            Toast.makeText(getApplicationContext(), "Título: " + nota.getTitulo() + "\nTexto: " + nota.getTexto(), Toast.LENGTH_LONG).show();
        });
    }

    private void atualizarLista() {
        listaNotas = dbHelper.listarNotas();
        ArrayAdapter<Nota> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaNotas);
        listView.setAdapter(adapter);
    }
}
