package com.ustl.ifi.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonType {
    private int id;
    private String name;
    private int baseExperience;
    private int weight;
    private int height;
    private Stats stats;
    private Sprites sprites;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }
}
