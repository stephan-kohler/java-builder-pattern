package nl.avisi.demo.test;

import nl.avisi.demo.builder.TestBuilder;
import nl.avisi.demo.builder.domain.DepositTestBuilder;
import nl.avisi.demo.builder.domain.PrivateAccountTestBuilder;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.List;

public class DomainObjectUnitTest {

    @Test
    public void testDomainObjects() {
        List<Matcher<TestBuilder<?>>> matchers = List.of(
                new InstanceHasPublicGettersMatcher(),
                new InstanceHasStaticBuildMethodMatcher(),
                new InstanceIsJsonSerializableMatcher());

        List<TestBuilder<?>> testBuilders = List.of(
                DepositTestBuilder.builder(),
                PrivateAccountTestBuilder.builder());

        testBuilders.forEach(builder -> matchers.forEach(((matcher) -> MatcherAssert.assertThat(builder, matcher))));
    }
}
