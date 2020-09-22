package io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean isSex() {
        return sex;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public University getUniversity() {
        return university;
    }

    public String[] getHobbies() {
        return hobbies;
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
                        + "\"name\":\"Ivan\""
                        + "\"university\":"
                        + "{"
                        + "\"accreditment\":4,"
                        + "},"
                        + "\"hobbies\":"
                        + "[\"reading\",\"badminton\"]"
                        + "}";
        final Student studentMod = gson.fromJson(personJson, Student.class);
        System.out.println(studentMod);

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonUniver = new JSONObject("{\"accreditment\":\"4}");
        List<String> list = new ArrayList<>();
        list.add("reading");
        list.add("badminton");
        JSONArray jsonHobbies = new JSONArray(list);

        jsonObject.put("sex", student.isSex());
        jsonObject.put("id", student.getId());
        jsonObject.put("name", student.getName());
        jsonObject.put("university", jsonUniver);
        jsonObject.put("statuses", jsonHobbies);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(student).toString());

    }
}
