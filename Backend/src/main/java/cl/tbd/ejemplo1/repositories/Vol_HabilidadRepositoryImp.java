
package  cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Vol_habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;


@Repository
public class Vol_HabilidadRepositoryImp implements Vol_HabilidadRepository{

    @Autowired
     private Sql2o sql2o;

    @Override
    public Long countVol_Habilidad(){
        String query = "select count(*) from vol_habilidad";
        Connection conn = sql2o.open();
        Long resultado = (Long) conn.createQuery(query,true).executeAndFetchFirst(Long.class);
        return resultado + 1; 
    }
    
    @Override
    public Vol_habilidad createVol_habilidad(Vol_habilidad vol_habilidad){
        Long id_prueba = countVol_Habilidad();
        String query = "insert into vol_habilidad (id, id_voluntario, id_habilidad) values (:id, :id_voluntario,:id_habilidad)";
        try(Connection conn = sql2o.open()){
            conn.createQuery(query, true)
                   .addParameter("id",id_prueba)
                   .addParameter("id_voluntario", vol_habilidad.getVoluntarioId())
                   .addParameter("id_habilidad", vol_habilidad.getHabilidadId())
                   .executeUpdate().getKey();
            vol_habilidad.setId(id_prueba);
            return vol_habilidad;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Vol_habilidad> getAllVol_habilidad(){
        String query = "select * from vol_habilidad";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).executeAndFetch(Vol_habilidad.class);
         }
         catch (Exception e){
             System.out.println(e.getMessage());
             return null;
         }
    }

    @Override
    public Vol_habilidad getVol_habilidad(Long id){
        String query = "select * from vol_habilidad where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Vol_habilidad.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Vol_habilidad updateVol_habilidad(Vol_habilidad vol_habilidad, Long id){
        String query = "update vol_habilidad set id_voluntario = :id_voluntario, id_habilidad =:id_habilidad  where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
           Long insertedid = (Long) conn.createQuery(query).addParameter("id", id)
            .addParameter("id",vol_habilidad.getId())
            .addParameter("id_habilidad", vol_habilidad.getHabilidadId())
            .addParameter("id_voluntario", vol_habilidad.getVoluntarioId())
            .executeUpdate().getKey(Long.class);
            vol_habilidad.setId(insertedid);
            return vol_habilidad;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteVol_habilidad(Long id){
        String query = "update vol_habilidad set deleted = true where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
           id = conn.createQuery(query, true).addParameter("id",id).executeUpdate().getKey(Long.class);
        }
        catch (Exception e){
           System.out.println(e.getMessage());
           return false;
       }
       return true;

    }

}