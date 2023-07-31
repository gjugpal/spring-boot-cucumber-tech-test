package com.geek.dto;

import com.geek.AbstractHttpSpecification;
import com.mashape.unirest.http.HttpMethod;

import java.net.URLEncoder;


public class SearchByName extends AbstractHttpSpecification {

    private final String name;

    public SearchByName(String name) {
        this.name = name;
    }

    @Override
    protected HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    protected String getEndpointUrl() {
        return "http://universities.hipolabs.com/search?name=" + URLEncoder.encode(name);
    }

}
