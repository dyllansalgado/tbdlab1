package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class HabilidadRepositoryImp implements HabilidadRepository{

     @Autowired
     private Sql2o sql2o;

     @Override
    public Long countHabilidad(){
        String query = "select count(*) from habilidad";
        Connection conn = sql2o.open();
        Long resultado = (Long) conn.createQuery(query,true).executeAndFetchFirst(Long.class);
        return resultado + 1; 
    }
    
    @Override
    public Habilidad createHabilidad(Habilidad habilidad){
        Long id_habilidad = countHabilidad();
        String query = "INSERT INTO habilidad (id,nombreH,descripcion) values (:id, :nombreH,:descripcion)";
        try(Connection conn = sql2o.open()){
            conn.createQuery(query,true).addParameter("id",id_habilidad)
                            .addParameter("nombreH", habilidad.getNombreH())
                            .addParameter("descripcion", habilidad.getDescripcion())
                            .executeUpdate().getKey(Long.class);
            habilidad.setId(id_habilidad);
            return habilidad;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

     @Override
    public Habilidad getHabilidad(Long id){
        String query = "select * from habilidad where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Habilidad.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

     @Override
    public List<Habilidad> getHabilidades(){
        String query = "select * from habilidad";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).executeAndFetch(Habilidad.class);
         }
         catch (Exception e){
             System.out.println(e.getMessage());
             return null;
         }
     }

     @Override
    public boolean deleteHabilidad(Long id){
         String query = "update habilidad set deleted = true where id = :id and deleted = false";
         try(Connection conn = sql2o.open()){
            id = conn.createQuery(query).addParameter("id",id).executeUpdate().getKey(Long.class);          
            return true;
         }
         catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
     }

     @Override
     public Habilidad updateHabilidad(Habilidad habilidad, Long id)
     {
        String query = "update habilidad set nombreH = :nombreH, descripcion = :descripcion where id = :id and deleted = false";
      
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query,true)
            .addParameter("id", id)
            .addParameter("descripcion", habilidad.getDescripcion())
            .addParameter("nombreH", habilidad.getNombreH())
            .executeUpdate().getKey(Long.class);

            habilidad.setId(insertedid);
            return habilidad;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }


     }

}

