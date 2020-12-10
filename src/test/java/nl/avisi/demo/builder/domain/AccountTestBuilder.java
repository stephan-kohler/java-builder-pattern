package nl.avisi.demo.builder.domain;

import nl.avisi.demo.builder.AbstractParentTestBuilder;
import nl.avisi.demo.builder.ParentTestBuilder;

public abstract class AccountTestBuilder<B extends AccountTestBuilder<B, T>, T extends Account> extends AbstractParentTestBuilder<B, T> {

    private Account.AbstractBuilder builder;

    protected AccountTestBuilder(Account.AbstractBuilder builder) {
        this.builder = builder
                .withAccountNumber("A001");
    }

    public B withAccountNumber(String accountNumber) {
        this.builder.withAccountNumber(accountNumber);
        return self();
    }

}
