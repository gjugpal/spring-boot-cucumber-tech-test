package com.geek.dto.university;

import com.geek.AbstractHttpSpecification;
import com.mashape.unirest.http.HttpMethod;

public class Search extends AbstractHttpSpecification {

    private String url = "http://universities.hipolabs.com/search";
    @Override
    protected HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    public String getEndpointUrl() {
        return url;
    }

    public void setEndpointUrl(String url) {
        this.url = url;
    }
}
