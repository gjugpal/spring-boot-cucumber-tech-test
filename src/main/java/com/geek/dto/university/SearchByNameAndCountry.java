package com.geek.dto.university;

import com.geek.AbstractHttpSpecification;
import com.mashape.unirest.http.HttpMethod;
import lombok.Setter;

import java.net.URLEncoder;

public class SearchByNameAndCountry extends AbstractHttpSpecification {

    private final String name;
    private final String country;

    public SearchByNameAndCountry(String name, String country) {
        this.name = name;
        this.country = country;
    }
    @Override
    protected HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    protected String getEndpointUrl() {
        return String.format("http://universities.hipolabs.com/search?name=%s&country=%s", URLEncoder.encode(name), URLEncoder.encode(country));
    }
}
