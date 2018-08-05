package com.example.beer.service;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
@AllArgsConstructor
public class FilterCriteria {

    private String key;
    private Object value;

}
