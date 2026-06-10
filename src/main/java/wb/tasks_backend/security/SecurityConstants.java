package wb.tasks_backend.security;

public abstract class SecurityConstants {

    public static final long JWT_EXPIRATION_TIME = 86400000; // 1 dia
    public static final String JWT_SECRET_KEY = "T4SkStHe5eCrEtK3Y-" +
            "RU#H29Qge5Sqr#CrC9-n^tyEBe222-Q" +
            "?'}3GTfjJmVmx.$9}7iP9B9l0W_3Sd2" +
            "9Lo643t4iWF76]IZy49y_KF6Jty\"$dI0";
    public static final String JWT_AUTHORIZATION_HEADER = "Authorization";
    public static final String JWT_TOKEN_PREFIX = "Bearer ";

}
