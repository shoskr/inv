
package Class;

public class Ubicacion {
    private int idubicacion;
    private String Lugar;

    public Ubicacion() {
    }

    public Ubicacion(int idubicacion, String Lugar) {
        this.idubicacion = idubicacion;
        this.Lugar = Lugar;
    }

    public int getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(int idubicacion) {
        this.idubicacion = idubicacion;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String Lugar) {
        this.Lugar = Lugar;
    }

    @Override
    public String toString() {
        return "Ubicacion{" + "idubicacion=" + idubicacion + ", Lugar=" + Lugar + '}';
    }
    
    
}
