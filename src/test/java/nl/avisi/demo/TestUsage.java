package nl.avisi.demo;

import nl.avisi.demo.builder.domain.PrivateAccount;
import nl.avisi.demo.builder.domain.PrivateAccountTestBuilder;
import org.junit.jupiter.api.Test;

public class TestUsage {

    @Test
    private void testPrivateAccount() {
        PrivateAccount privateAccount = PrivateAccountTestBuilder.builder()
                .withAccountNumber("A001") // Specific test value
                .build();
    }
}
