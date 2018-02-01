
package Class;


public class Servidor {
    private int idServidor;
    private String Nombre;

    public Servidor() {
    }

    public Servidor(int idServidor, String Nombre) {
        this.idServidor = idServidor;
        this.Nombre = Nombre;
    }

    public int getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(int idServidor) {
        this.idServidor = idServidor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "Servidores{" + "idServidor=" + idServidor + ", Nombre=" + Nombre + '}';
    }
    
    
}
