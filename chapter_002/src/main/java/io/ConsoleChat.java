package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {
    private List<String> answers = new ArrayList<>();

    public void speaking(String logPath, String ansPath) {

        try (BufferedReader consoleReder = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(logPath)));
             BufferedReader inAns = new BufferedReader(new FileReader(ansPath))) {
            System.out.println("write smth");
            while (consoleReder.read() != -1) {
                String s = consoleReder.readLine();
                if (s.equals("стоп")) {
                    answers.add(s);
                } else if (s.equals("закончить")) {
                    answers.add(s);
                    break;
                } else {
                    System.out.println(generateAnswer(inAns));
                    answers.add(s);
                }
            }
            StringBuilder sb = new StringBuilder();
            answers.forEach(s -> sb.append(s.trim()).append(System.lineSeparator()));
            out.write(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String generateAnswer(BufferedReader inAns) throws IOException {
        List<String> tmp = new ArrayList<>();
        while (inAns.ready()) {
            tmp.add(inAns.readLine());
        }
        String s = tmp.get((int) (Math.random() * (tmp.size() - 1)));
        answers.add(s);
        return s;
    }


    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat();
        consoleChat.speaking("./405.txt", "./404.txt");
    }


}
