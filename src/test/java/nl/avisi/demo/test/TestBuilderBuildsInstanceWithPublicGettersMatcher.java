package nl.avisi.demo.test;

import nl.avisi.demo.builder.TestBuilder;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;


public class TestBuilderBuildsInstanceWithPublicGettersMatcher extends TypeSafeMatcher<TestBuilder<?>> {

    @Override
    protected boolean matchesSafely(TestBuilder<?> source) {
        Object instance = source.build();
        try {
            for (Field field : getDeclaredFields(instance.getClass())) {
                if (!hasPublicGetter(instance.getClass(), field)) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void describeMismatchSafely(TestBuilder<?> source, Description description) {
        Object instance = source.build();
        getDeclaredFields(instance.getClass()).stream()
                .filter(field -> !hasPublicGetter(instance.getClass(), field))
                .forEach(field ->
                        description.appendText(String.format("Field '%s' in class '%s' has no public getter.",
                                field.getName(),
                                instance.getClass().getName())));
    }

    @Override
    public void describeTo(Description description) {
    }

    private List<Field> getDeclaredFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        for (Field field : DomainTestUtil.getDeclaredFields(clazz, new ArrayList<>())) {
            if (!Modifier.isStatic(field.getModifiers()) && !field.getName().equals("serialVersionUID")) {
                fields.add(field);
            }
        }
        return fields;
    }

    private boolean hasPublicGetter(Class<?> clazz, Field field) {
        Method getMethod = DomainTestUtil.getDeclaredMethod(clazz, getGetMethodName(field));
        return getMethod != null && Modifier.isPublic(getMethod.getModifiers()) && getMethod.getReturnType().equals(field.getType());
    }

    private String getGetMethodName(Field field) {
        String fieldName = field.getName();
        if(field.getType().isPrimitive() && field.getType().equals(boolean.class)){
            return "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }else{
            return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }
    }
}
