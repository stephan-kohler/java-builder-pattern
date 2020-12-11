package nl.avisi.demo.builder.domain;

import nl.avisi.demo.builder.CloneBuilder;

import java.util.Objects;

public class Deposit implements CloneBuilder<Deposit.Builder> {

    private Integer amount;

    private Deposit(Builder builder) {
        this.amount = Objects.requireNonNull(builder.amount, "amount cannot be null");
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public Builder cloneBuilder() {
        return builder()
                .withAmount(getAmount());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements nl.avisi.demo.builder.Builder<Deposit> {

        private Integer amount;

        private Builder() {
        }

        public Builder withAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        @Override
        public Deposit build() {
            return new Deposit(this);
        }
    }
}
