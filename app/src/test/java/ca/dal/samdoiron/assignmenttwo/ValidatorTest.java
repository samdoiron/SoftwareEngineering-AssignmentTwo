/**
 * Created by samdoiron on 2018-02-10.
 */
package ca.dal.samdoiron.assignmenttwo;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {
    @Test
    public void emptyPasswordsAreInvalid() {
        assertFalse(isValid(""));
    }

    @Test
    public void shortPasswordsAreInvalid() {
        assertInvalid(
                otherwiseValidPasswordWithLength(Validator.MIN_LENGTH - 1)
        );
    }

    @Test
    public void longPasswordsAreValid() {
        assertValid(
                otherwiseValidPasswordWithLength(Validator.MIN_LENGTH)
        );
    }

    @Test
    public void passwordCantBeLiterallyPassword() {
        assertInvalid("password");
        assertInvalid("PASSWORD");
        assertInvalid("PaSsWoRd");
    }

    @Test
    public void passwordMustContainANumber() {
        assertInvalid("A really long password with no number.");
        assertValid("Another password with 1 number in it.");
    }

    @Test
    public void passwordMustContainMixedCasing() {
        assertInvalid(validPassword().toLowerCase());
        assertInvalid(validPassword().toUpperCase());
    }

    @Test
    public void numbersAtTheEndDoNotCount() {
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
