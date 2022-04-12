package  cl.tbd.ejemplo1.models;

import com.google.gson.annotations.SerializedName;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Tarea")

public class TareaMongo {
    
    @Id
    private Long id;
    private String titulo;
    private String descripcion;
    private Long id_estado;
    private Long id_emergencia;
    private Integer cant_voluntarios;
    private Double latitud;
    private Double longitud;
    private boolean deleted;
    

    //***** GETTERS *****//

    public Long getId(){
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public Long getEstadoId(){
        return id_estado;
    }

    public Long getEmergenciaId(){
        return id_emergencia;
    }

    public Integer getCant_Voluntarios(){
        return cant_voluntarios;
    }

    public Double getLatitud(){
        return latitud;
    }
    public Double getLongitud(){
        return longitud;
    }

    public boolean getDelete(){
        return deleted;
    }

    //***** SETTERS *****//

    public void setId(Long id){
        this.id = id;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public void setEstadoId(Long id){
        this.id_estado = id;
    }

    public void setEmergenciaId(Long id){
        this.id_emergencia = id;
    }

    public void setCant_Voluntarios(Integer qty){
        this.cant_voluntarios = qty;
    }

    public void setLatitud(Double latitud){
        this.latitud = latitud;
    }
    public void setLongitud(Double longitud){
        this.longitud = longitud;
    }

    public void setDelete(boolean deleted){
        this.deleted = deleted;
    }
}
