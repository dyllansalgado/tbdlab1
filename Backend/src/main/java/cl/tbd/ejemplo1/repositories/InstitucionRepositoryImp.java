package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Institucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class InstitucionRepositoryImp implements InstitucionRepository{

    @Autowired
    private Sql2o sql2o;

    public Long countInstitucion(){
        String query = "select count(*) from institucion";
        Connection conn = sql2o.open();
        Long resultado = (Long) conn.createQuery(query,true).executeAndFetchFirst(Long.class);
        return resultado + 1; 
    }    

    @Override
    public Institucion createInstitucion(Institucion institucion){
        Long id_prueba =countInstitucion();
        String query = "insert into institucion (id,nombre,direccion,telefono,dominio) values (:id,:nombre,:direccion,:telefono,:dominio)";
        try(Connection conn = sql2o.open()){
            conn.createQuery(query, true).addParameter("id",id_prueba)
                            .addParameter("nombre", institucion.getNombre())
                            .addParameter("direccion", institucion.getDireccion())
                            .addParameter("telefono", institucion.getTelefono())
                            .addParameter("dominio", institucion.getDominio())
                            .executeUpdate().getKey(Long.class);
            institucion.setId(id_prueba);
            return institucion;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Institucion getInstitucion(Long id){
        String query = "select * from institucion where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Institucion.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<Institucion> getInstituciones() {
        String query = "select * from institucion";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).executeAndFetch(Institucion.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteInstitucion(Long id){
        String query = "update institucion set deleted = true where id = :id and deleted = false";
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
     public Institucion updateInstitucion(Institucion institucion, Long id)
     {
          String query = "update institucion nombre = :nombre, direccion = :direccion,telefono = :telefono, dominio = :dominio where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query).addParameter("id", id)
            .addParameter("nombre", institucion.getNombre())
            .addParameter("direccion", institucion.getDireccion())
            .addParameter("telefono", institucion.getTelefono())
            .addParameter("dominio", institucion.getDominio())
            .executeUpdate().getKey(Long.class);
            institucion.setId(insertedid);
            return institucion;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }


     }

}
