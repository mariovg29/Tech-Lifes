package constructoreslistas;

import java.util.ArrayList;

public class Listanoimg {
     String NOMBRE, DESCRIPCION;
     int PRECIO;



    public Listanoimg(String NOMBRE, String DESCRIPCION, int PRECIO) {
        this.NOMBRE = NOMBRE;
        this.DESCRIPCION = DESCRIPCION;
        this.PRECIO = PRECIO;
    }

    public Listanoimg() {

    }


    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(int PRECIO) {
        this.PRECIO = PRECIO;
    }
}
