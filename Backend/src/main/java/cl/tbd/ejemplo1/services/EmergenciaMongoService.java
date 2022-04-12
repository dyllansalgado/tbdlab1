package cl.tbd.ejemplo1.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import cl.tbd.ejemplo1.models.EmergenciaMongo;
import cl.tbd.ejemplo1.repositories.EmergenciaMongoRepository;
import org.bson.Document;
@CrossOrigin(origins = "*")
@RestController
public class EmergenciaMongoService {

    private final EmergenciaMongoRepository emergenciaRepository;

    EmergenciaMongoService(EmergenciaMongoRepository emergenciaRepository){
        this.emergenciaRepository = emergenciaRepository;
    }

    @RequestMapping( value = "/emergenciasMongo/", method = RequestMethod.GET)
    public List<Document> getEmergenciasMongo(){
        return emergenciaRepository.getEmergencias();
    }
}
