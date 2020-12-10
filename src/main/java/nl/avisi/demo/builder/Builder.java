package nl.avisi.demo.builder;

/**
 * A builder interface used by anything that implements the builder pattern.
 *
 * = Usage
 *
 * [source,java]
 * ----
 * public class SomeClass{
 *
 *    private String attribute;
 *
 *    private SomeClass(AClassBuilder builder){
 *        this.attribute = builder.attribute;
 *    }
 *
 *    public static class SomeClassBuilder implements Builder<SomeClass>{
 *
 *        private String attribute;
 *
 *        public SomeClassBuilder withAttribute(String attribute){
 *            this.attribute = attribute;
 *            return this;
 *        }
 *
 *        public SomeClass build(){
 *            new SomeClass(this);
 *        }
 *    }
 * }
 *
 *----
 */
public interface Builder<T>
{
    T build();
}