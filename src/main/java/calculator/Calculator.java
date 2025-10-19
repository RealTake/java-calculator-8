package calculator;

import java.math.BigDecimal;
import java.util.List;

public class Calculator {

    /**
     * 주어진 수를 전부 합한다.
     *
     * @param numbers 합할 값.
     * @return 모두 합한 최종값.
     */
    public static BigDecimal sumAll(List<BigDecimal> numbers) {
        return numbers.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
