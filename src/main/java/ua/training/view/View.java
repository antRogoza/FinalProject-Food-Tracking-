package ua.training.view;

import java.util.Locale;
import java.util.ResourceBundle;

public class View {
    public static final String MESSAGES_BUNDLE_NAME = "messages";
    public static ResourceBundle bundle =
            ResourceBundle.getBundle(
                    MESSAGES_BUNDLE_NAME,
                    new Locale("ua"));                // Ukrainian
                    //new Locale("en"));        // English

    public static String THEME_OF_THE_PROJECT = "theme.of.the.project";
    public static String ENTER_YOUR_DATA = "enter.your.data";
}
