import java.util.regex.Pattern;

public class Validator {

    boolean isValidEmail (String email) {
        return Pattern.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+", email);
    }

    boolean isValidPassword (String password) {
        return Pattern.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$", password);
    }
}
