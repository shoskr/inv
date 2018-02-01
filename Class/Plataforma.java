
package Class;

public class Plataforma {
    private int idPlataforma;
    private String NomPlataforma;

    public Plataforma() {
    }

    public Plataforma(int idPlataforma, String NomPlataforma) {
        this.idPlataforma = idPlataforma;
        this.NomPlataforma = NomPlataforma;
    }

    public int getIdPlataforma() {
        return idPlataforma;
    }

    public void setIdPlataforma(int idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    public String getNomPlataforma() {
        return NomPlataforma;
    }

    public void setNomPlataforma(String NomPlataforma) {
        this.NomPlataforma = NomPlataforma;
    }

    @Override
    public String toString() {
        return "Plataforma{" + "idPlataforma=" + idPlataforma + ", NomPlataforma=" + NomPlataforma + '}';
    }
    
    
}
