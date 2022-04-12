package cl.tbd.ejemplo1.services;
import cl.tbd.ejemplo1.repositories.VoluntarioRepository;
import cl.tbd.ejemplo1.models.Voluntarios;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class VoluntarioService {

    private final VoluntarioRepository voluntarioRepository;
    private final Gson gson;

    VoluntarioService(VoluntarioRepository voluntarioRepository){
        this.gson = new GsonBuilder().create();
        this.voluntarioRepository = voluntarioRepository;
    }

    @GetMapping("/voluntarios/")
    ResponseEntity<String> getVoluntarios(){
        List<Voluntarios> Voluntarioses = voluntarioRepository.getAllVoluntarios() ;
        return new ResponseEntity<>(gson.toJson(Voluntarioses),HttpStatus.OK);
    }

    @GetMapping("/voluntarios/{id}")
    ResponseEntity<String> getVoluntarios(@PathVariable Long id){
        Voluntarios Voluntarios = voluntarioRepository.getVoluntario(id);
        if(Voluntarios != null){
            return new ResponseEntity<>(gson.toJson(Voluntarios),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/voluntarios/create")
    ResponseEntity<String> createVoluntarios(@RequestBody String request){
        Voluntarios VoluntariosOut = gson.fromJson(request,Voluntarios.class);
        if (VoluntariosOut != null){
            VoluntariosOut = voluntarioRepository.createVoluntario(VoluntariosOut);
            return new ResponseEntity<>(gson.toJson(VoluntariosOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = {"http://localhost:8080"})
    @ResponseBody
    @RequestMapping(value ="/voluntarios/{id}",  method =RequestMethod.PUT,consumes="application/json")
    ResponseEntity<String> updateVoluntarios(@RequestBody String request, @PathVariable Long id){
        Voluntarios vol = gson.fromJson(request, Voluntarios.class);
        Voluntarios VoluntariosOut = voluntarioRepository.getVoluntario(id);
        if(VoluntariosOut != null){
            if(vol.getNombre() != null){
                VoluntariosOut.setNombre(vol.getNombre());
            }
            if(vol.getDisponibilidad() != null){
                VoluntariosOut.setDisponibilidad(vol.getDisponibilidad());
            }
            if(vol.getDireccion() != null){
                VoluntariosOut.setDireccion(vol.getDireccion());
            }
            if(vol.getCorreo_electronico() != null){
                VoluntariosOut.setCorreo_electronico(vol.getCorreo_electronico());
            }
            if(vol.getTelefono() != null){
                VoluntariosOut.setTelefono(vol.getTelefono());
            }
            VoluntariosOut = voluntarioRepository.updateVoluntario(VoluntariosOut, id);
            return new ResponseEntity<>(gson.toJson(VoluntariosOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/voluntarios/{id}")
    ResponseEntity<String> deleteVoluntarios(@PathVariable Long id){
        if(voluntarioRepository.deleteVoluntario(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/voluntariosTareas/{id_tarea}")
    ResponseEntity<String> getVoluntariosxTarea(@PathVariable Long id_tarea){
        List<Voluntarios> Voluntarios = voluntarioRepository.getVoluntarioxTarea(id_tarea);
        if(Voluntarios != null){
            return new ResponseEntity<>(gson.toJson(Voluntarios),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/voluntariosEmergencia/{id_emergencia}")
    ResponseEntity<String> getVoluntariosxEmergencia(@PathVariable Long id_emergencia){
        List<Voluntarios> Voluntarios = voluntarioRepository.getVoluntarioxEmergencia(id_emergencia);
        if(Voluntarios != null){
            return new ResponseEntity<>(gson.toJson(Voluntarios),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
