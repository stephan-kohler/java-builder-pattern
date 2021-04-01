package nl.avisi.demo.builder.domain;

public class PrivateAccountTestBuilder {

    public static PrivateAccount.Builder builder() {
        return PrivateAccount.builder()
                .withAccountBuilder(builder -> AccountTestBuilder.builder(builder))
                .withFirstName("John")
                .withLastName("Johnson");
    }

}
