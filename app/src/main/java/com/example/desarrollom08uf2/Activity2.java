package com.example.desarrollom08uf2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    BD myBd;
    EditText editNombre, editApellidos,editNota;
    Button btnInsert,btnSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        myBd = new BD(this);

        //Castings
        editNombre = findViewById(R.id.editText);
        editApellidos = findViewById(R.id.editText2);
        editNota = findViewById(R.id.editText3);
        btnInsert =  findViewById(R.id.button4);
        btnSelect = findViewById(R.id.button5);
        tableInsert();
        tableConsult();
    }

    public void goActivityMain(View view) {
        Intent intent = new Intent(Activity2.this,MainActivity.class);
        startActivity(intent);
        finish();//cerrar esta pantalla
    }

    public void tableCreate(View view) {
    }

    public void tableInsert() {
        btnInsert.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myBd.insertData(editNombre.getText().toString(),
                                editApellidos.getText().toString(),
                                editNota.getText().toString() );

                        if(isInserted)
                            Toast.makeText(Activity2.this, "Datos insertadosdsds",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(Activity2.this, "Datos NOI insertadosdsds",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }


    public void tableConsult() {
        btnSelect.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor result = myBd.getDatos();
                        if(result.getCount()==0){
                            mostrarMensaje("Error","No hay datos");
                            return;
                        }

                        StringBuilder buffer = new StringBuilder();
                        while (result.moveToNext()){//Guarda el resultado aqui
                            buffer.append("Id :").append(result.getString(0)).append("\n");
                            buffer.append("Nombre :").append(result.getString(1)).append("\n");
                            buffer.append("Apellido :").append(result.getString(2)).append("\n");
                            buffer.append("Nota :").append(result.getString(3)).append("\n\n");
                        }
                        //Mostrar todos los datos
                        mostrarMensaje("Datos",buffer.toString());
                    }
                }
        );

    }

    public void mostrarMensaje (String titulo, String Mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();
    }
}
