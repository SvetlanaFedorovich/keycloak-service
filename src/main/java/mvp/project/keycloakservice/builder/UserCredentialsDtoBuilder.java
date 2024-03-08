package mvp.project.keycloakservice.builder;


import mvp.project.authservice.TokenRequest;
import mvp.project.keycloakservice.model.dto.UserCredentialsDto;

public class UserCredentialsDtoBuilder {

    public static UserCredentialsDto userCredentialsDtoBuild(TokenRequest tokenRequest) {
        return UserCredentialsDto.builder()
                .client_id(tokenRequest.getClientId())
                .grant_type(tokenRequest.getGrantType())
                .username(tokenRequest.getUsername())
                .password(tokenRequest.getPassword())
                .build();
    }
}