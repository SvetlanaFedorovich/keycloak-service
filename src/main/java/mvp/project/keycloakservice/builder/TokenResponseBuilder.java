package mvp.project.keycloakservice.builder;


import mvp.project.authservice.TokenResponse;
import mvp.project.keycloakservice.model.response.KeycloakTokenResponse;

public class TokenResponseBuilder {
    public static TokenResponse tokenResponseBuild(KeycloakTokenResponse response) {
        return TokenResponse.newBuilder()
                .setAccessToken(response.access_token())
                .setRefreshToken(response.refresh_token())
                .setTokenType(response.token_type())
                .setExpiresIn(response.expires_in())
                .setSessionState(response.session_state())
                .build();
    }
}
