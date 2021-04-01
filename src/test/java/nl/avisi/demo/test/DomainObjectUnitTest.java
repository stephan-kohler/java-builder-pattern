package nl.avisi.demo.test;

import nl.avisi.demo.builder.domain.DepositTestBuilder;
import nl.avisi.demo.builder.domain.PrivateAccountTestBuilder;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DomainObjectUnitTest {

    @Test
    public void testDomainObjects() {
        List<Matcher<Object>> matchers = List.of(
                new InstanceHasPublicGettersMatcher(),
                new InstanceHasStaticBuildMethodMatcher(),
                new InstanceIsJsonSerializableMatcher());

        List<?> testObjects = List.of(
                DepositTestBuilder.builder().build(),
                PrivateAccountTestBuilder.builder().build());

        testObjects.forEach(builder -> matchers.forEach(((matcher) -> MatcherAssert.assertThat(builder, matcher))));
    }
}
