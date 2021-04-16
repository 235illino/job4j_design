package io;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        byte dateBirth = 5;
        short monthBirth = 7;
        long yearBirth = 1990;
        boolean man = true;
        float height = 150.0f;
        double weight = 75.6;
        LOG.debug("User info name : {}, age : {}, dateBirth : {}, monthBirth : {}, yearBirth : {}, man : {}, " + "height : {}, weight : {}", name, age, dateBirth, monthBirth, yearBirth, man, height, weight);
    }
}
