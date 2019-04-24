import io.vavr.Function4;
import io.vavr.Lazy;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class VavrTest {


    @BeforeSuite
    public void setupSuite() {
        System.out.println("Setting up Vavr suite");
    }

    @DataProvider(name = "fractions")
    public Object[][] providesFractions() {
        return new Object[][]{{1, 0}, {5, 0}, {4, 0}};
    }


    @Test(groups = {"try"}, dataProvider = "fractions")
    public void testForTry(final Integer dividend, final Integer divisor) {
        Try<Integer> t = Try.of(() -> dividend / divisor);
        assertThat(t.isFailure()).isTrue();
    }

    @Test(groups = {"option"})
    public void testForOption() {
        Option<String> optionNull = Option.of(null);
        Option<String> optionNotNull = Option.of("SSS");
        assertThat(optionNotNull).isNotNull();
        assertThat(optionNull).isNullOrEmpty();
    }

    @Test(groups = {"function"})
    public void testForFunction() {
        Function<Integer, Integer> square = (a) -> a * a;
        assertThat(square.apply(10)).isEqualTo(100);

        BiFunction<Integer, Integer, String> stringValueOfSum = (a, b) -> String.valueOf(a + b);
        assertThat(stringValueOfSum.apply(10, 10)).isEqualTo("20");

        Function4<Integer, Integer, Integer, Integer, Integer> sum = (a, b, c, d) -> a + b + c + d;
        assertThat(sum.apply(10, 10, 10, 10)).isEqualTo(40);
    }


    @Test(groups = {"collection"})
    public void testForCollectionApi() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        List<Integer> newIntegers = integers.append(6);
        assertThat(integers.hashCode()).isNotEqualTo(newIntegers.hashCode());

        assertThat(integers.filter(i -> i % 2 == 0)).hasSize(2);
        assertThat(newIntegers.filter(i -> i % 2 == 0)).hasSize(3);
    }

    @Test(groups = {"lazy"})
    public void testForLazy() {
        Lazy<Double> lazy = Lazy.of(Math::random);
        assertThat(lazy.isEvaluated()).isFalse();
        assertThat(lazy.get()).isNotNull();
    }

    @Test
    public void testForMapOfList() {
        Map<String, java.util.List<String>> map = new HashMap<>();
        map.put("x", new ArrayList<>());
        map.get("x").add("Hello");
        map.get("x").add("World");
        map.get("x").add("How you");

        assertThat(map.get("x")).isEqualTo(Arrays.asList("Hello", "World", "How you"));

        map.get("x").add("Hello");
        map.get("x").add("Hello");
        assertThat(map.get("x")).hasSize(5);


        map.get("x").add("Hello");
        map.get("x").add("Hello");
        java.util.List<String> l = map.get("x").stream().distinct().collect(Collectors.toList());
        assertThat(l).hasSize(3);

    }
}
