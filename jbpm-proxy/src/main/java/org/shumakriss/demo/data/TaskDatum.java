package org.shumakriss.demo.data;

/**
 * Created by chris on 9/3/17.
 */
public class TaskDatum {

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    private String type;
    private String name;

    @Override
    public String toString() {
        return "TaskDatum{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    private Object value;


}
