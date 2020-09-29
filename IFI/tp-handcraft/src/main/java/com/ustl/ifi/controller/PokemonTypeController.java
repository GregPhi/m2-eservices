package com.ustl.ifi.controller;

import annotation.Controller;
import com.ustl.ifi.bo.PokemonType;
import com.ustl.ifi.repository.PokemonTypeRepository;
import com.ustl.ifi.servlet.RequestMapping;

import java.util.Map;

@Controller
public class PokemonTypeController {
    private PokemonTypeRepository repository = new PokemonTypeRepository();

    @RequestMapping(uri = "/pokemons")
    public PokemonType getPokemon(Map<String,String[]> parameters){
        if(parameters != null){
            if(parameters.containsKey("id")){
                return repository.findPokemonById(Integer.valueOf(parameters.get("id")[0]));
            }else if(parameters.containsKey("name")){
                return repository.findPokemonByName(parameters.get("name")[0]);
            }else if(parameters.containsKey("sprites")){
                return repository.findPokemonByName(parameters.get("sprites")[0]);
            }else{
                throw new IllegalArgumentException("unknown parameter");
            }
        }else{
            throw new IllegalArgumentException("parameters should not be empty");
        }
    }
}