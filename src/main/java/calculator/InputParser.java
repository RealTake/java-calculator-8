package calculator;

import calculator.validator.NumberValidator;
import calculator.validator.PositiveNumberValidator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputParser {

    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";
    private static final int CUSTOM_DELIMITER_PREFIX_OFFSET = CUSTOM_DELIMITER_PREFIX.length();
    private static final int CUSTOM_DELIMITER_SUFFIX_OFFSET = CUSTOM_DELIMITER_SUFFIX.length();

    private static final String[] DEFAULT_DELIMITERS = {",", ":"};
    private static final String DEFAULT_DELIMITERS_CACHE = Arrays.stream(DEFAULT_DELIMITERS)
            .map(Pattern::quote)
            .collect(Collectors.joining("|"));


    private final NumberValidator VALIDATOR;

    public InputParser() {
        this(new PositiveNumberValidator());
    }

    public InputParser(NumberValidator validator) {
        this.VALIDATOR = validator;
    }

    public List<BigDecimal> parse(String input) {
        if (input == null || input.isBlank()) {
            return new ArrayList<>();
        }

        String numbersStr = input.replaceAll("\\s", "");
        String delimiter = DEFAULT_DELIMITERS_CACHE;

        if (hasCustomDelimiter(input)) {
            delimiter += "|" + getCustomDelimiter(input);
            numbersStr = getNumbersString(numbersStr);
        }

        return parseToBigDecimal(numbersStr, delimiter);
    }

    private String getNumbersString(String input) {
        return input.substring(input.indexOf(CUSTOM_DELIMITER_SUFFIX) + CUSTOM_DELIMITER_SUFFIX_OFFSET);
    }

    private List<BigDecimal> parseToBigDecimal(String numbersStr, String delimiter) {
        return Arrays.stream(numbersStr.split(delimiter))
                .filter(s -> !s.isBlank())
                .map(BigDecimal::new)
                .map(this::validate)
                .toList();
    }

    private BigDecimal validate(BigDecimal number) {
        VALIDATOR.isValid(number);
        return number;
    }

    private boolean hasCustomDelimiter(String numbersStr) {
        return numbersStr.startsWith(CUSTOM_DELIMITER_PREFIX);
    }

    private String getCustomDelimiter(String input) {
        final int delimiterSuffixIndex = input.indexOf(CUSTOM_DELIMITER_SUFFIX);

        if (delimiterSuffixIndex != 3) {
            throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다.");
        }

        return escape(input.substring(CUSTOM_DELIMITER_PREFIX_OFFSET, delimiterSuffixIndex).trim());
    }

    public static String escape(String delimiter) {
        return Pattern.quote(delimiter);
    }
}