
package Class;

public class Cinta {
    private int idCinta;
    private String modelo;
    private String Marca;
    private String Categoria;

    public Cinta() {
    }

    public Cinta(int idCinta, String modelo, String Marca, String Categoria) {
        
        setIdCinta(idCinta);
        setModelo(modelo);
        setMarca(Marca);
        setCategoria(Categoria);
             
    }

    public int getIdCinta() {
        return idCinta;
    }

    public void setIdCinta(int idCinta) {
        this.idCinta = idCinta;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    @Override
    public String toString() {
        return "Cinrta{" + "idCinta=" + idCinta + ", modelo=" + modelo + ", Marca=" + Marca + ", Categoria=" + Categoria + '}';
    }
    
    
}
