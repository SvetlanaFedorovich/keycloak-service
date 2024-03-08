package mvp.project.keycloakservice.data;

import mvp.project.keycloakservice.model.dto.UserCredentialsDto;

public class TestData {
    public static String username = "admin";
    public static String password = "admin";
    public static String keycloakRealmName = "crm-service";
    public static UserCredentialsDto userDataTest = UserCredentialsDto.builder()
            .username("user")
            .password("password")
            .client_id("crm-base-service")
            .grant_type("password")
            .build();
}
