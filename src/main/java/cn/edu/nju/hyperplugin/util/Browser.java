package cn.edu.nju.hyperplugin.util;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.List;

public class Browser {

    @SneakyThrows
    public static void openUrl(String url) {
        switch (getOS()) {
            case MacOS:
                openMac(url);
                break;
            case Windows:
                openWindows(url);
                break;
            default:
                openLinux(url);
        }
        System.out.println(url + " opened");
    }

    private static OS getOS() {
        String os = System.getProperty("os.name", "");
        return OS.getOS(os);
    }

    @SneakyThrows
    private static void openMac(String url) {
        Class<?> file = Class.forName("com.apple.eio.FileManager");
        Method openURL = file.getDeclaredMethod("openURL", String.class);
        openURL.invoke(null, url);
    }

    @SneakyThrows
    private static void openWindows(String url) {
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
    }

    @SneakyThrows
    private static boolean isBrowserExists(String name) {
        return Runtime.getRuntime().exec(new String[]{"which", name}).waitFor() == 0;
    }

    @SneakyThrows
    private static void openLinux(String url) {
        List<String> browsers = List.of("google", "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape");
        for (String browser : browsers) {
            if (!isBrowserExists(browser)) continue;
            Runtime.getRuntime().exec(new String[]{browser, url});
            return;
        }
    }

}
