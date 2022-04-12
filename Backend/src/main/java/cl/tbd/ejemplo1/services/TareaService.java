package cl.tbd.ejemplo1.services;
import cl.tbd.ejemplo1.repositories.TareaRepository;
import cl.tbd.ejemplo1.models.Tarea;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class TareaService {
    private final TareaRepository tareaRepository;
    private final Gson gson;

    TareaService(TareaRepository tareaRepository){
        this.gson = new GsonBuilder().create();
        this.tareaRepository = tareaRepository;
    }

    @GetMapping("/tareas/")
    ResponseEntity<String> getTareas(){
        List<Tarea> tareas = tareaRepository.getTareas() ;
        return new ResponseEntity<>(gson.toJson(tareas),HttpStatus.OK);
    }

    @GetMapping("/tareas/{id}")
    ResponseEntity<String> getTareas(@PathVariable Long id){
        Tarea tareas = tareaRepository.getTarea(id);
        if(tareas != null){
            return new ResponseEntity<>(gson.toJson(tareas),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/tareas/create")
    ResponseEntity<String> createTarea(@RequestBody String request){
        Tarea tarOut = gson.fromJson(request,Tarea.class);
        if (tarOut != null){
            Tarea tarOut2 = tareaRepository.createTarea(tarOut);
            return new ResponseEntity<>(gson.toJson(tarOut2),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @CrossOrigin(origins = {"http://localhost:8080"})
    @ResponseBody
    @RequestMapping(value ="/tareas/{id}",  method =RequestMethod.PUT,consumes="application/json")
    ResponseEntity<String> updateTarea(@RequestBody String request, @PathVariable Long id){
        Tarea funciona=gson.fromJson(request,Tarea.class);
        Tarea tarOut = tareaRepository.getTarea(id);
        
        if(tarOut != null){
            if(funciona.getCant_Voluntarios() != null){
                tarOut.setCant_Voluntarios(funciona.getCant_Voluntarios());
            }

            if(funciona.getTitulo() != null){
                tarOut.setTitulo(funciona.getTitulo());
            }

            if(funciona.getDescripcion() != null){
                tarOut.setDescripcion(funciona.getDescripcion());
            }
            if(funciona.getEmergenciaId() != null){
                tarOut.setEmergenciaId(funciona.getEmergenciaId());
            }
            if(funciona.getEstadoId() != null){
                tarOut.setEstadoId(funciona.getEstadoId());
            }
            tarOut = tareaRepository.updateTarea(tarOut, id);
            return new ResponseEntity<>(gson.toJson(tarOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    

    @DeleteMapping("/tareas/{id}")
    ResponseEntity<String> deleteTarea(@PathVariable Long id){
        if(tareaRepository.deleteTarea(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @CrossOrigin(origins = {"http://localhost:8080"})
  //@ResponseBody
    @RequestMapping(value ="/tareasEmergencias/{id_emergencia}",  method =RequestMethod.GET)
    ResponseEntity<String> getTareasxEmergencia(@PathVariable Long id_emergencia){
        List<Tarea> tareas = tareaRepository.getTareaxEmergencia(id_emergencia);
        if(tareas != null){
            return new ResponseEntity<>(gson.toJson(tareas),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
