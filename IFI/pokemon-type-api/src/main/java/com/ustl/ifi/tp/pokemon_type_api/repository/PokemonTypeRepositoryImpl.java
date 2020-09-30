package com.ustl.ifi.tp.pokemon_type_api.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.ustl.ifi.tp.pokemon_type_api.bo.PokemonType;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PokemonTypeRepositoryImpl implements PokemonTypeRepository {

    private List<PokemonType> pokemons;

    public PokemonTypeRepositoryImpl() {
        try {
            var objectMapper = new ObjectMapper();
            CollectionType listType = objectMapper.getTypeFactory()
                    .constructCollectionType(ArrayList.class, PokemonType.class);
            List<PokemonType> ts = objectMapper.readValue(new File("./src/main/ressources/pokemons.json"), listType);
            this.pokemons = ts;
            /*var pokemonsStream = this.getClass().getResourceAsStream("/pokemons.json");
            var objectMapper = new ObjectMapper();
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PokemonType findPokemonTypeById(int id) {
        System.out.println("Loading Pokemon information for Pokemon id " + id);
        for(PokemonType pokemon : this.pokemons){
            if(id == pokemon.getId()){
                return pokemon;
            }
        }
        return null;
    }

    @Override
    public PokemonType findPokemonTypeByName(String name) {
        System.out.println("Loading Pokemon information for Pokemon name " + name);
        for(PokemonType pokemon : this.pokemons){
            if(name.equals(pokemon.getName())){
                return pokemon;
            }
        }
        return null;
    }

    @Override
    public List<PokemonType> findAllPokemonType() {
        return this.pokemons;
    }
}
