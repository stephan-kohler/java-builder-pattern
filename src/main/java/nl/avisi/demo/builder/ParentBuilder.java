package nl.avisi.demo.builder;

/**
 * A parent builder is used by base classes that other classes inherits from. The {@link nl.knb.cdr.kladrepertorium.common.builder.ParentBuilder#self()} method
 * allows the class to expose its builder methods, while at the same time return a reference to the implementing
 * class and not itself.
 */
public interface ParentBuilder<T, U> extends Builder<U>
{
    /**
     * Provide a self reference to the implementing class so that the parent class does not return
     * itself when its builder methods are used.
     *
     * @return The implementing class
     */
    T self();
}
