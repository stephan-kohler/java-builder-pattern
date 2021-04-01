package nl.avisi.demo.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.unitils.reflectionassert.ReflectionComparator;
import org.unitils.reflectionassert.ReflectionComparatorFactory;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import org.unitils.reflectionassert.difference.Difference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class InstanceIsJsonSerializableMatcher extends TypeSafeMatcher<Object> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected boolean matchesSafely(Object instance) {
        try {
            Difference difference = getDifference(instance);
            return difference == null;
        } catch (Throwable e) {
            return false;
        }
    }

    @Override
    public void describeMismatchSafely(Object instance, Description description) {
        try {
            Difference difference = getDifference(instance);
            if (difference != null) {
                Object result = difference.accept(new DifferenceVisitorImpl(), "\nDifferences: \n");
                description.appendText(String.format("TestBuilder for '%s' does not return a JSON serializable instance: %s", instance.getClass().getSimpleName(), result.toString()));
            }
        } catch (Throwable exception) {
            StringWriter stringWriter = new StringWriter();
            exception.printStackTrace(new PrintWriter(stringWriter));
            description.appendText(String.format("Exception while testing JSON serialization using TestBuilder for '%s': %s", instance.getClass().getSimpleName(), stringWriter.toString()));
        }
    }

    private Difference getDifference(Object instance) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            objectMapper.writeValue(byteArrayOutputStream, instance);

            Object target = objectMapper.readValue(byteArrayOutputStream.toByteArray(), instance.getClass());
            ReflectionComparator reflectionComparator = ReflectionComparatorFactory.createRefectionComparator(ReflectionComparatorMode.LENIENT_ORDER);
            return reflectionComparator.getDifference(instance, target);
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("JSON serializable object");
    }

}
