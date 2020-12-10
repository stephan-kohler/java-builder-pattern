package nl.avisi.demo.builder;

import java.lang.reflect.ParameterizedType;

/**
 * Abstract classes for creating a {@link TestBuilder}.
 */
public abstract class AbstractTestBuilder<T> implements TestBuilder<T> {

    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractTestBuilder() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public Class<?> type() {
        return persistentClass;
    }
}
