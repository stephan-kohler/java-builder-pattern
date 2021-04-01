package nl.avisi.demo.builder.domain;

import java.util.List;

public class AccountTestBuilder {

    public static Account.Builder builder(Account.Builder source) {
        return source
                .withAccountNumber("A001")
                .withDeposits(List.of(DepositTestBuilder.builder().build()));
    }

}
