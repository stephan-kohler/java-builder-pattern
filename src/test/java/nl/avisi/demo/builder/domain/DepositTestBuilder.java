package nl.avisi.demo.builder.domain;

import nl.avisi.demo.builder.AbstractTestBuilder;

public class DepositTestBuilder extends AbstractTestBuilder<Deposit> {

    private final Deposit.Builder builder;

    private DepositTestBuilder(Deposit.Builder builder) {
        this.builder = builder
                .withAmount(100);
    }

    public DepositTestBuilder withAmount(Integer amount) {
        this.builder.withAmount(amount);
        return this;
    }

    public static DepositTestBuilder builder() {
        return new DepositTestBuilder(Deposit.builder());
    }

    @Override
    public Deposit build() {
        return this.builder.build();
    }

}
