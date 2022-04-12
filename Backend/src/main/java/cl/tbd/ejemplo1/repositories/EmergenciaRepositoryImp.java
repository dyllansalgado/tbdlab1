package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Emergencia;
import cl.tbd.ejemplo1.models.Eme_Habilidad;
import cl.tbd.ejemplo1.models.Habilidad;
import cl.tbd.ejemplo1.repositories.HabilidadRepository;
import cl.tbd.ejemplo1.repositories.Eme_HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class EmergenciaRepositoryImp implements EmergenciaRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public Long countEmergencia(){
        String query = "select count(*) from emergencia";
        Connection conn = sql2o.open();
        Long resultado = (Long) conn.createQuery(query,true).executeAndFetchFirst(Long.class);
        return resultado + 1; 
    }

    @Override
    public Emergencia createEmergencia(Emergencia emergencia){
        Long id_prueba =countEmergencia();
        String query = "INSERT INTO emergencia (id,titulo,descripcion,direccion,id_institucion,activo) values (:id,:titulo,:direccion,:descripcion,:id_institucion,:activo)";
        try(Connection conn = sql2o.open()){
            conn.createQuery(query,true).addParameter("id",id_prueba).addParameter("titulo", emergencia.getTitulo())
                            .addParameter("descripcion", emergencia.getDescripcion())
                            .addParameter("id_institucion", emergencia.getInstitucionId())
                            .addParameter("direccion", emergencia.getDireccion())
                            .addParameter("activo", emergencia.getActivo())
                            .executeUpdate().getKey();
            emergencia.setId(id_prueba);
            return emergencia;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Emergencia getEmergencia(Long id){
        String query = "select * from emergencia where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Emergencia.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @Override
    public List<Emergencia> getEmergencias() {
        System.out.println("holaaa");
        String query = "select emergencia.id, emergencia.titulo, emergencia.descripcion, emergencia.direccion,emergencia.id_institucion,emergencia.activo, st_x(st_astext(emergencia.ubicacion)) AS longitud, st_y(st_astext(emergencia.ubicacion)) AS latitud from emergencia";
        
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).executeAndFetch(Emergencia.class);
         }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteEmergencia(Long id){
        String query = "update emergencia set deleted = true where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            id = conn.createQuery(query).addParameter("id",id).executeUpdate().getKey(Long.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Emergencia updateEmergencia( Emergencia emergencia,Long id){
        String query = "update emergencia set titulo = :titulo,descripcion = :descripcion, direccion = :direccion,id_institucion = :id_institucion,activo = :activo where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
          Long insertedid = (Long) conn.createQuery(query).addParameter("id", id)
            .addParameter("titulo", emergencia.getTitulo())
            .addParameter("descripcion", emergencia.getDescripcion())
            .addParameter("id_institucion", emergencia.getInstitucionId())
            .addParameter("direccion", emergencia.getDireccion())
            .addParameter("activo", emergencia.getActivo())
            .executeUpdate().getKey(Long.class);
            emergencia.setId(insertedid);
            return emergencia;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public List<Habilidad> getHabilidadesEmergencia(Long id_em){
        String query = "SELECT eme_habilidad.id , habilidad.nombreH FROM eme_habilidad,habilidad WHERE habilidad.id=eme_habilidad.id_habilidad and eme_habilidad.id_emergencia=:id_em ";
        try(Connection conn = sql2o.open()){
            System.out.println(conn.createQuery(query,true).addParameter("id_em", id_em).executeAndFetch(Habilidad.class));
            List<Habilidad> habilidades=conn.createQuery(query).addParameter("id_em", id_em).executeAndFetch(Habilidad.class);
            return(habilidades);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}