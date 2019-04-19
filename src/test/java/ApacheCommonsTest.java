import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Triple;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class ApacheCommonsTest {

    @BeforeSuite
    public void setupSuite() {
        System.out.println("Initializing suite");
    }

    @Test(groups = {"string"})
    public void testForStringUtils() {
        assertThat(StringUtils.isAllLowerCase("hello")).isTrue();
        assertThat(StringUtils.isAllUpperCase("HELLO")).isTrue();
        assertThat(StringUtils.isAlpha("123")).isFalse();
        assertThat(StringUtils.isMixedCase("HellO")).isTrue();
        assertThat(StringUtils.isWhitespace(" ")).isTrue();
        assertThat(StringUtils.isBlank("")).isTrue();
        assertThat(StringUtils.isNumeric("9999")).isTrue();

    }

    @Test(groups = {"array"})
    public void testForArrayUtils() {
        String[] array = {"a", "b", "D"};
        assertThat(ArrayUtils.contains(array, "D")).isTrue();
        assertThat(ArrayUtils.add(array, "F")).hasSize(4);
        assertThat(array.length).isEqualTo(3);
    }

    @Test(groups = {"array"})
    public void testForArrayUtilsImmutability() {
        String[] array = {"a", "b", "D"};
        assertThat(Arrays.hashCode(ArrayUtils.add(array, "F"))).isNotEqualTo(Arrays.hashCode(array));
        assertThat(Arrays.hashCode(array)).isEqualTo(Arrays.hashCode(array));
    }

    @Test(groups = {"number"}, expectedExceptions = {NumberFormatException.class})
    public void testForNumberUtilsImmutability() {
        assertThat(NumberUtils.compare(1L, 1L)).isEqualTo(0);
        assertThat(NumberUtils.toInt("1.9999")).isEqualTo(0);
        // Throws NumberFormatException but expectedExceptions caught it
        assertThat(NumberUtils.createInteger("1.9999")).isEqualTo(0);
        assertThat(NumberUtils.createInteger("19999")).isEqualTo(19999);
    }

    private MutablePair<String, String> pair;

    @BeforeTest(groups = {"pair"})
    public void setupPair() {
        pair = new MutablePair<String, String>("Hello", "World");
    }

    @Test(groups = {"pair"})
    public void testForPairUtils() {
        assertThat(pair.left).isEqualTo("Hello");
        assertThat(StringUtils.isMixedCase(pair.right)).isTrue();
        pair.setLeft("HELLO");
        pair.setRight("WORLD");
        assertThat(pair.right).isEqualTo("WORLD");
    }

    private Triple<String, Integer, Long> triple;

    @BeforeTest(groups = {"triple"})
    public void setupTriple() {
        triple = Triple.of("ME", 1, 9L);
    }

    @Test(groups = {"triple"})
    public void testForTripleUtils() {
        assertThat(triple.getLeft()).isEqualTo("ME");
        assertThat(triple.getMiddle()).isInstanceOf(Integer.class);
        assertThat(triple.getRight()).isInstanceOf(Long.class);
    }
}
