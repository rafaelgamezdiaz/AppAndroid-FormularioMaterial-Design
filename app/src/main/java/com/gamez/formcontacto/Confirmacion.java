package com.gamez.formcontacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Confirmacion extends AppCompatActivity implements View.OnClickListener{

    // Atributos
    private Button btnEditar, btnSiguiente;
    String nombre, fecha,telefono, email, descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        TextView tvNombre       = (TextView) findViewById(R.id.tvNombre);
        TextView tvFecha        = (TextView) findViewById(R.id.tvFecha);
        TextView tvTelefono     = (TextView) findViewById(R.id.tvTelefono);
        TextView tvEmail        = (TextView) findViewById(R.id.tvEmail);
        TextView tvDescripcion  = (TextView) findViewById(R.id.tvDescripcion);

        Bundle parametros   = getIntent().getExtras();

        nombre       = parametros.getString(getResources().getString(R.string.keyNombre));
        fecha        = parametros.getString(getResources().getString(R.string.keyFecha));
        telefono     = parametros.getString(getResources().getString(R.string.keyTelefono));
        email        = parametros.getString(getResources().getString(R.string.keyEmail));
        descripcion  = parametros.getString(getResources().getString(R.string.keyDescripcion));

        tvNombre.setText(nombre);
        tvFecha.setText(fecha);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
        tvDescripcion.setText(descripcion);

        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(this);

        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEditar:
                finish(); // Con finish destruimos el activity actual de Confirmación y regresamos a Editar los datos
                break;
            case R.id.btnSiguiente:
                Intent intent = new Intent(Confirmacion.this, DatosEnviados.class);
                startActivity(intent);
                finish();
                break;
        }

    }
}
