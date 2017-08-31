package org.shumakriss.demo.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Chris on 9/4/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {
    private Long id;
    private String quote;

    public Value() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "org.shumakriss.demo.data.Value{" + "id=" + id + ", quote='" + quote + '\'' + '}';
    }
}
