package com.ustl.ifi.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats {
    private Integer speed;
    private Integer attack;
    private Integer defense;
    private Integer hp;
}
