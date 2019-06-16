package micronaut.conference.presentation;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

import static java.util.Arrays.asList;

@Controller("/conferences")
public class ConferenceEndpoint {

    @Get(produces = MediaType.APPLICATION_JSON)
    public List<ConferenceResponse> getConferences() {
        ConferenceResponse conferenceResponse = new ConferenceResponse();
        conferenceResponse.id = 1;
        conferenceResponse.name = "TDC";

        return asList(conferenceResponse);
    }

}
