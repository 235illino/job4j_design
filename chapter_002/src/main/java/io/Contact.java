package io;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int id;
    private final String email;

    public Contact(int id, String phone) {
        this.id = id;
        this.email = phone;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(111, "email@email.com");

        /* Запись объекта в файл */
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }

        /* Чтение объекта из файла */
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}