package com.irvingmedina.graficasconsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText datoUno_EditText, datoDos_EditText, texto_EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datoUno_EditText = findViewById(R.id.datoUno_EditText);
        datoDos_EditText =  findViewById(R.id.datoDos_EditText);
        texto_EditText = findViewById(R.id.texto_EditText);

        final DatosDB datosDB = new DatosDB(getApplicationContext());


        findViewById(R.id.guardarDatos_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Accion para cuando se de click en el boton
                datosDB.agregarDatos(datoUno_EditText.getText().toString(),
                        datoDos_EditText.getText().toString(), texto_EditText.getText().toString());

                Toast.makeText(getApplicationContext(), "Datos agregados", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.pieChart_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PieChartActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.barChart_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BarChartActivity.class);
                startActivity(intent);
            }
        });

    }


}