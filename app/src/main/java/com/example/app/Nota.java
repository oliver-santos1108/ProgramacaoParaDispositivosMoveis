package com.example.app;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Nota {
    private final int id;
    private final String titulo;
    private final String texto;

    public Nota(int id, String titulo, String texto) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTexto() {
        return texto;
    }

    @NonNull
    @Override
    public String toString() {
        if (titulo != null && !titulo.isEmpty() && texto != null && !texto.isEmpty() && !Objects.equals(titulo, texto)) {
            return titulo + " - " + texto;
        } else if (titulo != null && !titulo.isEmpty()) {
            return titulo;
        } else if (texto != null && !texto.isEmpty()) {
            return texto;
        }
        return "Nota #" + id;
    }
}
