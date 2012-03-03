package org.android.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ListaDB{
	private SQLiteDatabase db;
	private String DATABASE_TABLE = "lista";
	private Context ctx;

	public ListaDB(Context context) {
		this.ctx= context;
	}
	//Establecemos los nombres de las columnas
	public static final String KEY_ID = "_id";
	public final static String KEY_COL1 = "nombre";
	public final static String KEY_COL2 = "nota";
	 
	//Array de strings para su uso en los diferentes métodos
	private static final String[] cols = new String[] { KEY_ID, KEY_COL1, KEY_COL2 };
/**
 * Restricciones de la tabla Lista
 * 1. El nombre debe ser unico
 */
	public boolean listaCorrecta(String nombre)
	{
		//Obtenemos todas las listas
		List <Lista> listas = new ArrayList<Lista>();
		listas = this.getListas();
		for (Lista l:listas)
		{
			if (l.getNombre().equals(nombre))
			{
					return false; //El nombre ya está repetido
					//TODO: raise error nombre repetido
			}
		}
		return true;
	}
	/**
	* INSERTAR NUEVA Lista
	* */
	public long insertlista(String nombre, String nota) 
	{
		Log.v("insertando en lista",nombre+" "+nota);
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_COL1, nombre);
		newValues.put(KEY_COL2, nota);
		//Restricciones propias de la tabla
		this.listaCorrecta(nombre);
		Log.v("insertando en lista","lista correcta");
		return db.insert(DATABASE_TABLE, null, newValues);
	} 
	/**
	* BORRAR lista que tenga el identificador _id
	* */
	public boolean removelista(long _id) {
		return db.delete(DATABASE_TABLE, KEY_ID + "=" + _id, null) > 0;
	}
	 
	/**
	* ACTUALIZAR la lista con el _id indicado
	* */
	public boolean updatelista(Integer _id, String nombre, String nota) 
	{
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_COL1,nombre);
		newValues.put(KEY_COL2, nota);
		//Comprobamos que los valores son correctos
		//no hemos cambiado los datos
		Lista l=getLista(_id);
		if (!l.getNombre().equals(nombre))
		{
			this.listaCorrecta(nombre); //Si el nombre ya esta repetido saltará un error
			return db.update(DATABASE_TABLE, newValues, KEY_ID + "=" + _id, null) > 0;
		}
		return true; //Si no hay cambios, no hacemos nada pero indicamos que la operación es correcta
	}	
	public Lista getLista(long _id) 
	{
		
		Lista lista = new Lista();
		Cursor result = db.query(true, DATABASE_TABLE,
		cols,
		KEY_ID + "=" + _id, null, null, null,
		null, null);
		if ((result.getCount() == 0) || !result.moveToFirst()) 
		{
		//Si la Lista no existe, devuelve una Lista con valores -1 y -1
			lista = new Lista("-1","-1"); 
		} else 
		{
			if (result.moveToFirst()) 
			{
				lista = new Lista(
				result.getString(result.getColumnIndex(KEY_COL1)),
				result.getString(result.getColumnIndex(KEY_COL2))
			);}
		}
		return lista;
	}	 
	public List<Lista> getListas() 
	{
		Log.v("listaDB","en getlistas");
		ArrayList<Lista> listas = new ArrayList<Lista>();
		Cursor result = db.rawQuery("select * from lista", null);
		Log.d("contando listas",Integer.toString(result.getCount()));
		if (result.moveToFirst())
		do
		{
			Log.v("getLista",result.getString(result.getColumnIndex(KEY_COL1)));
			listas.add(new Lista(
			result.getString(result.getColumnIndex(KEY_COL1)),
			result.getString(result.getColumnIndex(KEY_COL2))
					)
			);
		} while(result.moveToNext());
		return listas;
	} 	
}