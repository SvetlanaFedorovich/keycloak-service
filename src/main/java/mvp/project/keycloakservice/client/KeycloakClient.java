package mvp.project.keycloakservice.client;


import mvp.project.keycloakservice.model.dto.UserCredentialsDto;
import mvp.project.keycloakservice.model.response.KeycloakTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(
        name = "${spring.application.name}",
        url = "${keycloak.auth-server-url}"
)
@Component
public interface KeycloakClient {

    @PostMapping(path = "/realms/${keycloak.realm}/protocol/openid-connect/token",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    Optional<KeycloakTokenResponse> getToken(@RequestBody UserCredentialsDto userCredentialsDto);
}