package org.android.truco;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.android.db.Lista;


//TODO: marcar con un * todos los campos que sean obligatorios
public class NuevaLista extends Activity{
    Button guardar;
    Button limpiar;
    EditText nombre;
    EditText notas;
    ListaDB lista;
	@Override
    public void onCreate(Bundle savedInstanceState) {
	       final String LOG_TAG = "NuevaLista";  
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_lista);
        //Inicializamos la base de datos
   //esperamos a que nos pulsen el boton guardar
        guardar = (Button)findViewById(R.id.btn_guardar);
        nombre = (EditText)findViewById(R.id.nombre_lista);
        notas = (EditText)findViewById(R.id.TxtNotas);

        this.guardar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
            	try{
            		guardarLista();
            	    mostrarMensaje(R.string.add_lista_ok);
            	    //¿Usamos una pantalla intermedia? 3opciones, usar_lista,añadir_mas,editar,menu
                    //Intent intent = new Intent(NuevaLista.this, MisListas.class);
                    //startActivity(intent);            	
                }
            	catch (SQLException e) {
            		Log.e(LOG_TAG,"Fallo al insertar");
            		//Si falla, mostramos un mensaje de alerta
            		//TODO: falta poner el icono de fallo y mejorar esto
            		int msg=R.string.add_lista_fail;
            		mostrarAlerta(msg);
            	}
            }
        });
    }
	void mostrarAlerta(int msg)
	{
		AlertDialog alerta= new AlertDialog.Builder(NuevaLista.this).create();
	        alerta.setTitle(R.string.error_title);
	        alerta.findViewById(msg);
	        alerta.setButton(-1,getResources().getString(R.string.atras), new DialogInterface.OnClickListener() {
	          public void onClick(DialogInterface dialog, int which) {
	            return;
	        } }); 
	        alerta.show();
	}
	//Mostramos un Toast mensaje.. falta refactorizar para poner estas cosas en una clase mensajes
	// Tanto el mensaje toast como el dialog... ¿Clase static para llamarlo mejor tipo Message.toast(stringdelmensaje)
	private void mostrarMensaje(int addListaOk) {
	    Toast mensajeOk = new Toast(getApplicationContext()); 
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.mensaje_toast,
                        (ViewGroup) findViewById(R.id.lytLayout));
        TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
        txtMsg.setText(addListaOk);
        mensajeOk.setDuration(60);
        mensajeOk.setView(layout);
        mensajeOk.show();				
	}
    private void guardarLista(){
    	//Comprobamos que podemos hacer el guardado
    	// validar campos
    	if (!this.validarCampos())
    	{
			this.mostrarAlerta(R.string.obligatorio);   		
    	}
    	Log.e("insertando","Nombre: "+this.nombre.getText().toString()+" nota: "+this.notas.getText().toString());
    	lista.insertlista(this.nombre.getText().toString(),this.notas.getText().toString());
    	mostrarMensaje(R.string.add_lista_ok);
    }
    private boolean validarCampos() {
		// TODO Auto-generated method stub
		if (this.nombre.toString().isEmpty())
			return false;
		return true;
	}
	@Override
    protected void onDestroy() {
        super.onDestroy();
    }
}