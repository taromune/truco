package org.android.db;

import java.io.Serializable;


//Clase para tener una lista más fácil de usar
public class Lista implements Serializable{
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre(){
		return this.nombre;
	}
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String nota;
	Lista ()
	{
		super();
	}
	Lista(String nombre,String nota) 
	{
		super();
		this.nombre = nombre;
		this.nota = nota;
	}
	public long getId() {
		// TODO Auto-generated method stub
		return serialVersionUID;
	}

}