package cl.tbd.ejemplo1.services;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cl.tbd.ejemplo1.repositories.InstitucionRepository;
import cl.tbd.ejemplo1.models.Institucion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@CrossOrigin(origins = "*")
@RestController
public class InstitucionService {
    
    private final InstitucionRepository institucionRepository;
    private final Gson gson;

    InstitucionService(InstitucionRepository institucionRepository){
        this.gson = new GsonBuilder().create();
        this.institucionRepository = institucionRepository;
    }


    @GetMapping("/instituciones/")
    ResponseEntity<String> getInstituciones(){
        List<Institucion> instituciones = institucionRepository.getInstituciones();
        return new ResponseEntity<>(gson.toJson(instituciones),HttpStatus.OK);
    }

    @GetMapping("/instituciones/{id}")
    ResponseEntity<String> getInstitucion(@PathVariable Long id){
        Institucion institucion = institucionRepository.getInstitucion(id);
        if(institucion != null){
            return new ResponseEntity<>(gson.toJson(institucion),HttpStatus.OK);
        }
        return new ResponseEntity<>("No se encontro la institucion objetivo :(\n",HttpStatus.NOT_FOUND);
    }

    @PostMapping("/instituciones/create")
    ResponseEntity<String> createInstitucion(@RequestBody String request){
        Institucion institucionOut = gson.fromJson(request,Institucion.class);
        if (institucionOut != null){
            institucionOut = institucionRepository.createInstitucion(institucionOut);
            return new ResponseEntity<>(gson.toJson(institucionOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @CrossOrigin(origins = {"http://localhost:8080"})
    @ResponseBody
    @RequestMapping(value ="/instituciones/{id}",  method =RequestMethod.PUT,consumes="application/json")
    ResponseEntity<String> updateInstitucion(@RequestBody String request, @PathVariable Long id){
        Institucion inst=gson.fromJson(request,Institucion.class);
        Institucion institucionOut = institucionRepository.getInstitucion(id);
        if(institucionOut != null){
            if(inst.getNombre() != null){
                institucionOut.setNombre(inst.getNombre());
            }

            if(inst.getDireccion() != null){
                institucionOut.setDireccion(inst.getDireccion());
            }

            if(inst.getDominio() != null){
                institucionOut.setDominio(inst.getDominio());
            }
            if(inst.getTelefono() != null){
                institucionOut.setTelefono (inst.getTelefono());
            }
 
            institucionOut = institucionRepository.updateInstitucion(institucionOut, id);
            return new ResponseEntity<>(gson.toJson(institucionOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/instituciones/{id}")
    ResponseEntity<String> deleteInstitucion(@PathVariable Long id){
          if(institucionRepository.deleteInstitucion(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
