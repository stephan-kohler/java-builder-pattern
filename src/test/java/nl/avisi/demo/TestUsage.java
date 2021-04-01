package nl.avisi.demo;

import nl.avisi.demo.builder.domain.PrivateAccount;
import nl.avisi.demo.builder.domain.PrivateAccountTestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestUsage {

    @Test
    public void testPrivateAccount() {
        PrivateAccount privateAccount = PrivateAccountTestBuilder.builder()
                .withAccountBuilder(builder -> builder
                        .withAccountNumber("A002")) // Specific test value
                .build();

        Assertions.assertEquals("A002", privateAccount.getAccountNumber());
    }
}
