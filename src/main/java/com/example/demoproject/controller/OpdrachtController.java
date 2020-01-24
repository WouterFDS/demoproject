package com.example.demoproject.controller;

import com.example.demoproject.domain.ApiResponse;
import com.example.demoproject.domain.Opdracht;
import com.example.demoproject.service.OpdrachtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/opdracht")
public class OpdrachtController {
    @Autowired
    OpdrachtService facade;

    @RequestMapping(value="/lijst",method = RequestMethod.POST)
    @CrossOrigin(origins="*", maxAge=3600)
    public ApiResponse krijgOpdrachten(){
        return new ApiResponse(200,"de opdrachten",facade.krijgAlleOpdrachten());

    }

    @RequestMapping(value="/maak",method = RequestMethod.POST)
    @CrossOrigin(origins="*", maxAge=3600)
    public ApiResponse maakOpdracht(@RequestBody Opdracht opdracht){
        String boodschap="";
        if(opdracht.getId()==0){
            facade.maakOpdracht(opdracht.getNaam(),opdracht.getBelangrijkheid());
            boodschap="de opdracht is gemaakt";
            return new ApiResponse(200,"de opdracht is gemaakt","ok");
        }
        else{
            facade.updateOpdracht(opdracht.getId(),opdracht.getNaam(),opdracht.getBelangrijkheid());
            boodschap="de opdracht is gewijzigd";
            return new ApiResponse(200,"de opdracht is gewijzigd","ok");
        }


    }

    @RequestMapping(value="/verwijder",method = RequestMethod.POST)
    @CrossOrigin(origins="*", maxAge=3600)
    public ApiResponse verijderOpdracht(@RequestBody Opdracht opdracht){
        facade.verwijderOpdracht(opdracht.getId());
        return new ApiResponse(200,"de opdracht is verwijderd",facade.krijgAlleOpdrachten());

    }

    @RequestMapping(value="/wijzig",method = RequestMethod.POST)
    @CrossOrigin(origins="*", maxAge=3600)
    public ApiResponse wijzigOpdracht(@RequestBody Opdracht opdracht){
        facade.updateOpdracht(opdracht.getId(),opdracht.getNaam(),opdracht.getBelangrijkheid());
        return new ApiResponse(200,"de opdracht is gewijzigd","");

    }
}
