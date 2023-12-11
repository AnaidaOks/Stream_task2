package ru.netology;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long personAge18 = persons.stream().filter(p -> p.getAge() < 18).count();
        System.out.println("Количество несовершеннолетних: " + personAge18);

        System.out.println("Призывники:");
        persons.stream().filter(p -> p.getAge() >= 18 && p.getAge() <= 27)
                .map(p -> p.getFamily()).forEach(System.out::println);


        System.out.println("Потенциальные работники с высшим образованием:");
        persons.stream()
                .filter(p -> p.getSex() == Sex.MAN
                        ? p.getAge() >= 18 && p.getAge() <= 65
                        : p.getAge() >= 18 && p.getAge() <= 60)
                .filter(p -> p.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .forEach(System.out::println);
    }
}