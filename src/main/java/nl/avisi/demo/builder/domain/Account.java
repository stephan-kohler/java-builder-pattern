package nl.avisi.demo.builder.domain;

import nl.avisi.demo.builder.ParentBuilder;
import nl.avisi.demo.builder.ParentCloneBuilder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class Account implements ParentCloneBuilder<Account.AbstractBuilder<? extends Account.AbstractBuilder<?, ?>, ?>> {

    private String accountNumber;

    private List<Deposit> deposits;

    protected Account() {
    }

    protected Account(AbstractBuilder builder) {
        this.accountNumber = Objects.requireNonNull(builder.accountNumber, "accountNumber cannot be null");
        this.deposits = builder.deposits;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    @Override
    public AbstractBuilder<?, ?> cloneBuilder(AbstractBuilder<?, ?> builder) {
        return builder
                .withAccountNumber(getAccountNumber())
                .withDeposits(getDeposits().stream().map(deposit -> deposit.cloneBuilder().build()).collect(Collectors.toList()));
    }

    public static abstract class AbstractBuilder<B extends AbstractBuilder<B, T>, T extends Account> implements ParentBuilder<B, T> {

        private String accountNumber;

        private List<Deposit> deposits;

        public B withAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return self();
        }

        public B withDeposits(List<Deposit> deposits) {
            this.deposits = deposits;
            return self();
        }
    }

}
