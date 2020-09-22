package io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Student {
    private final boolean sex;
    private final int id;
    private final String name;
    private final University university;
    private final String[] hobbies;

    public Student(boolean sex, int id, String name, University university, String... hobbies) {
        this.sex = sex;
        this.id = id;
        this.name = name;
        this.university = university;
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Student{"
                + "sex=" + sex
                + ", id=" + id
                + ", name=" + name
                + ", university=" + university
                + ", hobbies=" + Arrays.toString(hobbies)
                + '}';
    }


    public static void main(String[] args) {
        final Student student = new Student(false, 123, "Ivan", new University(4), "reading","badminton");

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(student));

        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"id\":123,"
                        + "\"phone\":\"Ivan\""
                        + "\"university\":"
                        + "{"
                        + "\"accreditment\":4,"
                        + "},"
                        + "\"hobbies\":"
                        + "[\"reading\",\"badminton\"]"
                        + "}";
        final Student studentMod = gson.fromJson(personJson, Student.class);
        System.out.println(studentMod);
    }
}
