package com.example.health;

public class Productosclass {
    private String Nombre, Descripcion;
    private int imgproducto;

    public Productosclass() {
    }

    public Productosclass(String nombre, String descripcion, int imgproducto) {
        Nombre = nombre;
        Descripcion = descripcion;
        this.imgproducto = imgproducto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getImgproducto() {
        return imgproducto;
    }

    public void setImgproducto(int imgproducto) {
        this.imgproducto = imgproducto;
    }
}
