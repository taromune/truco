package org.android.db;

/*
 * 
 * Roberto.. lo comento para poder probar el resto.. iba a arreglarlo.. pero te lo dejo a ti xD xD
 * Asi que mañana descomenta y listo ;)
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ProductosDB extends DBHelper {

	public ProductosDB(Context context) {
		super(context);
	}
	private SQLiteDatabase db;
	private String DATABASE_TABLE = "Productos";
	private Context ctx;


	//Establecemos los nombres de las columnas
	public static final String KEY_ID = "_id";
	public static final String KEY_ID_lista = "_id_lista";
	public static final String KEY_ID_marca = "_id_marca";
	public static final String KEY_ID_categoria = "_id_categoria";
	public final static String KEY_COL1 = "nombre";
	public final static String KEY_COL2 = "cantidad";
	public final static String KEY_COL3 = "unidad";
	 
	//Array de strings para su uso en los diferentes métodos
	private static final String[] cols = new String[] { KEY_ID, KEY_COL1, KEY_COL2 };
/**
 * Restricciones de la tabla Producto
 * 1. El nombre debe ser unico
 */

/*
	public boolean productoCorrecto(String nombre)
	{
		//Obtenemos todas los productos
		List <Producto> productos = new ArrayList<Producto>();
		productos = this.getProductos();
		for (Producto l:lista)
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
	* INSERTAR NUEVO producto
	* */
/*
	public long insertProdcuto(String nombre, String nota) 
	{
		Log.v("insertando en lista",nombre+" "+nota);
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_COL1, nombre);
		newValues.put(KEY_COL2, nota);
		//Restricciones propias de la tabla
		this.productoCorrecta(nombre);
		Log.v("insertando en lista","lista correcta");
		return db.insert(DATABASE_TABLE, null, newValues);
	} 
	/**
	* BORRAR producto que tenga el identificador _id
	* */
/*
	public boolean removeProducto(long _id) {
		return db.delete(DATABASE_TABLE, KEY_ID + "=" + _id, null) > 0;
	}
	 
	/**
	* ACTUALIZAR la lista con el _id indicado
	* */
/*
	public boolean updateProdcuto(Integer _id, String nombre, String nota) 
	{
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_COL1,nombre);
		newValues.put(KEY_COL2, nota);
		//Comprobamos que los valores son correctos
		//no hemos cambiado los datos
		Producto p = getProducto(_id);
		if (!p.getNombre().equals(nombre))
		{
			this.productoCorrecto(nombre); //Si el nombre ya esta repetido saltará un error
			return db.update(DATABASE_TABLE, newValues, KEY_ID + "=" + _id, null) > 0;
		}
		return true; //Si no hay cambios, no hacemos nada pero indicamos que la operación es correcta
	}	
	
	
	public Producto getProducto(long _id) 
	{
		
		Producto lista = new Producto;
		Cursor result = db.query(true, DATABASE_TABLE,
		cols,
		KEY_ID + "=" + _id, null, null, null,
		null, null);
		if ((result.getCount() == 0) || !result.moveToFirst()) 
		{
		//Si la Lista no existe, devuelve una Lista con valores -1 y -1
			producto = new Producto("-1", "-1", "-1"); 
		} else 
		{
			if (result.moveToFirst()) 
			{
				producto = new Producto(
				result.getString(result.getColumnIndex(KEY_COL1)),
				result.getString(result.getColumnIndex(KEY_COL2)),
				result.getString(result.getColumnIndex(KEY_COL3)));
			}
		}
		return producto;
	}	 
	public List<Producto> getProductos() 
	{
		Log.v("listaDB","en getlistas");
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Cursor result = db.rawQuery("select * from lista", null);
		Log.d("contando productos",Integer.toString(result.getCount()));
		if (result.moveToFirst())
		do
		{
			Log.v("getProducto",result.getString(result.getColumnIndex(KEY_COL1)));
			productos.add(new Producto(
			result.getString(result.getColumnIndex(KEY_COL1)),
			result.getString(result.getColumnIndex(KEY_COL2)),
			result.getString(result.getColumnIndex(KEY_COL3))
					)
			);
		} while(result.moveToNext());
		return productos;
	} 	
}
*/