package org.android.truco;

import org.android.db.DBHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuPpal extends Activity{
	//Inicializamos los botones de la interfaz
	Button btn_nueva_lista;
	Button btn_comprar;
	Button btn_consultar_lista;
	Button btn_settings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);	
		DBHelper helper = new DBHelper(getBaseContext());
		SQLiteDatabase db = helper.getWritableDatabase();
        setContentView(R.layout.menu_ppal); 
        // preparamos los botones
    	btn_nueva_lista=(Button)findViewById(R.id.Btn_nueva_lista);
    	btn_comprar=((Button)findViewById(R.id.Btn_comprar));
    	btn_consultar_lista=((Button)findViewById(R.id.Btn_consultar_lista));
    	btn_settings = (Button)findViewById(R.id.Btn_settings);
        this.btn_nueva_lista.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MenuPpal.this, NuevaLista.class);
              /*
                //Pasamos la información que querramos darle a la nueva actividad
                Bundle bundle = new Bundle();
                bundle.putString("NOMBRE", txtNombre.getText().toString());
                intent.putExtras(bundle);
               */
                startActivity(intent);
            }
        });
/**
        this.btn_consultar_lista.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        startActivity(new Intent( MenuPpal.this, MisListas.class));
		          }
		});          
        this.btn_comprar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MenuPpal.this, Comprar.class);
                startActivity(intent);
            }
        });
*/

    }
}