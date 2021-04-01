package nl.avisi.demo.builder.domain;

import java.util.Objects;

public class Deposit {

    private Integer amount;

    private Deposit() {
    }

    private Deposit(Deposit source) {
        this.amount = Objects.requireNonNull(source.amount, "amount cannot be null");
    }

    public Integer getAmount() {
        return amount;
    }

    private void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Builder cloneBuilder() {
        return builder()
                .withAmount(getAmount());
    }

    public static Builder builder() {
        return new Builder(new Deposit());
    }

    public static class Builder {

        private final Deposit deposit;

        private Builder(Deposit source) {
            deposit = source;
        }

        public Builder withAmount(Integer amount) {
            deposit.setAmount(amount);
            return this;
        }

        public Deposit build() {
            return new Deposit(deposit);
        }
    }
}
