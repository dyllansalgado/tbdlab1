package cl.tbd.ejemplo1.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import cl.tbd.ejemplo1.models.TareaMongo;
import cl.tbd.ejemplo1.repositories.TareaMongoRepository;
import org.bson.Document;
@CrossOrigin(origins = "*")
@RestController
public class TareaMongoService {

    private final TareaMongoRepository tareaRepository;

    TareaMongoService(TareaMongoRepository tareaRepository){
        this.tareaRepository = tareaRepository;
    }

    @RequestMapping( value = "/tareasMongo/{id_emergencia}", method = RequestMethod.GET)
    public List<Document> getTareas(@PathVariable Long id_emergencia) {
       
        return tareaRepository.getTareas(id_emergencia);
    }

}
