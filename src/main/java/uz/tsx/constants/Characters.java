package uz.tsx.constants;

public interface Characters {

    String USER_ADDRESS_CHECK = "||";

    String FOLDER_BOUNDARY = "/";

    String PHONE_NUMBER_PREFIX = "+998";

        /*
        REGEX CHECK PHONE NUMBER TEM
        "+(998) 90 138 99 18",
            "+998 91 138 99 18",
            "+998-90-138-99-18",
            "901389918",
            "+901389918",
            "+998 (90) 1389918",
            "90-138-99-18",
            "90 138 99 18",
            "9 0 1 3 8 9 9 1 8",
            "+998901389918",
            "+998(90)138-99-18",
            "(90)138-99-18",
            "(90) 138 99 18",*/
    String REGEX_PHONE_NUMBER_PATTERN = "\\+?(998)?[-\\s]?\\(?(\\d{2})\\)?[-\\s]?((\\d{3})[-\\s]?(\\d{2})[-\\s]?(\\d{2}))";

    String REGEX_EMAIL_PATTERN = "[ExecuteBot-Za-z0-9._%+\\-]+@[ExecuteBot-Za-z.\\-]{2,}\\.[ExecuteBot-Za-z]{2,}";

}
