package nl.avisi.demo.builder.domain;

import nl.avisi.demo.builder.ParentBuilder;
import nl.avisi.demo.builder.ParentCloneBuilder;

public abstract class Account implements ParentCloneBuilder<Account.AbstractBuilder<? extends Account.AbstractBuilder<?, ?>, ?>> {

    private String accountNumber;

    protected Account(AbstractBuilder builder) {
        this.accountNumber = builder.accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public AbstractBuilder<?, ?> cloneBuilder(AbstractBuilder<?, ?> builder) {
        return builder.withAccountNumber(getAccountNumber());
    }

    public static abstract class AbstractBuilder<B extends AbstractBuilder<B, T>, T extends Account> implements ParentBuilder<B, T> {

        private String accountNumber;

        public B withAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return self();
        }
    }

}
