package nl.avisi.demo.builder;

/**
 *
 */
public interface ParentCloneBuilder<B extends Builder<?>> {

    B cloneBuilder(B builder);
}


