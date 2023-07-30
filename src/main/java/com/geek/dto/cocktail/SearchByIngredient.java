package com.geek.dto.cocktail;

import com.geek.AbstractHttpSpecification;
import com.mashape.unirest.http.HttpMethod;
import lombok.Builder;
import lombok.Setter;
import lombok.experimental.Accessors;

@Builder
@Accessors(chain = true)
public class SearchByIngredient extends AbstractHttpSpecification {

    @Setter private String ingredient;

    @Override
    protected HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    protected String getEndpointUrl() {
        return "http://www.thecocktaildb.com/api/json/v1/1/filter.php?i=" + ingredient;
    }
}
