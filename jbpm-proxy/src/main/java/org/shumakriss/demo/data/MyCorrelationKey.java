package org.shumakriss.demo.data;

import org.kie.internal.process.CorrelationKey;
import org.kie.internal.process.CorrelationProperty;

import java.util.ArrayList;
import java.util.List;

public class MyCorrelationKey implements CorrelationKey {

    public static final String NAME = "Tracking Number";
    private List<CorrelationProperty<?>> props = new ArrayList<CorrelationProperty<?>>();

    public MyCorrelationKey(String trackingNumber){
        MyCorrelationProperty prop = new MyCorrelationProperty(trackingNumber);
        this.props.add(prop);
    }

    @Override
    public String getName() {
        return MyCorrelationKey.NAME;
    }

    @Override
    public List<CorrelationProperty<?>> getProperties() {
        return this.props;
    }

    @Override
    public String toExternalForm() {
        String externalForm = "";
        for(CorrelationProperty<?> prop : props)
            externalForm += prop.getValue();
        return externalForm;
    }
}
