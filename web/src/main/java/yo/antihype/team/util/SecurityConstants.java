package yo.antihype.team.util;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 3_600_000; // 60 minutes
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/sign-up";
    public static final String DELETE_ALL_USERS = "/users/delete/all";
}

