import java.util.Collections;
import java.util.stream.Stream;

public class Validator {
    private final String password;

    final static int MIN_LENGTH = 8;

    public Validator(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return !password.equalsIgnoreCase("password")
                && password.length() >= MIN_LENGTH
                
                // Markers note: this is two rules (numbers, non-terminal numbers)
                && passwordContainsNonterminalNumbers()
                && passwordHasMixedCasing();
    }

    private boolean passwordContainsNonterminalNumbers() {
        long numberCount = password.chars().filter(Character::isDigit).count();
        char lastChar = password.charAt(password.length() - 1);
        return numberCount > 0 && (!Character.isDigit(lastChar) || numberCount > 1);
    }

    private boolean passwordHasMixedCasing() {
        return password.chars().anyMatch(Character::isUpperCase)
                && password.chars().anyMatch(Character::isLowerCase);

    }
}
