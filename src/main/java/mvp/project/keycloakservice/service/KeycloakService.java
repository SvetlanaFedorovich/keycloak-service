package mvp.project.keycloakservice.service;

import lombok.RequiredArgsConstructor;
import mvp.project.keycloakservice.client.KeycloakClient;
import mvp.project.keycloakservice.model.dto.UserCredentialsDto;
import mvp.project.keycloakservice.model.response.KeycloakTokenResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final KeycloakClient keycloakClient;

    public Optional<KeycloakTokenResponse> getToken(UserCredentialsDto userCredentialsDto){
        return keycloakClient.getToken(userCredentialsDto);
    }
}
