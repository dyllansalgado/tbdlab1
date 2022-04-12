package cl.tbd.ejemplo1.services;
import org.springframework.web.bind.annotation.*;

import cl.tbd.ejemplo1.repositories.RankingRepository;
import cl.tbd.ejemplo1.models.Ranking;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;


@CrossOrigin
@RestController
public class RankingService {
    
    private final RankingRepository RankingRepository;
    private final Gson gson;

    RankingService(RankingRepository RankingRepository){
        this.gson = new GsonBuilder().create();
        this.RankingRepository = RankingRepository;
    }

    @GetMapping("/rankings/")
    ResponseEntity<String> getRankings(){
        List<Ranking> Ran = RankingRepository.getRankings() ;
        return new ResponseEntity<>(gson.toJson(Ran),HttpStatus.OK);
    }

    @GetMapping("/rankings/{id}")
    ResponseEntity<String> getRanking(@PathVariable Long id){
        Ranking Ranking = RankingRepository.getRanking(id);
        if(Ranking != null){
            return new ResponseEntity<>(gson.toJson(Ranking),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/rankings/create")
    ResponseEntity<String> createRanking(@RequestBody String request){
        Ranking RankingOut = gson.fromJson(request,Ranking.class);
        if (RankingOut != null){
            RankingOut = RankingRepository.createRanking(RankingOut);
            return new ResponseEntity<>(gson.toJson(RankingOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = {"http://localhost:8080"})
    @ResponseBody
    @RequestMapping(value ="/rankings/{id}",  method =RequestMethod.PUT,consumes="application/json")
    ResponseEntity<String> updateRanking(@RequestBody String request, @PathVariable Long id){
        Ranking rank=gson.fromJson(request,Ranking.class);
        Ranking RankingOut = RankingRepository.getRanking(id);
        if(RankingOut != null){
            if(rank.getvalor() != null){
                RankingOut.setvalor(rank.getvalor());
            }
            if(rank.getId_tarea() != null){
                RankingOut.setId_tarea(rank.getId_tarea());
            }
            if(rank.getId_voluntario() != null){
                RankingOut.setId_voluntario(rank.getId_voluntario());
            }
      
            RankingOut = RankingRepository.updateRanking(RankingOut, id);
            return new ResponseEntity<>(gson.toJson(RankingOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/rankings/{id}")
    ResponseEntity<String> deleteRanking(@PathVariable Long id){
        if(RankingRepository.deleteRanking(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
