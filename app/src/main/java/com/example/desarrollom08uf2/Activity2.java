package com.example.desarrollom08uf2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    BD myBd;
    EditText editNombre, editApellidos,editNota,editExtra;
    TextView text1,text2,text3,text4;
    Button btnInsert,btnSelect,btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        myBd = new BD(this);

        //Castings
        editNombre = findViewById(R.id.editText);
        editApellidos = findViewById(R.id.editText2);
        editNota = findViewById(R.id.editText3);
        editExtra= findViewById(R.id.editText4);
        btnInsert =  findViewById(R.id.button4);
        btnSelect = findViewById(R.id.button5);
        btnCreate = findViewById(R.id.button3);
        text1= findViewById(R.id.textView3);
        text2= findViewById(R.id.textView4);
        text3= findViewById(R.id.textView5);
        text4= findViewById(R.id.textView7);


        tableInsert();
        tableConsult();
    }

    public void goActivityMain(View view) {
        Intent intent = new Intent(Activity2.this,MainActivity.class);
        startActivity(intent);
        finish();//cerrar esta pantalla
    }

    public void tableCreate(View view) {
        //Recogemos los valores
        BD.TABLE_NAME = editNombre.getText().toString();
        BD.COL_2 = editApellidos.getText().toString();
        BD.COL_3 = editNota.getText().toString();
        BD.COL_4 = editExtra.getText().toString();
        //Creamos la tabla
        myBd.createTable(BD.TABLE_NAME,BD.COL_1,BD.COL_2,BD.COL_3,BD.COL_4);
        //Ocultamos lo que no sirve y mostramos lo que si
        editExtra.setVisibility(View.INVISIBLE);
        text4.setVisibility(View.INVISIBLE);
        text1.setText(BD.COL_2);
        text2.setText(BD.COL_3);
        text3.setText(BD.COL_4);
        //vaciamos los editText
        editNombre.setText(null);
        editApellidos.setText(null);
        editNota.setText(null);



        btnInsert.setVisibility(View.VISIBLE);
        btnCreate.setVisibility(View.INVISIBLE);

    }

    public void tableInsert() {
        btnInsert.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myBd.insertData(editNombre.getText().toString(),
                                editApellidos.getText().toString(),
                                editNota.getText().toString() );

                        if(isInserted){
                            Toast.makeText(Activity2.this, R.string.insertOK,Toast.LENGTH_SHORT).show();
                            btnSelect.setVisibility(View.VISIBLE);//Damos la posibilidad de ver los datos

                            //Si se inserta correctamente vaciamos los editText
                            editNombre.setText(null);
                            editApellidos.setText(null);
                            editNota.setText(null);
                            }
                        else
                            Toast.makeText(Activity2.this, R.string.insertNOK,Toast.LENGTH_SHORT).show();
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
                            buffer.append(BD.COL_1).append(" :").append(result.getString(0)).append("\n");
                            buffer.append(BD.COL_2).append(" :").append(result.getString(1)).append("\n");
                            buffer.append(BD.COL_3).append(" :").append(result.getString(2)).append("\n");
                            buffer.append(BD.COL_4).append(" :").append(result.getString(3)).append("\n\n");
                        }
                        //Mostrar todos los datos
                        mostrarMensaje(BD.TABLE_NAME,buffer.toString());
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
