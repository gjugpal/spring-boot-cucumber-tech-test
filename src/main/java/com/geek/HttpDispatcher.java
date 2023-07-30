package com.geek;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.geek.dto.university.UniversityResponse;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

public class HttpDispatcher {

    public void send(Gateway gateway) {

        StringBuilder queryString = new StringBuilder().append(gateway.getBody().getEndpointUrl());

        if (gateway.getLimit() > 0) {
            queryString.append("&limit=").append(gateway.getLimit());
        }

        if (gateway.getOffset() != 0) {
            queryString.append("&offset=").append(gateway.getOffset());
        }

        HttpMethod method = gateway.getBody().getHttpMethod();
        HttpRequest request = new HttpRequest(method, queryString.toString());

        HttpResponse response;

        try {
            response = request.asJson();
        } catch (UnirestException e) {
            try {
                response = request.asString();
            } catch (UnirestException ex) {
                throw new RuntimeException(ex);
            }
        }

        gateway.setHttpRequest(request);
        gateway.setHttpResponse(response);

        try {
            String reqLog = String.format("\nREQUEST\n-----------\nMETHOD: %s\nURL: %s\nHEADERS: %s\n", new Object[]{request.getHttpMethod(), request.getUrl(), request.getHeaders()});
            ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, MarkupHelper.createCodeBlock(reqLog));

            String respLog = String.format("\nRESPONSE\n-----------\nSTATUS_CODE: %d\nSTATUS_TEXT: %s\nHEADERS: %s\nBODY: %s\n", response.getStatus(), response.getStatusText(), response.getHeaders(), response.getBody());
            ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, MarkupHelper.createCodeBlock(respLog));
        }catch (NullPointerException ne) {

        }

        try {
            UniversityResponse[] uniResponse = new Gson().fromJson(response.getBody().toString(), UniversityResponse[].class);
            gateway.setSerializedResponse(uniResponse);
        } catch (Exception e) {
            // the response returned was not a valid one but no need to do anything
        }
    }
}
