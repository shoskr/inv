package Class;

public class Inventario {

    private String idInventario ;
    private int Cinta_idCinta ;
    private String Contenido ;
    private String retencion ;
    private int Plataforma ;
    private String Fecha_Plataforma ;
    private String Fecha_Exp ;
    private String Fecha_ultim ;
    private int Pais_idPais ;
    private int Ubicacion_Bodega ;
    private int Destino_Actual ;
    private String Valija ;
    private String Continuacion ;
    private String Observaciones ;
    private String Solicitado ;
    private String Responsable ;
    private int Servidor ;
    private String Lugar_Requerido ;
    private String mes_anio ;

    public Inventario() {
    }

    public Inventario(String idInventario, int Cinta_idCinta, String Contenido, String retencion, int Plataforma, String Fecha_Plataforma, String Fecha_Exp, String Fecha_ultim, int Pais_idPais, int Ubicacion_Bodega, int Destino_Actual, String Valija, String Continuacion, String Observaciones, String Solicitado, String Responsable, int Servidor, String Lugar_Requerido, String mes_anio) {
        this.idInventario = idInventario;
        this.Cinta_idCinta = Cinta_idCinta;
        this.Contenido = Contenido;
        this.retencion = retencion;
        this.Plataforma = Plataforma;
        this.Fecha_Plataforma = Fecha_Plataforma;
        this.Fecha_Exp = Fecha_Exp;
        this.Fecha_ultim = Fecha_ultim;
        this.Pais_idPais = Pais_idPais;
        this.Ubicacion_Bodega = Ubicacion_Bodega;
        this.Destino_Actual = Destino_Actual;
        this.Valija = Valija;
        this.Continuacion = Continuacion;
        this.Observaciones = Observaciones;
        this.Solicitado = Solicitado;
        this.Responsable = Responsable;
        this.Servidor = Servidor;
        this.Lugar_Requerido = Lugar_Requerido;
        this.mes_anio = mes_anio;
    }
    
    

    public String getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(String idInventario) {
        this.idInventario = idInventario;
    }

    public int getCinta_idCinta() {
        return Cinta_idCinta;
    }

    public void setCinta_idCinta(int Cinta_idCinta) {
        this.Cinta_idCinta = Cinta_idCinta;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String Contenido) {
        this.Contenido = Contenido;
    }

    public String getRetencion() {
        return retencion;
    }

    public void setRetencion(String retencion) {
        this.retencion = retencion;
    }

    public int getPlataforma() {
        return Plataforma;
    }

    public void setPlataforma(int Plataforma) {
        this.Plataforma = Plataforma;
    }

    public String getFecha_Plataforma() {
        return Fecha_Plataforma;
    }

    public void setFecha_Plataforma(String Fecha_Plataforma) {
        this.Fecha_Plataforma = Fecha_Plataforma;
    }

    public String getFecha_Exp() {
        return Fecha_Exp;
    }

    public void setFecha_Exp(String Fecha_Exp) {
        this.Fecha_Exp = Fecha_Exp;
    }

    public String getFecha_ultim() {
        return Fecha_ultim;
    }

    public void setFecha_ultim(String Fecha_ultim) {
        this.Fecha_ultim = Fecha_ultim;
    }

    public int getPais_idPais() {
        return Pais_idPais;
    }

    public void setPais_idPais(int Pais_idPais) {
        this.Pais_idPais = Pais_idPais;
    }

    public int getUbicacion_Bodega() {
        return Ubicacion_Bodega;
    }

    public void setUbicacion_Bodega(int Ubicacion_Bodega) {
        this.Ubicacion_Bodega = Ubicacion_Bodega;
    }

    public int getDestino_Actual() {
        return Destino_Actual;
    }

    public void setDestino_Actual(int Destino_Actual) {
        this.Destino_Actual = Destino_Actual;
    }

    public String getValija() {
        return Valija;
    }

    public void setValija(String Valija) {
        this.Valija = Valija;
    }

    public String getContinuacion() {
        return Continuacion;
    }

    public void setContinuacion(String Continuacion) {
        this.Continuacion = Continuacion;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public String getSolicitado() {
        return Solicitado;
    }

    public void setSolicitado(String Solicitado) {
        this.Solicitado = Solicitado;
    }

    public String getResponsable() {
        return Responsable;
    }

    public void setResponsable(String Responsable) {
        this.Responsable = Responsable;
    }

    public int getServidor() {
        return Servidor;
    }

    public void setServidor(int Servidor) {
        this.Servidor = Servidor;
    }

    public String getLugar_Requerido() {
        return Lugar_Requerido;
    }

    public void setLugar_Requerido(String Lugar_Requerido) {
        this.Lugar_Requerido = Lugar_Requerido;
    }

    public String getMes_anio() {
        return mes_anio;
    }

    public void setMes_anio(String mes_anio) {
        this.mes_anio = mes_anio;
    }

    @Override
    public String toString() {
        return "Inventario{" + "idInventario=" + idInventario + ", Cinta_idCinta=" + Cinta_idCinta + ", Contenido=" + Contenido + ", retencion=" + retencion + ", Plataforma=" + Plataforma + ", Fecha_Plataforma=" + Fecha_Plataforma + ", Fecha_Exp=" + Fecha_Exp + ", Fecha_ultim=" + Fecha_ultim + ", Pais_idPais=" + Pais_idPais + ", Ubicacion_Bodega=" + Ubicacion_Bodega + ", Destino_Actual=" + Destino_Actual + ", Valija=" + Valija + ", Continuacion=" + Continuacion + ", Observaciones=" + Observaciones + ", Solicitado=" + Solicitado + ", Responsable=" + Responsable + ", Servidor=" + Servidor + ", Lugar_Requerido=" + Lugar_Requerido + ", mes_a\u00f1o=" + mes_anio + '}';
    }
    
    
}
