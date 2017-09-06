package org.shumakriss.demo.data;

import org.kie.internal.process.CorrelationKey;
import org.kie.internal.process.CorrelationProperty;

import java.util.List;

public class MyCorrelationProperty implements CorrelationProperty {

    private String trackingNumber;

    public MyCorrelationProperty(String trackingNumber){
        this.trackingNumber = trackingNumber;
    }

    @Override
    public String getName() {
        return "Tracking Number";
    }

    @Override
    public String getType() {
        return "java.lang.String";
    }

    @Override
    public Object getValue() {
        return this.trackingNumber;
    }
}
