package selenium.config;

import java.io.File;

public class FilePath {
    public static String driverPath = System.getProperty("user.dir") + File.separator + "driver" + File.separator;
    public static String appPropPath = System.getProperty("user.dir") + File.separator + "src" + File.separator +
            "main" + File.separator + "resources" + File.separator + "application.properties";
}
