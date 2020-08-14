package collection;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {

    @Test
    public void whenAdded2() {

        List<Analize.User> previous = Arrays.asList(
                new Analize.User(111, "A")
        );
        List<Analize.User> current = Arrays.asList(
                new Analize.User(111, "A"),
                new Analize.User(222, "B"),
                new Analize.User(333, "C")
        );
        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);
        int rsl = info.added;
        int ext = 2;
        assertThat(ext, is(rsl));
    }
    @Test
    public void whenDelete3() {

        List<Analize.User> previous = Arrays.asList(
                new Analize.User(111, "A"),
                new Analize.User(222, "B"),
                new Analize.User(333, "C"),
                new Analize.User(444, "D")
        );
        List<Analize.User> current = Arrays.asList(
                new Analize.User(111, "A")
        );
        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);
        int rsl = info.deleted;
        int ext = 3;
        assertThat(ext, is(rsl));
    }
    @Test
    public void whenChanged2() {

        List<Analize.User> previous = Arrays.asList(
                new Analize.User(111, "A"),
                new Analize.User(222, "B"),
                new Analize.User(333, "C"),
                new Analize.User(444, "D")
        );
        List<Analize.User> current = Arrays.asList(
                new Analize.User(111, "B"),
                new Analize.User(222, "A"),
                new Analize.User(333, "C"),
                new Analize.User(444, "C")

        );
        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);
        int rsl = info.changed;
        int ext = 3;
        assertThat(ext, is(rsl));
    }
}