package cn.edu.nju.hyperplugin.util;

import lombok.Getter;

@Getter
public enum OS {
    MacOS("Mac OS"), Linux("Linux"), Windows("Windows");

    private final String name;

    OS(String name) {
        this.name = name;
    }

    public static OS getOS(String name) {
        if (name.startsWith("Mac OS")) return MacOS;
        if (name.startsWith("Windows")) return Windows;
        return Linux;
    }

}
