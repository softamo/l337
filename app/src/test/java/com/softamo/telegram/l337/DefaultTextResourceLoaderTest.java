package com.softamo.telegram.l337;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DefaultTextResourceLoaderTest {

    @Test
    void candidates() {
        DefaultTextResourceLoader loader = new DefaultTextResourceLoader(null);
        List<String> expected = Arrays.asList("help.md", "help.markdown", "help.html", "help.txt");
        List<String> result = loader.candidates("help").collect(Collectors.toList());
        assertEquals(expected, result);
        result = loader.candidates("/help").collect(Collectors.toList());
        assertEquals(expected, result);
    }
}