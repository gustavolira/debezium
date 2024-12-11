package io.debezium.connector.jdbc.util;

import io.debezium.util.Strings;

import java.util.Locale;

/**
 * Utility class for applying various naming styles (e.g., snake_case, camelCase, UPPER_CASE, LOWER_CASE)
 * to a given name. This supports flexible transformations based on the specified naming style.
 * The transformations are:
 * - SNAKE_CASE: Converts camelCase or PascalCase to snake_case.
 * - CAMEL_CASE: Converts snake_case to camelCase.
 * - UPPER_CASE: Converts all characters to uppercase.
 * - LOWER_CASE: Converts all characters to lowercase.
 * - DEFAULT: No transformation is applied.
 * This class is used by naming strategies to enforce naming conventions.
 *
 * @author Gustavo Lira
 */
public class NamingStyleUtils {

    /**
     * Applies a naming style to a given name.
     *
     * @param name  the original name
     * @param style the naming style as an enum
     * @return the transformed name
     */
    public static String applyNamingStyle(String name, NamingStyle style) {
        if (name == null || style == null) {
            throw new IllegalArgumentException("Name and style must not be null");
        }

        return switch (style) {
            case SNAKE_CASE -> Strings.toSnakeCase(name);
            case CAMEL_CASE -> Strings.convertDotAndUnderscoreStringToCamelCase(name);
            case UPPER_CASE -> name.toUpperCase(Locale.ROOT);
            case LOWER_CASE -> name.toLowerCase(Locale.ROOT);
            default -> name; // Default: no transformation
        };
    }

}
