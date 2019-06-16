package micronaut.conference.presentation;

import io.micronaut.context.ApplicationContext;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConferenceEndpointTest {

    private static EmbeddedServer server;
    private static HttpClient client;

    @BeforeAll
    static void setUpServer() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server.getApplicationContext()
                .createBean(HttpClient.class, server.getURL());
    }

    @AfterAll
    static void stopServer() {
        if (nonNull(server)) {
            server.stop();
        }
        if (nonNull(client)) {
            client.stop();
        }
    }

    @Test
    void getConferencesTest() {
        List<ConferenceResponse> conferences = client.toBlocking()
                .retrieve(HttpRequest.GET("/conferences"), Argument.of(List.class, ConferenceResponse.class));

        assertEquals(conferences.size(), 1);

        ConferenceResponse conference = conferences.get(0);
        assertAll("should return conference information",
                () -> assertEquals(1, conference.id),
                () -> assertEquals("TDC", conference.name));
    }

}