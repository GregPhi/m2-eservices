package com.ustl.ifi.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sprites {
    private String front_default;
    private String back_default;
}
