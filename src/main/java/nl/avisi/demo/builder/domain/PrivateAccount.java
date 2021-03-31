package nl.avisi.demo.builder.domain;

import nl.avisi.demo.builder.CloneBuilder;

import java.util.Objects;

public class PrivateAccount extends Account implements CloneBuilder<PrivateAccount.Builder> {

    private String firstName;
    private String lastName;

    private PrivateAccount() {
    }

    private PrivateAccount(Builder builder) {
        super(builder);
        this.firstName = builder.firstName;
        this.lastName = Objects.requireNonNull(builder.lastName, "lastName cannot be null");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public Builder cloneBuilder() {
        Builder builder = builder();
        super.cloneBuilder(builder);
        return builder
                .withFirstName(getFirstName())
                .withLastName(getLastName());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Account.AbstractBuilder<Builder, PrivateAccount> {

        private String firstName;
        private String lastName;

        private Builder() {
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return self();
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return self();
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public PrivateAccount build() {
            return new PrivateAccount(this);
        }
    }

}
