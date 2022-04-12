package cl.tbd.ejemplo1.repositories;

import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import java.util.concurrent.TimeUnit;
import org.bson.Document;
import com.mongodb.client.AggregateIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cl.tbd.ejemplo1.models.TareaMongo;
import cl.tbd.ejemplo1.repositories.TareaMongoRepository;
import cl.tbd.ejemplo1.models.EmergenciaMongo;

@Repository
public class TareaMongoRepositoryImp implements TareaMongoRepository{

    @Override
    public ArrayList<Document> getTareas(Long id_emergencia) {
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/test?readPreference=primary&serverSelectionTimeoutMS=2000&appname=MongoDB+Compass&directConnection=true&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("TBD-LAB2");
        MongoCollection<Document> collection = database.getCollection("Tarea");
        
        AggregateIterable<Document> tareas = collection.aggregate(Arrays.asList(new Document("$match", new Document("id_emergencia", id_emergencia)),
                                            new Document("$lookup", new Document("from", "Tarea").append("localField","id").append("foreignField","id_emergencia").append("as","Tareas"))));
        
        ArrayList<Document> docOut = new ArrayList<>();
        for (Document document : tareas) {
            docOut.add(document);
        }
        return docOut;
    }

}
/*
 * Requires the MongoDB Java Driver.
 * https://mongodb.github.io/mongo-java-driver
 */


