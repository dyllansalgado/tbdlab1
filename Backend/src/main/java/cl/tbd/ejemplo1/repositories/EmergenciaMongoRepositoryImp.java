package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.repositories.EmergenciaMongoRepository;
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
import cl.tbd.ejemplo1.models.EmergenciaMongo;

import org.springframework.stereotype.Repository;

@Repository
public class EmergenciaMongoRepositoryImp implements EmergenciaMongoRepository {


    @Override
    public List<Document> getEmergencias() {
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/test?directConnection=true&serverSelectionTimeoutMS=2000"));
        MongoDatabase database = mongoClient.getDatabase("TBD-LAB2");
        MongoCollection<Document> collection = database.getCollection("Emergencia");
        List <Document> emergencias = collection.find().into(new ArrayList<>());
        
        return emergencias;
    }
}
