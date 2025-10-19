package calculator;

import static camp.nextstep.edu.missionutils.Console.readLine;

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
        // 데이터 입력 받음
        final String input = readLine();
    }
}
