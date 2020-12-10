package nl.avisi.demo.builder;

/**
 * {@code TestBuilder}s are used by tests to build default, pre-populated instances of (domain) classes.
 */
public interface TestBuilder<T> {

    T build();

    Class<?> type();
}

