package tech.itpark.http.enums;

import java.util.HashSet;
import java.util.Set;

public enum HttpMethod {
    GET,
    POST;

    private static Set<String> httpMethods = new HashSet<>();

    static {
        for (HttpMethod choice : HttpMethod.values()) {
            httpMethods.add(choice.name());
        }
    }

    public static boolean contains(String value) {
        return httpMethods.contains(value);
    }
}
