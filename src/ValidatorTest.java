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

    @Test
    void passwordMustContainANumber() {
        assertInvalid("A really long password with no number.");
        assertValid("Another password with 1 number in it.");
    }

    @Test
    void passwordMustContainMixedCasing() {
        assertInvalid(validPassword().toLowerCase());
        assertInvalid(validPassword().toUpperCase());
    }

    @Test
    void numbersAtTheEndDoNotCount() {
        assertInvalid("A really long password with the number 1");
        assertValid(validPassword() + '1');
    }

    private String validPassword() {
        return otherwiseValidPasswordWithLength(Validator.MIN_LENGTH);
    }

    private String otherwiseValidPasswordWithLength(int length) {
        StringBuilder builder = new StringBuilder();
        builder.append('1'); // Must contain digit
        builder.append('A'); // Must contain mixed casing

        for (int i = 0; i < length - 2; i++) {
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