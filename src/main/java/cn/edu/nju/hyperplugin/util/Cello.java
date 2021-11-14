package cn.edu.nju.hyperplugin.util;

public class Cello {

    public static void open(String customPath) {
        if (customPath == null || customPath.length() == 0) Browser.openUrl(Config.celloUrl);
        Browser.openUrl(customPath);
    }

}
