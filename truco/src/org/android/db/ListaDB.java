package org.android.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ListaDB{
	
    
    // declare database fields
    public static final String TBL_LISTA = "lista";
    public static final String COL_ID = "_id";
    public static final String COL_NOMBRE = "nombre";
    public static final String COL_NOTA = "notas";
    
    // projection on all columns
    private static final String[] PROJECTION_ALL = new String[] {
            COL_ID, COL_NOMBRE, COL_NOTA
    };
    
    // query output type
    public static final int QUERY_TYPE_STRING_ARRAY = 0x01;
    public static final int QUERY_TYPE_USERINFO_OBJ = 0x02;
    
    // declared fields
    private Context mContext;
    private SQLiteDatabase mDb;
    private DBHelper mDbHelper;
    
    /*
     * constructor
     */
    public ListaDB(Context c) {
            mContext = c;
    }
    
    /*
     * open database connection
     */
    public ListaDB open() throws SQLException {
            mDbHelper = new DBHelper(mContext);
            mDb = mDbHelper.getWritableDatabase();
            return this;
    }
    
    /*
     * close database connection
     */
    public void close() {
            mDbHelper.close();
    }
    
    /*
     * insert a record to db
     */
    public long insertLista(String nombre, String nota) {
            return mDb.insert(TBL_LISTA, null, createContentValues(nombre, nota));
    }
    
    /*
     * update a record to db
     */
    public long updateLista(int id, String nombre, String nota) {
            return mDb.update(TBL_LISTA, createContentValues(nombre, nota), COL_ID + "=" + id, null);
    }
    
    /*
     * delete a record from db
     */
    public long deleteLista(int id) {
            return mDb.delete(TBL_LISTA, COL_ID + "=" + id, null);
    }
    
    /*
     * query all records
     */
    public List<Lista> getListas() {
            // get query cursor
            Cursor queryCursor = mDb.query(TBL_LISTA, PROJECTION_ALL, null, null, null, null, null);
            // just return null if cursor null
            if(queryCursor == null) {
                    Log.d("ListaDB", "UserDbAdapter.fetchAllUsers(): queryCursor = null "); 
                    return null;
            }
            // init list to hold user info
            List<Lista> listUsers = new ArrayList<Lista>();
            // set cursor to the first element
            queryCursor.moveToFirst();
            // if cursor is not the last element
            while(queryCursor.isAfterLast() == false) {
                    // add new user info
                    listUsers.add(new Lista(
                                    // get user name from cursor
                                    queryCursor.getString(queryCursor.getColumnIndexOrThrow(COL_NOMBRE)),
                                    // get user age from cursor
                                    queryCursor.getString(queryCursor.getColumnIndexOrThrow(COL_NOTA))
                    ));
                    // move cursor to next item
                    queryCursor.moveToNext();
            }
            // check if cursor is still opened and not null
            if(queryCursor != null && !queryCursor.isClosed()) {
                    // close it to avoid memory leak
                    queryCursor.close();
            }
            Log.d("ListaDB", "UserDbAdapter.fetchAllUsers(): listUsers.size() = " + listUsers.size());
            // return user list
            return listUsers;
    }
    
    /*
     * query one record
     */
    public Object getLista(int id, int type) {
            // query a cursor on identified user
            Cursor c = mDb.query(true, TBL_LISTA, PROJECTION_ALL, COL_ID + "=" + id, null, null, null, null, null);
            // return null if no record avaiable
            if(c == null) {
                    return null;
            }
            
            Object objOut = null;
            
            if(type == QUERY_TYPE_STRING_ARRAY) {
                    // create array to hold user info
                    String[] user_info = new String[4];
                    user_info[0] = String.valueOf(id);
                    user_info[1] = c.getString(c.getColumnIndexOrThrow(COL_NOMBRE));
                    user_info[2] = c.getString(c.getColumnIndexOrThrow(COL_NOTA));
                    //TODO: mandar el numero de productos de la lista
                    objOut = user_info;
            } else {
                    // create UserInfo object
                    Lista l = new Lista(
                                    c.getString(c.getColumnIndexOrThrow(COL_NOMBRE)),
                                    c.getString(c.getColumnIndexOrThrow(COL_NOTA))
                                    );
                    objOut = l;
            }
            // close cursor 
            c.close();
            
            // return user info
            return objOut;          
    }
    
    
    
    /*
     * create ContentValues object to use for db transaction
     */
    private ContentValues createContentValues(String nombre, String nota) {
            // init a ContentValues object
            ContentValues cv = new ContentValues();
            // put data
            cv.put(COL_NOMBRE, nombre);
            cv.put(COL_NOTA, nota);
            // return object
            return cv;
    }
    
    

}