package mvp.project.keycloakservice;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static mvp.project.keycloakservice.data.TestData.keycloakRealmName;
import static mvp.project.keycloakservice.data.TestData.password;
import static mvp.project.keycloakservice.data.TestData.username;
@Testcontainers(disabledWithoutDocker = true)
public class KeycloakTestContainer implements BeforeAllCallback, AfterAllCallback {

    @Container
    public static final KeycloakContainer KEYCLOAK_CONTAINER = new KeycloakContainer("quay.io/keycloak/keycloak:23.0")
            .withRealmImportFile("/realm-export.json")
            .withAdminUsername(username)
            .withAdminPassword(password);

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        KEYCLOAK_CONTAINER.start();
        System.setProperty("keycloak.auth-server-url", KEYCLOAK_CONTAINER.getHost()+":"+ KEYCLOAK_CONTAINER.getHttpPort());
        System.setProperty("keycloak.realm", keycloakRealmName);
    }
    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        KEYCLOAK_CONTAINER.stop();
    }
}
