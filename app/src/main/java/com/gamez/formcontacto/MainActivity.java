package com.gamez.formcontacto;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Atributos
    private TextInputEditText nombre, telefono, email, descripcion;
    private DatePicker simpleDatePicker;
    private Button btnSiguinte;
    private DatosContacto datosContacto;
    private String fechaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tomando los valores de la Vista
        nombre = (TextInputEditText) findViewById(R.id.etNombre);
        telefono = (TextInputEditText) findViewById(R.id.etTelefono);
        email = (TextInputEditText) findViewById(R.id.etEmail);
        descripcion = (TextInputEditText) findViewById(R.id.etDescripcion);




        // Configuracion del DatePicker
        simpleDatePicker = (DatePicker) findViewById(R.id.simpleDatePicker);
        Calendar calendario = Calendar.getInstance();
        // Se inicializa con la fecha actual
        int year = calendario.get(Calendar.YEAR);
        int month = calendario.get(Calendar.MONTH);
        int day = calendario.get(Calendar.DAY_OF_MONTH);

        Date fechaini = calendario.getTime();
        DateFormat fechaInicia = DateFormat.getDateInstance(DateFormat.MEDIUM);
        fechaSeleccionada = fechaInicia.format(fechaini);

        // Obtenemos la nueva fecha
        simpleDatePicker.init(year, month, day, new DatePicker.OnDateChangedListener(){
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Do something when the date changed from date picker object

                // Creamos un objeto con la fecha seleccionada
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(0);
                cal.set(year, monthOfYear, dayOfMonth,0,0,0);
                Date shosenDate = cal.getTime();

                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                fechaSeleccionada = df.format(shosenDate);
               // tvFecha.setText(formattedDate);
            }
        });

        // Si el usuario presiona el botón "Siguiente"
        btnSiguinte = (Button) findViewById(R.id.btnSiguiente);
        btnSiguinte.setOnClickListener(this); // Aqui programamos la accion del boton desde codigo y no desde el xml

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSiguiente:
                // Verificamos que la direccion de correo tenga un formato valido
                if (!email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                    Toast.makeText(this,"Correo Electrónico Inválido",Toast.LENGTH_SHORT).show();
                }else {
                    datosContacto = new DatosContacto(nombre.getText().toString(), fechaSeleccionada,telefono.getText().toString(), email.getText().toString(), descripcion.getText().toString());
                    Intent intent = new Intent(MainActivity.this, Confirmacion.class);
                    // Envio de los parametros
                    intent.putExtra(getResources().getString(R.string.keyNombre),datosContacto.getNombre());
                    intent.putExtra(getResources().getString(R.string.keyFecha),datosContacto.getFecha());
                    intent.putExtra(getResources().getString(R.string.keyTelefono),datosContacto.getTelefono());
                    intent.putExtra(getResources().getString(R.string.keyEmail),datosContacto.getEmail());
                    intent.putExtra(getResources().getString(R.string.keyDescripcion),datosContacto.getDescripcion());
                    startActivity(intent);
                /* En este caso, cuando le damos click al boton "Siguiente" para enviar el formulario,
                nosotros queremos que el usuario pueda regresar a esta activity del formulario a editar
                los datos del contacto, entonces no destruimos el activity actual */
                    //finish();
                }
                break;
        }
    }


}
