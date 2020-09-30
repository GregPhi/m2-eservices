package com.ustl.ifi.tp.pokemon_type_api.repository;

import com.ustl.ifi.tp.pokemon_type_api.bo.PokemonType;

import java.util.List;

public interface PokemonTypeRepository {
    PokemonType findPokemonTypeById(int id);
    PokemonType findPokemonTypeByName(String name);
    List<PokemonType> findPokemonTypeByTypes(String types);
    List<PokemonType> findAllPokemonType();
}
