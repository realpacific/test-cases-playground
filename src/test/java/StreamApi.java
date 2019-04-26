import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamApi {

    @Test
    public void testForIntegerStream() {
        int[] array = {9, 5, 6, 4, 7, 1, 0, 8};
        assertThat(IntStream.of(array).max().getAsInt()).isEqualTo(9);

        int[] cloned = array.clone();
        Assert.assertArrayEquals(IntStream.of(cloned).distinct().sorted().limit(3).toArray(), new int[]{0, 1, 4});
        Assert.assertArrayEquals(IntStream.of(cloned).distinct().sorted().filter(e -> e % 2 == 0).toArray(), new int[]{0, 4, 6, 8});
    }

    @Test
    public void testForObjectStreams() {
        List<String> list = Arrays.asList("Hello", "Abc", "Def", "GHIJKL");

        list.stream().sorted(Comparator.comparing(String::length).reversed()).forEach(System.out::print);
        System.out.println(list.stream().sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.joining("; ")));
        assertThat(list.stream().collect(Collectors.groupingBy(String::length)))
                .hasEntrySatisfying(3, (s) -> s.containsAll(Arrays.asList("Abc", "Def")));
    }

}
