package org.shumakriss.demo.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Chris on 9/4/16.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String secondName;
    private String thirdName;

    protected MyEntity() {}

    public MyEntity(Long id, String name, String secondName, String thirdName) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.thirdName = thirdName;
    }

    @Override
    public String toString() {
        return String.format("{id=%d, name='%s', secondName='%s', thirdName='%s'}", id, name, secondName, thirdName);
    }
}
