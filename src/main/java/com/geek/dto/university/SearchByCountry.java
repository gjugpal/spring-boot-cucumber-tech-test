package com.geek.dto.university;

import com.geek.AbstractHttpSpecification;
import com.mashape.unirest.http.HttpMethod;
import lombok.Setter;

import java.net.URLEncoder;

public class SearchByCountry extends AbstractHttpSpecification {

    private final String country;

    public SearchByCountry(String country) {
        this.country = country;
    }
    @Override
    protected HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    protected String getEndpointUrl() {
        return "http://universities.hipolabs.com/search?country=" + URLEncoder.encode(country);
    }
}
