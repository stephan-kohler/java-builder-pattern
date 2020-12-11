package nl.avisi.demo;

import nl.avisi.demo.builder.domain.PrivateAccount;

public class Usage {

    private void usePrivateAccount() {
        PrivateAccount privateAccount = PrivateAccount.builder()
                .withAccountNumber("A001")
                .withFirstName("John")
                .withLastName("Johnson")
                .build();
    }
}
