package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

@Repository
public class TareaRepositoryImp implements TareaRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public Long countTarea(){
        String query = "select count(*) from tarea";
        Connection conn = sql2o.open();
        Long resultado = (Long) conn.createQuery(query,true).executeAndFetchFirst(Long.class);
        return resultado + 1; 
    }

    @Override
    public Tarea createTarea(Tarea tarea){
        Long id_prueba =countTarea();
        String query = "INSERT into tarea (id,titulo,descripcion,id_estado,id_emergencia,cant_voluntarios) values (:id,:titulo,:descripcion,:id_estado,:id_emergencia,:cant_voluntarios)";
        try(Connection conn = sql2o.open()){
            conn.createQuery(query,true).addParameter("id",id_prueba).addParameter("titulo", tarea.getTitulo())
                            .addParameter("descripcion", tarea.getDescripcion())
                            .addParameter("id_estado",Long.valueOf(tarea.getEstadoId()))
                            .addParameter("id_emergencia",Long.valueOf(tarea.getEmergenciaId()))
                            .addParameter("cant_voluntarios", tarea.getCant_Voluntarios())
                            .executeUpdate().getKey();
            tarea.setId(id_prueba);
            return tarea;
        } 
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    
    @Override
    public Tarea getTarea(Long id){
        String query = "select * from tarea where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Tarea.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Tarea> getTareas(){
        String query = "select * from tarea where deleted=false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).executeAndFetch(Tarea.class);
         }
         catch (Exception e){
             System.out.println(e.getMessage());
             return null;
         }
     }

    @Override
    public boolean deleteTarea(Long id){
         String query = "update tarea set deleted = true where id = :id and deleted = false";
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
    public Tarea updateTarea(Tarea tarea, Long id){
        String query = "update tarea set titulo = :titulo, descripcion = :descripcion, id_estado = :id_estado, cant_voluntarios = :cant_voluntarios where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query).addParameter("id", id)
                            .addParameter("titulo", tarea.getTitulo())
                            .addParameter("descripcion", tarea.getDescripcion())
                            .addParameter("id_estado", tarea.getEstadoId())
                            .addParameter("cant_voluntarios", tarea.getCant_Voluntarios())
                            .executeUpdate().getKey(Long.class);
            tarea.setId(insertedid);
            return tarea;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override 
    public List<Tarea> getTareaxEmergencia(Long id_emergencia){
        String query = "SELECT * FROM tarea WHERE tarea.id_emergencia = :id_emergencia AND tarea.deleted = false ORDER BY tarea.titulo, tarea.id_estado";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).addParameter("id_emergencia",id_emergencia).executeAndFetch(Tarea.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
