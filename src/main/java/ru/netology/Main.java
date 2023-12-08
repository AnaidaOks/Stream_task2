package ru.netology;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

        List<String> recrut = persons.stream().filter(p -> p.getAge() >= 18 && p.getAge() <= 27)
                .map(p -> p.getFamily()).collect(Collectors.toList());
        System.out.println("Призывники:");
        // System.out.println(recrut);

        List<Person> worker = persons.stream()
                .filter(p -> p.getSex() == Sex.MAN
                        ? p.getAge() >= 18 && p.getAge() <= 65
                        : p.getAge() >= 18 && p.getAge() <= 60)
                .filter(p -> p.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .collect(Collectors.toList());

        System.out.println("Потенциальные работники с высшим образованием:");
        System.out.println(worker);

    }
}