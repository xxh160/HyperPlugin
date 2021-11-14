package cn.edu.nju.hyperplugin.util;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

public class SmartContract {

    @SneakyThrows
    public static Vector<Vector<String>> readApi(String filepath) {
        File file = new File(filepath);
        Vector<Vector<String>> result = new Vector<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tmp;
        while ((tmp = reader.readLine()) != null) {
            Vector<String> row = new Vector<>();
            if (tmp.toLowerCase().contains("async")) {
                String func = tmp.substring(tmp.indexOf("async") + 5 + 1, tmp.indexOf('('));
                row.add(func);
                String args = tmp.substring(tmp.indexOf('(') + 1, tmp.indexOf(')'));
                row.add(args);
                result.add(row);
            }

        }
        return result;
    }
}
