package io.debezium.connector.jdbc.naming;

import io.debezium.connector.jdbc.util.NamingStyle;
import io.debezium.connector.jdbc.util.NamingStyleUtils;
import io.debezium.sink.DebeziumSinkRecord;
import io.debezium.sink.naming.CollectionNamingStrategy;

import java.util.Map;

/**
 * Custom implementation of the {@link CollectionNamingStrategy}.
 * Supports various naming styles and optional prefix/suffix.
 *
 * @author Gustavo Lira
 */
public class CustomCollectionNamingStrategy implements CollectionNamingStrategy {

    private String prefix = "";
    private String suffix = "";
    private NamingStyle namingStyle = NamingStyle.DEFAULT;

    @Override
    public void configure(Map<String, String> properties) {
        this.prefix = properties.getOrDefault("collection.naming.prefix", "");
        this.suffix = properties.getOrDefault("collection.naming.suffix", "");
        String style = properties.getOrDefault("collection.naming.style", NamingStyle.DEFAULT.getValue());
        this.namingStyle = NamingStyle.from(style);
    }

    @Override
    public String resolveCollectionName(DebeziumSinkRecord record, String collectionFormat) {
        String transformedName = NamingStyleUtils.applyNamingStyle(collectionFormat, namingStyle);
        return (prefix != null ? prefix : "") + transformedName + (suffix != null ? suffix : "");
    }
}
