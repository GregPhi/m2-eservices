package com.ustl.ifi.tp.pokemon_type_api.controller;

import com.ustl.ifi.tp.pokemon_type_api.bo.PokemonType;
import com.ustl.ifi.tp.pokemon_type_api.service.PokemonTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon-types")
class PokemonTypeController {

    private PokemonTypeService service;

    public PokemonTypeController(PokemonTypeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    PokemonType getPokemonTypeFromId(@PathVariable int id){
        return this.service.getPokemonType(id);
    }

    @RequestMapping(path="/",params = "name")
    PokemonType getPokemonTypeFromName(String name){
        return this.service.getPokemonType(name);
    }

    @GetMapping("/")
    public List<PokemonType> getAllPokemonTypes() {
        return this.service.getAllPokemonTypes();
    }
}
