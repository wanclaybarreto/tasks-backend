package wb.tasks_backend.security;

public abstract class SecurityConstants {

    public static final long JWT_EXPIRATION_TIME = 86400000; // 1 dia
    public static final String JWT_SECRET_KEY = "T4SkStHe5eCrEtK3Y";
    public static final String JWT_AUTHORIZATION_HEADER = "Authorization";
    public static final String JWT_TOKEN_PREFIX = "Bearer ";

}
