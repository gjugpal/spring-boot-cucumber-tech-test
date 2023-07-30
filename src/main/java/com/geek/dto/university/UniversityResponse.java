package com.geek.dto.university;

import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
public class UniversityResponse {

    private List<String> domains;
    @SerializedName("state-province") private Object stateProvince;
    @SerializedName("alpha_two_code") private String alphaTwoCode;
    @SerializedName("web_pages") private List<String> webPages;
    private String country;
    private String name;

}
