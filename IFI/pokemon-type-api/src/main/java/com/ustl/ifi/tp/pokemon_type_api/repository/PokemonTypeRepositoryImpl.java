package com.ustl.ifi.tp.pokemon_type_api.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.ustl.ifi.tp.pokemon_type_api.bo.PokemonType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class PokemonTypeRepositoryImpl implements PokemonTypeRepository {

    private List<PokemonType> pokemons;

    public PokemonTypeRepositoryImpl() {
        try {
            var pokemonsStream = new ClassPathResource("pokemons.json").getInputStream();
            var objectMapper = new ObjectMapper();
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);
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
    public List<PokemonType> findPokemonTypeByTypes(String types) {
        System.out.println("Loading Pokemon information for Pokemon types " + types);
        List<PokemonType> result = new ArrayList<>();
        String[] types_split = types.split(",");
        List<String> type_list = Arrays.asList(types_split);
        Collections.sort(type_list);
        for(PokemonType pokemonType : this.pokemons){
            Collections.sort(pokemonType.getTypes());
            if(pokemonType.getTypes().equals(type_list)){
                result.add(pokemonType);
            }
        }
        return result;
    }

    @Override
    public List<PokemonType> findAllPokemonType() {
        return this.pokemons;
    }
}
