package cl.tbd.ejemplo1.services;
import cl.tbd.ejemplo1.repositories.HabilidadRepository;
import cl.tbd.ejemplo1.models.Habilidad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*")
@RestController

public class HabilidadService {

    private final HabilidadRepository habilidadRepository;
    private final Gson gson;

    HabilidadService(HabilidadRepository habilidadRepository){
        this.gson = new GsonBuilder().create();
        this.habilidadRepository = habilidadRepository;
    }

    @GetMapping("/habilidades/")
    ResponseEntity<String> getHabilidades(){
        List<Habilidad> habilidades = habilidadRepository.getHabilidades();
        return new ResponseEntity<>(gson.toJson(habilidades),HttpStatus.OK);
    }

    @GetMapping("/habilidades/{id}")
    ResponseEntity<String> getHabilidad(@PathVariable Long id){
        Habilidad habilidad = habilidadRepository.getHabilidad(id);
        if(habilidad != null){
            return new ResponseEntity<>(gson.toJson(habilidad),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/habilidades/create")
    ResponseEntity<String> createHabilidad(@RequestBody String request){
        Habilidad habilidadOut = gson.fromJson(request,Habilidad.class);
        if (habilidadOut != null){  
            habilidadOut = habilidadRepository.createHabilidad(habilidadOut);
            return new ResponseEntity<>(gson.toJson(habilidadOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    

    @CrossOrigin(origins = {"http://localhost:8080"})
    @ResponseBody
    @RequestMapping(value ="/habilidades/{id}",  method =RequestMethod.PUT,consumes="application/json")
    ResponseEntity<String> updateHabilidad(@RequestBody String request, @PathVariable Long id){
        Habilidad hab= gson.fromJson(request,Habilidad.class);
        Habilidad habilidadOut = habilidadRepository.getHabilidad(id);
        if(habilidadOut != null){
            if(hab.getNombreH() != null){
                habilidadOut.setNombreH(hab.getNombreH());
            }

            if(hab.getDescripcion() != null){ 
                habilidadOut.setDescripcion(hab.getDescripcion());
            }

            habilidadOut = habilidadRepository.updateHabilidad(habilidadOut, id);
            return new ResponseEntity<>(gson.toJson(habilidadOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/habilidades/{id}")
    ResponseEntity<String> deleteHabilidad(@PathVariable Long id){
        if(habilidadRepository.deleteHabilidad(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.status(404).body("No se encontro la habilidad objetivo :(\n");
    }


}

