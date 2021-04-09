package nl.avisi.demo.test;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class InstanceHasStaticBuildMethodMatcher extends TypeSafeMatcher {

    @Override
    protected boolean matchesSafely(Object serializable) {
        try {
            Method builderMethod = serializable.getClass().getDeclaredMethod("builder");

            //If there is a builder method we check that it is public and has a build method
            if (!Modifier.isPublic(builderMethod.getModifiers())) {
                return false;
            }
            Class<?> builderClass = builderMethod.getReturnType();
            Method buildMethod = builderClass.getDeclaredMethod("build");
            if (!Modifier.isPublic(buildMethod.getModifiers())) {
                return false;
            }

            //Check that the build method of the test builder and the class builder returns the same type
            if (buildMethod.getReturnType() != serializable.getClass()) {
                return false;
            }
        } catch (NoSuchMethodException e) {
            //We do not require that the builder method exists but if it does we do the checks
            return true;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void describeMismatchSafely(Object serializable, Description description) {
        description.appendText(String.format("Class '%s' does not conform to builder pattern (a public builder() method that returns a builder with a public build() method)", serializable.getClass().getSimpleName()));
    }

    @Override
    public void describeTo(Description description) {
    }
}
