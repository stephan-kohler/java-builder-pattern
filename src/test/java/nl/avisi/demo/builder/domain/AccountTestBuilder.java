package nl.avisi.demo.builder.domain;

import nl.avisi.demo.builder.AbstractParentTestBuilder;

import java.util.List;

public abstract class AccountTestBuilder<B extends AccountTestBuilder<B, T>, T extends Account> extends AbstractParentTestBuilder<B, T> {

    private Account.AbstractBuilder builder;

    protected AccountTestBuilder(Account.AbstractBuilder builder) {
        this.builder = builder
                .withAccountNumber("A001")
                .withDeposits(List.of(DepositTestBuilder.builder().build()));
    }

    public B withAccountNumber(String accountNumber) {
        this.builder.withAccountNumber(accountNumber);
        return self();
    }

}
