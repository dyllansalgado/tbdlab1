package cl.tbd.ejemplo1.repositories;

import java.util.List;
import org.bson.Document;
import cl.tbd.ejemplo1.models.TareaMongo;

public interface TareaMongoRepository{
    public List<Document> getTareas(Long id_emergencia);
}
