package com.ustl.ifi.repository;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.ustl.ifi.bo.PokemonType;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PokemonTypeRepository {

    private List<PokemonType> pokemons;

    public PokemonTypeRepository() {
        try {
            var objectMapper = new ObjectMapper();
            CollectionType listType = objectMapper.getTypeFactory()
                    .constructCollectionType(ArrayList.class, PokemonType.class);
            List<PokemonType> ts = objectMapper.readValue(new File("./src/main/ressources/pokemons.json"), listType);

            /*
            var pokemonsStream = this.getClass().getResourceAsStream("/pokemons.json");
            objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE,true);
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);*/
            this.pokemons = ts;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PokemonType findPokemonById(int id) {
        System.out.println("Loading Pokemon information for Pokemon id " + id);
        for(PokemonType pokemon : this.pokemons){
            if(id == pokemon.getId()){
                return pokemon;
            }
        }
        return null;
    }

    public PokemonType findPokemonByName(String name) {
        System.out.println("Loading Pokemon information for Pokemon name " + name);
        for(PokemonType pokemon : this.pokemons){
            if(name.equals(pokemon.getName())){
                return pokemon;
            }
        }
        return null;
    }

    public List<PokemonType> findAllPokemon() {
        return this.pokemons;
    }
}