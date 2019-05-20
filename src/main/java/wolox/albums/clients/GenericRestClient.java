package wolox.albums.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GenericRestClient {
    public static final String EXTERNAL_SERVICE_URL = "https://jsonplaceholder.typicode.com";

    protected RestTemplate restTemplate;

    @Autowired
    public GenericRestClient(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

}
