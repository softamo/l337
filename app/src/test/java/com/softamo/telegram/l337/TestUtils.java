package com.softamo.telegram.l337;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestUtils {

    public static String text(String fileName) throws IOException {
        return text(new FileReader(fileName));
    }

    public static String text(File file) throws IOException {
        return text(new FileReader(file));
    }

    public static String text(FileReader fileReader) throws IOException {
        BufferedReader br = new BufferedReader(fileReader);
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}
