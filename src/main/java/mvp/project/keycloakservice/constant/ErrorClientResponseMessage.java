package mvp.project.keycloakservice.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorClientResponseMessage {
    public static final String REALM_DOES_NOT_EXIST = "Realm с таким именем не существует";
    public static final String INVALID_USER_CREDENTIALS_OR_CLIENT = "Неправильный логин, пароль или имя клиента";
    public static final String SERVER_CONNECTION_ERROR = "Ошибка подключения к серверу, проверьте соединение";
    public static final String UNSUPPORTED_GRANT_TYPE = "Grant type с таким значением не поддерживается";
}