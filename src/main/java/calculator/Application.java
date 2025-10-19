package calculator;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.math.BigDecimal;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void run() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        // 시스템으로 부터 입력 받음
        final String input = readLine();

        final InputParser parser = new InputParser();
        final List<BigDecimal> parseNumbers = parser.parse(input);

        final BigDecimal result = Calculator.sumAll(parseNumbers);
        System.out.println("결과 : " + result);
    }
}
