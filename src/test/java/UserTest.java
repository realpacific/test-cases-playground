import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserTest {

    private List<User> users = Arrays.asList(
            new User("Prashant", "password"),
            new User("User", "user123"),
            new User("admin", "admin")
    );

    @Test
    public void mappingTestUsingHamcrestAndAssertj() {
        Assertions.assertThat(users.stream().map(User::getPassword).collect(Collectors.toList()))
                .isSubsetOf(Arrays.asList("password", "user123", "admin"));
        MatcherAssert.assertThat(users.stream().map(User::getPassword).collect(Collectors.toList()), CoreMatchers.hasItem("admin"));
        MatcherAssert.assertThat(users.stream().map(User::getPassword).collect(Collectors.toList()), CoreMatchers.not(CoreMatchers.hasItem("User")));

    }
}
