package nl.avisi.demo.builder;

import java.lang.reflect.ParameterizedType;

/**
 * {@code AbstractParentTestBuilder}s are used to populate the common parts for classes that uses inheritance.
 */
public abstract class AbstractParentTestBuilder<T, U> implements nl.avisi.demo.builder.ParentTestBuilder<T, U>, nl.avisi.demo.builder.TestBuilder<U> {

    private Class<U> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractParentTestBuilder() {
        this.persistentClass = (Class<U>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Override
    public Class<?> type() {
        return persistentClass;
    }
}