package game;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LanguageConfiguration {
    protected static LanguageConfiguration uniqueInstance;
    protected Properties configuration;
    protected static final String[] supportedLanguages = {"english", "spanish", "japanese"};
    protected int currLanguage;
    protected Properties language;

    protected LanguageConfiguration() {
        configuration = new Properties();
        language = new Properties();
        currLanguage = 0;
        try {
            InputStream input = new FileInputStream("configuration.properties");
            configuration.load(input);
            input.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        setLanguage(supportedLanguages[currLanguage]);
    }


    public static LanguageConfiguration instance() {
        if (uniqueInstance == null) {
            uniqueInstance =  new LanguageConfiguration();

        }
        return uniqueInstance;
    }

    public void setLanguage(String s) {
        try {
            FileInputStream input = new FileInputStream(configuration.getProperty(s));
            language.load(input);
            input.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String s) {
        return language.getProperty(s);
    }

    public void nextLanguage() {
        currLanguage = (currLanguage + 1) % supportedLanguages.length;
        setLanguage(supportedLanguages[currLanguage]);
        SingletonGraphicEngine.instance().reload();
    }
}
