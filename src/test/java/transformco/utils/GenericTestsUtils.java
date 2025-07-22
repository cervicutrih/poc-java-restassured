package transformco.utils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class GenericTestsUtils {

    private static final OffsetDateTime NOW = OffsetDateTime.now();

    public static final String DATE_NOW =
            NOW.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
}
