package com.coursera.contactpageprogramabit;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class ContactDetail extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcion;
    private TextView tvFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_detail);

        Bundle datos = getIntent().getExtras();

        String nombre = datos.getString(getResources().getString(R.string.pnombre).toString());
        String telefono = datos.getString(getResources().getString(R.string.ptelefono).toString());
        String eMail = datos.getString(getResources().getString(R.string.pemail).toString());
        String fecha = datos.getString(getResources().getString(R.string.pfecha).toString());
        String descripcion = datos.getString(getResources().getString(R.string.pdescripcion).toString());


        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvFecha = (TextView) findViewById(R.id.etFechaNac);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);

        tvNombre.setText(nombre);
        tvTelefono.setText(telefono);
        tvEmail.setText(eMail);
        tvDescripcion.setText(descripcion);
        tvFecha.setText(fecha);

        android.widget.Button btn = (android.widget.Button) findViewById(R.id.btnEditar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (ContactDetail.this,  MainActivity.class);

                TextView txtNom = findViewById(R.id.tvNombre);
                TextView txtDescrip = findViewById(R.id.tvDescripcion);
                TextView txteMail = findViewById(R.id.tvEmail);
                TextView txttel = findViewById(R.id.tvTelefono);
                TextView txtFecha = findViewById(R.id.etFechaNac);
                intent.putExtra(getResources().getString(R.string.pdescripcion), txtDescrip.getText().toString());
                intent.putExtra(getResources().getString(R.string.ptelefono), txttel.getText().toString());
                intent.putExtra(getResources().getString(R.string.pfecha), txtFecha.getText().toString());
                intent.putExtra(getResources().getString(R.string.pnombre), txtNom.getText().toString());
                intent.putExtra(getResources().getString(R.string.pemail), txteMail.getText().toString());
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(ContactDetail.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    public void call(View v){
        String telefono = tvTelefono.getText().toString();
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono)));
    }

    public void sendeMail(View v){
        String mail = tvEmail.getText().toString();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, mail);
        startActivity(Intent.createChooser(emailIntent, "Email "));
    }


}
