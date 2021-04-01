package nl.avisi.demo.builder.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Account {

    private String accountNumber;

    private List<Deposit> deposits;

    protected Account() {
    }

    protected Account(Account account) {
        Objects.requireNonNull(account, "account cannot be null");
        this.accountNumber = Objects.requireNonNull(account.accountNumber, "accountNumber cannot be null");
        this.deposits = account.deposits;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    private void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    private void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    protected Builder cloneBuilder(Account source) {
        return new Builder(source)
                // Make a deep clone of the deposits
                .withDeposits(getDeposits().stream().map(deposit -> deposit.cloneBuilder().build()).collect(Collectors.toList()));
    }

    public static class Builder {
        private final Account account;

        protected Builder(Account account) {
            this.account = account;
        }

        public Builder withAccountNumber(String accountNumber) {
            account.setAccountNumber(accountNumber);
            return this;
        }

        public Builder withDeposits(List<Deposit> deposits) {
            account.setDeposits(deposits);
            return this;
        }
    }

}
