package com.easyimpl.jdk.study.compare;

import lombok.Data;

/**
 * Created by yupeng on 1/1/2017.
 */
@Data
public class Person implements Comparable<Person> {

    private String name;
    private Integer age;
    private Integer children;


    public long maxAge() {
        return age * children;
    }


    @Override
    public int compareTo(Person o) {
        if (this.maxAge() > o.maxAge()) {
            return 1;
        } else if (this.maxAge() == o.maxAge()) {
            return 0;
        } else {
            return -1;
        }

    }
}
