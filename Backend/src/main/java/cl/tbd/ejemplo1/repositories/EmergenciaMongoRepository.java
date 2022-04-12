package cl.tbd.ejemplo1.repositories;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import cl.tbd.ejemplo1.models.EmergenciaMongo;
public interface EmergenciaMongoRepository{
    public List<Document> getEmergencias();
}