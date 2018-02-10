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
                && password.length() >= MIN_LENGTH;
    }
}
