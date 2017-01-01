package com.easyimpl.jdk.study.compare;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yupeng on 1/1/2017.
 */
public class PersonComparetorTest {


    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        try {

            List<String> personTest = FileUtils.readLines(new File("src/main/java/com/easyimpl/jdk/study/compare/PersonTest"), "UTF-8");
            for (String str : personTest) {
                Person person = new Person();
                String[] split = str.split("\\|\\|");
                person.setName(split[0]);
                person.setAge(Integer.parseInt(split[1]));
                person.setChildren(Integer.parseInt(split[2]));
                personList.add(person);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(personList);
        Collections.sort(personList);
        System.out.println(personList);

        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return (o1.maxAge() > o2.maxAge()) ? -1 : (o1.maxAge() == o2.maxAge()) ? 0 : 1;
            }
        });
        System.out.println(personList);
    }
}
