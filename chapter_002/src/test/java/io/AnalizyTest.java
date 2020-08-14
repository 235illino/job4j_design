package io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Test
    public void whenPairWithoutComment() {
        String source = "/home/ilya/IdeaProjects/job4j_design/404.txt";
        String target = "/home/ilya/IdeaProjects/job4j_design/405.txt";
        Analizy analizy = new Analizy();
        analizy.unavailable(source, target);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(sb::append);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String rsl = "10:57:01;10:59:01; 11:01:02;11:02:02;";
        assertThat(sb.toString().trim(), is(rsl));
    }
}