package com.ustl.ifi.tp.pokemon_type_api.service;

import com.ustl.ifi.tp.pokemon_type_api.bo.PokemonType;

import java.util.List;

public interface PokemonTypeService {
    PokemonType getPokemonTypeFromId(int id);
    PokemonType getPokemonTypeFromName(String name);
    List<PokemonType> getPokemonTypeFromTypes(String types);
    List<PokemonType> getAllPokemonTypes();
}
