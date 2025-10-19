package calculator.validator;

import java.math.BigDecimal;

public class PositiveNumberValidator implements NumberValidator {

    @Override
    public void isValid(BigDecimal number) {
        if (number.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(String.format("음수는 허용되지 않습니다: %s", number));
        }
    }
}
