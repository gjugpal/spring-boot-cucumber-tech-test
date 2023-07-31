package com.geek;

import com.geek.dto.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.request.HttpRequest;
import lombok.Getter;
import lombok.Setter;

public class Gateway {

    @Getter
    private AbstractHttpSpecification body;
    @Getter
    @Setter
    private HttpResponse httpResponse;
    @Getter
    @Setter
    private HttpRequest httpRequest;
    @Getter
    @Setter
    private UniversityResponse[] serializedResponse;
    @Getter
    @Setter
    private int limit;
    @Getter
    @Setter
    private int offset;

    public Gateway search(Search req) {
        body = req;
        return this;
    }

    public Gateway searchByName(SearchByName req) {
        body = req;
        return this;
    }

    public Gateway searchByCountry(SearchByCountry req) {
        body = req;
        return this;
    }

    public Gateway searchByNameAndCountry(SearchByNameAndCountry req) {
        body = req;
        return this;
    }

    public Gateway send() {
        new HttpDispatcher().send(this);
        return this;
    }
}
