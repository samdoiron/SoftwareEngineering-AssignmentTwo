import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    @Test
    void emptyPasswordsAreInvalid() {
        assertFalse(isValid(""));
    }

    @Test
    void shortPasswordsAreInvalid() {
        assertInvalid(
                otherwiseValidPasswordWithLength(Validator.MIN_LENGTH - 1)
        );
    }

    @Test
    void longPasswordsAreValid() {
        assertValid(
                otherwiseValidPasswordWithLength(Validator.MIN_LENGTH)
        );
    }

    @Test
    void passwordCantBeLiterallyPassword() {
        assertInvalid("password");
        assertInvalid("PASSWORD");
        assertInvalid("PaSsWoRd");
    }

    private String otherwiseValidPasswordWithLength(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length ; i++) {
            builder.append('a');
        }
        return builder.toString();
    }

    private void assertValid(String password) {
        assertTrue(isValid(password));
    }

    private void assertInvalid(String password) {
        assertFalse(isValid(password));
    }

    private boolean isValid(String password) {
        Validator validator = new Validator(password);
        return validator.isValid();
    }
}