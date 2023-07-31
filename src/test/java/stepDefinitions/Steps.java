package stepDefinitions;

import com.geek.Gateway;
import com.geek.dto.*;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class Steps {

    private Gateway request = new Gateway();
    private boolean hasRequestAlreadyBeenSent = false;
    private HttpResponse previousResponse;


    @And("the response should contain the university {string}")
    public void assertResponseContainsUni(String uni) {
        UniversityResponse[] response = request.getSerializedResponse();
        Optional<UniversityResponse> result = Arrays.stream(response).filter(p -> p.getName().equalsIgnoreCase(uni)).findFirst();
        assertThat(result.isPresent(), is(true));
    }

    @And("the response should not contain the university {string}")
    public void assertResponseDoesNotContainUni(String uni) {
        UniversityResponse[] response = request.getSerializedResponse();
        Optional<UniversityResponse> result = Arrays.stream(response).filter(p -> p.getName().equalsIgnoreCase(uni)).findFirst();
        assertThat(result.isPresent(), is(false));
    }

    @And("the response should return {int} university/universities")
    public void assertNumReturned(int numReturned) {
        UniversityResponse[] response = request.getSerializedResponse();
        assertThat(response.length, is(numReturned));
    }

    @Given("I perform a search without any query parameters")
    public void search() {
        request.search(new Search());
    }

    @Given("I search for a university by the name {string}")
    public void searchByName(String uniName) {
        request.searchByName(new SearchByName(uniName));
    }

    @Given("I search for universities in the country {string}")
    public void searchByCountry(String country) {
        request.searchByCountry(new SearchByCountry(country));
    }

    @Given("I search for university {string} in the country {string}")
    public void searchByNameAndCountry(String name, String country) {
        request.searchByNameAndCountry(new SearchByNameAndCountry(name, country));
    }

    @When("I send the request")
    public void sendRequest() {
        if (hasRequestAlreadyBeenSent) {
            previousResponse = request.getHttpResponse();
        }
        request.send();
        hasRequestAlreadyBeenSent = true;
    }

    @Then("the response code should be {int} {string}")
    public void theResponseCodeShouldBeOK(int code, String statusText) {
        assertThat(request.getHttpResponse().getStatus(), is(code));
        assertThat(request.getHttpResponse().getStatusText(), is(statusText));
    }

    @And("I set the search limit to {int}")
    public void setSearchLimit(int limit) {
        request.setLimit(limit);
    }

    @And("the response should contain universities from {string} only")
    public void assertResponseContainsFromCountryOnly(String country) {
        UniversityResponse[] response = request.getSerializedResponse();

        long universityCount = Arrays.stream(response)
                .filter(p -> p.getCountry().equalsIgnoreCase(country))
                .count();

        assertThat(universityCount, is(equalTo((long) response.length)));
    }

    @And("the response should contain universities with names that contain {string} only")
    public void assertResponseContainsUnisWhichContainTheName(String name) {

        UniversityResponse[] response = request.getSerializedResponse();

        long universityCount = Arrays.stream(response)
                .filter(p -> p.getName().contains(name))
                .count();

        assertThat(universityCount, is(equalTo((long) response.length)));
    }

    @When("I set the offset to {int}")
    public void setOffset(int offset) {
        request.setOffset(offset);
    }

    @Then("the second response should return the same universities apart from the first {int}")
    public void assertOffsetParameterHasWorked(int offset) {

        UniversityResponse[] firstResponse = new Gson().fromJson(previousResponse.getBody().toString(), UniversityResponse[].class);
        UniversityResponse[] secondResponse = request.getSerializedResponse();
        UniversityResponse[] slicedArray = Arrays.copyOfRange(firstResponse, offset, offset + secondResponse.length);

        IntStream indices = IntStream.range(0, Math.min(secondResponse.length, slicedArray.length));

        // Check if the 'name' fields match in order for each pair of elements
        boolean namesMatchInOrder = indices.allMatch(i -> secondResponse[i].getName().equals(slicedArray[i].getName()));

        assertThat(namesMatchInOrder, is(true));
    }

    @Then("the response should contain universities from multiple countries")
    public void assertResponseContainsMultipleUniversities() {

        UniversityResponse[] response = request.getSerializedResponse();

        Set<String> uniqueCountries = Arrays.stream(response)
                .map(UniversityResponse::getCountry)
                .collect(Collectors.toSet());

        assertThat(uniqueCountries.size(), is(greaterThanOrEqualTo(2)));
    }

    @And("I set the endpoint to {string}")
    public void overrideUrl(String endpoint) {
        Search search = new Search();
        search.setEndpointUrl("http://universities.hipolabs.com" + endpoint);
        request.search(search);
    }
}
