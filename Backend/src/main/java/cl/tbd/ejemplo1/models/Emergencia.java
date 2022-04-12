package  cl.tbd.ejemplo1.models;
//import org.postgis.PGgeometry;
//import org.postgis.Point;
public class Emergencia{
    private Long id;
    private String titulo;
    private String direccion;
    private String descripcion;
    private Long id_institucion;
    private Double latitud;
    private Double longitud;
    private Boolean activo;
    private boolean deleted;
    //private PGeometry ubicacion;

    //***** GETTERS *****//
    public Long getId() {
        return id;
    }
    public String getTitulo(){
        return titulo;
    }
    public String getDireccion(){
        return direccion;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public Long getInstitucionId(){
        return id_institucion;
    }
    public Boolean getActivo(){
        return activo;
    }
    public boolean getDeleteEstado(){
        return deleted;
    }
    public Double getLatitud(){
        return latitud;
    }
    public Double getLongitud(){
        return longitud;
    }

    //***** SETTERS *****//
    public void setId(Long id){
        this.id = id;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public void setInstitucionId(Long id_institucion){
        this.id_institucion = id_institucion;
    }
    public void setActivo(boolean activo){
        this.activo=activo;
    }
    public void setDeleteEstado(boolean deleted){
        this.deleted = deleted;
    }
    public void setLatitud(double latitud){
        this.latitud = latitud;
    }
    public void setLongitud(double longitud){
        this.longitud = longitud;
    }
}    


