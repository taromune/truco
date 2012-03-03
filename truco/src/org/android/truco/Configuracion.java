package org.android.truco;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.Context;
import android.content.SharedPreferences;

public class Configuracion extends Activity {
	Button saveBoton;
	Button cancelBoton;
	EditText et;
	Spinner spin;
	SharedPreferences prefs;
	private static int radio=0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
        saveBoton = (Button)findViewById(R.id.button1);
        cancelBoton = (Button)findViewById(R.id.button2);
        
        et = (EditText)findViewById(R.id.editText1);
        
        prefs = getSharedPreferences("preferences",Context.MODE_PRIVATE);
        
        et.setText(String.valueOf(prefs.getLong("rad", 100)));

        spin = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new MyOnItemSelectedListener());
        

        saveBoton.setOnClickListener(new OnClickSaveListener());

        cancelBoton.setOnClickListener(new OnClickCancelListener());
    }
    
    public class MyOnItemSelectedListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        	// Cuando se seleccione la unidad, cambiar el radio en funcion de lo que se elija
        	if(parent.getItemAtPosition(pos).toString().equals("m."))
        		radio = 1;
        	else if(parent.getItemAtPosition(pos).toString().equals("Km."))
        		radio = 1000;
        }

        public void onNothingSelected(AdapterView<?> parent) {
          // Si no hay nada seleccionado no se hace nada.
        }
    }
    
    public class OnClickSaveListener implements View.OnClickListener {

        public void onClick(View view) {
        	// Cuando se haga click en el boton guardar
        	SharedPreferences.Editor edit = prefs.edit();
        	if(et.getText().toString().equals(""))
        		Toast.makeText(saveBoton.getContext(), "Error. El radio tiene que ser un número.",Toast.LENGTH_SHORT).show();
        	else{
        		radio = radio * Integer.valueOf(et.getText().toString());
        		edit.putLong("rad", radio);
        		edit.commit();
            	Toast.makeText(saveBoton.getContext(), "Configuración guardada con exito.",Toast.LENGTH_SHORT).show();
            	finish();
        	}
        }
    }
    
    public class OnClickCancelListener implements View.OnClickListener {

        public void onClick(View view) {
        	// Cuando se haga click en el boton cancelar
        	finish();
        }
    }
    
    //Evento por si se cierra la aplicacion en este punto, para que se propage a la Activity padre
    @Override
    public void onDestroy(){
    	super.onDestroy();
    }
}