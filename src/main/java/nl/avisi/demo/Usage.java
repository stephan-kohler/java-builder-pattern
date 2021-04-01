package nl.avisi.demo;

import nl.avisi.demo.builder.domain.Deposit;
import nl.avisi.demo.builder.domain.PrivateAccount;

import java.util.List;

public class Usage {

    private PrivateAccount privateAccount;

    private void usePrivateAccount() {
        privateAccount = PrivateAccount.builder()
                .withAccountBuilder(builder -> builder
                        .withAccountNumber("A001")
                        .withDeposits(List.of(Deposit.builder()
                                .withAmount(100)
                                .build())))
                .withFirstName("John")
                .withLastName("Johnson")
                .build();
    }

    private void clonePrivateAccount() {
        PrivateAccount clone = privateAccount.cloneBuilder()
                .withLastName("Anderson") // Clone the account and only change the last name.
                .build();
    }
}
