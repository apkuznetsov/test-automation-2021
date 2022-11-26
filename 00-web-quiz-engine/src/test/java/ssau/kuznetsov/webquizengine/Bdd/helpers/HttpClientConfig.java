package ssau.kuznetsov.webquizengine.Bdd.helpers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class HttpClientConfig {

    public static final String HOST = "http://localhost:";
    public static final int PORT = 8080;

    public static final String ADMIN_TRUNCATE_DATABASE = "/admin/truncate/database";
    public static final String ADMIN_CREATE_QUIZZES = "/admin/create/quizzes";
    public static final String CLIENT_API_QUIZZES = "/api/quizzes";
    public static final String CLIENT_API_QUIZ_PAGE = "/api/quizzes/page/";
    public static final String CLIENT_API_QUIZ = "/api/quiz";

    public static final HttpHeaders ADMIN_HTTP_HEADERS;
    public static final HttpEntity ADMIN_HTTP_ENTITY;

    static {
        ADMIN_HTTP_HEADERS = new HttpHeaders();
        String plainCreds = "example@example.com" + ":" + "qwerty";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String authHeader = "Basic " + new String(base64CredsBytes);
        ADMIN_HTTP_HEADERS.add("Authorization", authHeader);

        ADMIN_HTTP_ENTITY = new HttpEntity<>(null, ADMIN_HTTP_HEADERS);
    }
}
