package cl.tbd.ejemplo1.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import cl.tbd.ejemplo1.models.Voluntarios;
import cl.tbd.ejemplo1.models.Tarea;
import cl.tbd.ejemplo1.models.Vol_habilidad;
import cl.tbd.ejemplo1.models.Eme_Habilidad;
import cl.tbd.ejemplo1.models.Tarea_Habilidad;


@Repository
public class VoluntarioRepositoryImp implements VoluntarioRepository {

    @Autowired
     private Sql2o sql2o;

    @Override
    public Long countVoluntarios(){
        String query = "select count(*) from voluntario";
        Connection conn = sql2o.open();
        Long resultado = (Long) conn.createQuery(query,true).executeAndFetchFirst(Long.class);
        return resultado + 1; 
    }
    
    @Override
    public Voluntarios createVoluntario(Voluntarios voluntario){
        Long id_vol = countVoluntarios();
        String coordenada = voluntario.getLongitud().toString() + " " + voluntario.getLatitud().toString();
        String query = "INSERT INTO voluntario (id,nombre,disponibilidad,telefono,direccion,correo_electronico,rut,ubicacion) values (:id,:nombre,:disponibilidad,:telefono," + 
        ":direccion,:correo_electronico,:rut,NOW(), ST_GeomFromText('POINT(" + coordenada + ")', 4326)))";
        try(Connection conn = sql2o.open()){
            conn.createQuery(query,true)
                            .addParameter("id",id_vol)
                            .addParameter("disponibilidad", voluntario.getDisponibilidad())
                            .addParameter("nombre", voluntario.getNombre())
                            .addParameter("telefono", voluntario.getTelefono())
                            .addParameter("direccion", voluntario.getDireccion())
                            .addParameter("correo_electronico", voluntario.getCorreo_electronico())
                            .addParameter("rut", voluntario.getRut())
                            .executeUpdate().getKey();
        
            voluntario.setId(id_vol);
            return voluntario;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Voluntarios getVoluntario(Long id){
        String query = "select nombre, disponibilidad, telefono, direccion, correo_electronico, st_x(st_astext(ubicacion)) AS longitud, st_y(st_astext(ubicacion)) AS latitud from voluntario where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Voluntarios.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Voluntarios> getAllVoluntarios() {
        String query = "select nombre, disponibilidad, telefono, direccion, correo_electronico, st_x(st_astext(location)) AS longitud, st_y(st_astext(location)) AS latitud" + "from voluntario where deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).executeAndFetch(Voluntarios.class);
         }
         catch (Exception e){
             System.out.println(e.getMessage());
             return null;
         }
     }

    @Override
    public boolean deleteVoluntario(Long id){
        String query = "update voluntario set deleted=true where id=:id and deleted=false";
        try(Connection conn = sql2o.open()){
            conn.createQuery(query).addParameter("id",id).executeUpdate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Voluntarios updateVoluntario( Voluntarios voluntario,Long id){
        String query = "update voluntario set nombre = :nombre , disponibilidad = :disponibilidad , telefono = :telefono, direccion = :direccion, correo_electronico = :correo_electronico, rut = :rut, where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query).addParameter("id", id)
            .addParameter("id",voluntario.getId())
            .addParameter("nombre", voluntario.getNombre())
            .addParameter("disponibilidad", voluntario.getDisponibilidad())
            .addParameter("telefono", voluntario.getTelefono())
            .addParameter("direccion", voluntario.getDireccion())
            .addParameter("correo_electronico", voluntario.getCorreo_electronico())
            .addParameter("rut", voluntario.getRut())
            .executeUpdate().getKey(Long.class);
            voluntario.setId(insertedid);
        
            return voluntario;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

     }
     @Override
     public List<Voluntarios> getVoluntarioxTarea(Long id_tarea) {
         String query = "select distinct voluntario.id,voluntario.nombre,voluntario.disponibilidad,voluntario.telefono,voluntario.correo_electronico,voluntario.direccion,voluntario.rut from voluntario,vol_habilidad,tarea_habilidad,eme_habilidad where tarea_habilidad.id_tarea = :id_tarea and eme_habilidad.id_habilidad = tarea_habilidad.id_eme_habilidad and vol_habilidad.id_habilidad=eme_habilidad.id_habilidad and vol_habilidad.id_voluntario=voluntario.id and voluntario.deleted=false ORDER BY voluntario.disponibilidad,voluntario.nombre";
         try(Connection conn = sql2o.open()){
             return conn.createQuery(query).addParameter("id_tarea",id_tarea).executeAndFetch(Voluntarios.class);
          }
          catch (Exception e){
              System.out.println(e.getMessage());
              return null;
          }
      }
    
    @Override
     public List<Voluntarios> getVoluntarioxEmergencia(Long id_emergencia) {
         String query = "select distinct voluntario.id, voluntario.nombre, st_x(st_astext(voluntario.ubicacion)) AS longitud,st_y(st_astext(voluntario.ubicacion)) AS latitud from voluntario,vol_habilidad,tarea_habilidad,eme_habilidad,tarea where tarea.id_emergencia = :id_emergencia and tarea_habilidad.id_tarea =tarea.id and eme_habilidad.id_habilidad = tarea_habilidad.id_eme_habilidad and vol_habilidad.id_habilidad =  eme_habilidad.id_habilidad and vol_habilidad.id_voluntario = voluntario.id";
          try(Connection conn = sql2o.open()){
             return conn.createQuery(query).addParameter("id_emergencia",id_emergencia).executeAndFetch(Voluntarios.class);
          }
          catch (Exception e){
              System.out.println(e.getMessage());
              return null;
          }
     }
}
