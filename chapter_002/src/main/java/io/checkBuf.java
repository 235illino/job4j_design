package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class checkBuf {
    public static void main(String[] args) {
        List<String> st = new ArrayList<>();
        try {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (reader.ready()){
            st.add(reader.readLine());
            if (st.get(0).equals("0")) {
                reader.close();
                break;
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        st.forEach(System.out::println);
        System.out.println("ghb");

    }
}
