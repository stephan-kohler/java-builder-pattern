package nl.avisi.demo.builder.domain;

public class PrivateAccountTestBuilder extends AccountTestBuilder<PrivateAccountTestBuilder, PrivateAccount> {

    private final PrivateAccount.Builder builder;

    private PrivateAccountTestBuilder(PrivateAccount.Builder builder) {
        super(builder);
        this.builder = builder
                .withFirstName("John")
                .withLastName("Johnson");
    }

    public PrivateAccountTestBuilder withFirstName(String firstName) {
        this.builder.withFirstName(firstName);
        return self();
    }

    public PrivateAccountTestBuilder withLastName(String lastName) {
        this.builder.withLastName(lastName);
        return self();
    }

    public static PrivateAccountTestBuilder builder() {
        return new PrivateAccountTestBuilder(PrivateAccount.builder());
    }

    @Override
    public PrivateAccount build() {
        return this.builder.build();
    }

    @Override
    public PrivateAccountTestBuilder self() {
        return this;
    }


}
