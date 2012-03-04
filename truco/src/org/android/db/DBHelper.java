package org.android.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//Esta clase maneja toda la base de datos en conjunto... Cada tabla se manejará en una clase aparte
public class DBHelper extends SQLiteOpenHelper{
	private Context mCtx = null;
	private static final String DB_NAME = "truco_db";
	private static final int DB_version = 1;

    //Sentencia SQL para crear las tablas de la base de datos
    private String[] sqlCreate = 
    	{
    		"CREATE TABLE if not exists "+DB_NAME+".lista (_id NUMBER primary key asc,nombre TEXT unique, nota TEXT)",
    		"CREATE TABLE if not exists "+DB_NAME+".producto (nombre TEXT,_id NUMBER,_id_lista NUMBER,_id_marca,_id_categoria," +
    				"cantidad NUMBER,unidad TEXT)," +
    				"FOREIGN KEY(_id_lista) REFERENCES lista(_id)," +
    				"FOREIGN KEY(_id_marca) REFERENCES marca(_id)),",
    				"FOREIGN KEY(_id_categoria) REFERENCES categoria(_id))," +
    				"PRIMARY KEY (_id,_id_lista)",
    		"CREATE TABLE if not exists "+DB_NAME+".marca (_id NUMBER, nombre TEXT,)",
    		"Create TABLE if not exists "+DB_NAME+".categoria (_id NUMBER primary key asc, nombre TEXT)",
    		"Create TABLE if not exists "+DB_NAME+".supermercado (_id NUMBER primary key asc, nombre TEXT)"
    	};
    private String[] sqlDelete =
    	{
    		"Drop table if exists "+DB_NAME+".lista",
    		"Drop table if exists "+DB_NAME+".producto",
    		"Drop table if exists "+DB_NAME+".marca",
    		"Drop table if exists "+DB_NAME+".categoria",
    		"Drop table if exists "+DB_NAME+".supermercado"
    	};
	/**
	* Constructor
	* Toma referencia hacia el contexto de la aplicación que lo invoca para poder acceder a los 'assets' y 'resources' de la aplicación.
	* Crea un objeto DBOpenHelper que nos permitirá controlar la apertura de la base de datos.
	* @param context
	*/
	public DBHelper(Context context) {	 
		super(context, DB_NAME, null, DB_version);
		Log.i("DBHelper","Creando la base de datos"+DB_NAME);
	}
	public void createTables(SQLiteDatabase db){
		for (String a:this.sqlCreate)
		{
			Log.i("CreateTables",a);
			db.execSQL(a);
		}
		//Creamos varias tuplas de lista.. añadir cuando tengáis nuevas clases
		Log.i("DBHelper","Creando tablas de lista");
		ContentValues values = new ContentValues();   
	    values.put("nombre","lista1" );   
	    values.put("nota", "nota1");
	    db.insert("lista", null, values);
	    values.clear();
	    values.put("nombre","lista2" );   
	    values.put("nota", "nota2");
	    db.insert("lista", null, values);
	}
	public void deleteTables(SQLiteDatabase db){
		for (String a:this.sqlDelete)
		{
			db.execSQL(a);
		}
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("DBHelper","Oncreate");
		createTables(db); 
	}
	@Override
	//TODO:mejorar esto.. ahora tan solo borramos la base de datos y la rehacemos
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		deleteTables(db);
		createTables(db);		
	}
}
