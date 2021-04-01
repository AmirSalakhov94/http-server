package tech.itpark.http.enums;

import java.util.HashSet;
import java.util.Set;

public enum HttpMethod {
    GET,
    POST;

    private static Set<String> _values = new HashSet<>();

    static {
        for (HttpMethod choice : HttpMethod.values()) {
            _values.add(choice.name());
        }
    }

    public static boolean contains(String value) {
        return _values.contains(value);
    }
}
