package nl.avisi.demo.builder;

/**
 * Interface for parent test.
 * Must implement the self() method for inheritance to work.
 */
public interface ParentTestBuilder<T, U> extends TestBuilder<U> {

    T self();
}
