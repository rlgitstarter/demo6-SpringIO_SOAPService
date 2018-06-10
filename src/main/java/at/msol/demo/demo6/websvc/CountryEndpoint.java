package at.msol.demo.demo6.websvc;

/*
@Endpoint registers the class with Spring WS as a potential candidate for processing incoming
SOAP messages.
@PayloadRoot is then used by Spring WS to pick the handler method based on the message’s namespace
and localPart.
@RequestPayload indicates that the incoming message will be mapped to the method’s request parameter.
The @ResponsePayload annotation makes Spring WS map the returned value to the response payload.
*/

import at.msol.demo.demo6.countriesgenerated.GetCountryRequest;
import at.msol.demo.demo6.countriesgenerated.GetCountryResponse;
import at.msol.demo.demo6.domain.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {
    // namespace & localPart
    private static final String NAMESPACE_URI = "http://demo6.demo.msol.at/countriesgenerated";
    private CountryRepository countryRepository;

    // Konstruktor mit Parameter
    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));
        return response;
    }

}


