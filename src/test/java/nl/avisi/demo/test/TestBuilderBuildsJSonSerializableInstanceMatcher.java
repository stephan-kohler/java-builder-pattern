package nl.avisi.demo.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.avisi.demo.builder.TestBuilder;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.unitils.reflectionassert.ReflectionComparator;
import org.unitils.reflectionassert.ReflectionComparatorFactory;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import org.unitils.reflectionassert.difference.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;


public class TestBuilderBuildsJSonSerializableInstanceMatcher extends TypeSafeMatcher<TestBuilder<?>> {

    private ObjectMapper objectMapper;

    public TestBuilderBuildsJSonSerializableInstanceMatcher(ObjectMapper objectMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper,"objectMapper");
    }

    @Override
    protected boolean matchesSafely(TestBuilder<?> source) {
        try {
            Difference difference = getDifference(source);
            return difference == null;
        } catch (Throwable e) {
            return false;
        }
    }

    @Override
    public void describeMismatchSafely(TestBuilder<?> source, Description description) {
        try {
            Difference difference = getDifference(source);
            if (difference != null) {
                Object result = difference.accept(new DifferenceVisitorImpl(), "\nDifferences: \n");
                description.appendText(String.format("Builder '%s' does not return a JSON serializable instance: %s", source.getClass().getSimpleName(), result.toString()));
            }
        } catch (Throwable exception) {
            StringWriter stringWriter = new StringWriter();
            exception.printStackTrace(new PrintWriter(stringWriter));
            description.appendText(String.format("Exception while testing JSON serialization using builder '%s': %s", source.getClass().getSimpleName(), stringWriter.toString()));
        }
    }

    private Difference getDifference(TestBuilder<?> source) throws IOException {
        Object o = source.build();
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            objectMapper.writeValue(byteArrayOutputStream, o);

            Object target = objectMapper.readValue(byteArrayOutputStream.toByteArray(), o.getClass());
            ReflectionComparator reflectionComparator = ReflectionComparatorFactory.createRefectionComparator(ReflectionComparatorMode.LENIENT_ORDER);
            return reflectionComparator.getDifference(o, target);
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("JSON serializable object");
    }

}
