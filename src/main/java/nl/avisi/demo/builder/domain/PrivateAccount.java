package nl.avisi.demo.builder.domain;

import java.util.Objects;
import java.util.function.Consumer;

public class PrivateAccount extends Account {

    private String firstName;
    private String lastName;

    private PrivateAccount() {
    }

    public PrivateAccount(PrivateAccount source) {
        super(source);
        this.firstName = source.firstName;
        this.lastName = Objects.requireNonNull(source.lastName, "lastName cannot be null");
    }

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Builder cloneBuilder() {
        return new Builder(this, super.cloneBuilder(this));
    }

    public static Builder builder() {
        return new Builder(new PrivateAccount());
    }

    public static class Builder {

        private final PrivateAccount privateAccount;

        private final Account.Builder accountBuilder;

        private Builder(PrivateAccount source) {
            this(source, new Account.Builder(source));
        }

        private Builder(PrivateAccount source, Account.Builder accountBuilder) {
            this.privateAccount = source;
            this.accountBuilder = accountBuilder;
        }

        public Builder withAccountBuilder(Consumer<Account.Builder> configure) {
            configure.accept(accountBuilder);
            return this;
        }

        public Builder withFirstName(String firstName) {
            privateAccount.setFirstName(firstName);
            return this;
        }

        public Builder withLastName(String lastName) {
            privateAccount.setLastName(lastName);
            return this;
        }

        public PrivateAccount build() {
            return new PrivateAccount(privateAccount);
        }
    }

}
