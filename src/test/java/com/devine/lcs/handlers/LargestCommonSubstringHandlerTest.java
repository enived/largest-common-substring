package com.devine.lcs.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LargestCommonSubstringHandlerTest {

    private LargestCommonSubstringHandler largestCommonSubstringHandler;

    @BeforeEach
    public void setup() {
        largestCommonSubstringHandler = new LargestCommonSubstringHandler();
    }

    @Test
    public void getLargestCommonSubstring_NoMatches() {
        assertTrue(largestCommonSubstringHandler.getLargestCommonSubstring(Arrays.asList("a", "b", "c")).isEmpty());
    }
    @Test
    public void getLargestCommonSubstring_OneString() {
        assertEquals("abc", largestCommonSubstringHandler.getLargestCommonSubstring(Arrays.asList("abc")).get(0));
    }

    @Test
    public void getLargestCommonSubstring_InvalidInput() {
        assertTrue(largestCommonSubstringHandler.getLargestCommonSubstring(Arrays.asList("", "bb", "bbc")).isEmpty());
    }

    @Test
    public void getLargestCommonSubstring_MoreThanOneAnswer() {
        List<String> multiple = largestCommonSubstringHandler.getLargestCommonSubstring(Arrays.asList("bbdcc", "bbzcc", "bbacc"));
        assertEquals(2, multiple.size());
        assertTrue(multiple.contains("bb"));
        assertTrue(multiple.contains("cc"));
    }

    @Test
    public void getLargestCommonSubstring_HappyPath() {
        assertEquals("aa", largestCommonSubstringHandler.getLargestCommonSubstring(Arrays.asList("aa", "aab", "abcaa")).get(0));
        assertEquals("cast", largestCommonSubstringHandler.getLargestCommonSubstring(Arrays.asList("comcast", "comcastic", "broadcaster")).get(0));
        assertEquals("road", largestCommonSubstringHandler.getLargestCommonSubstring(Arrays.asList("broadcaster", "broad", "tattedroad", "roadster")).get(0));
        assertEquals("oad", largestCommonSubstringHandler.getLargestCommonSubstring(Arrays.asList("broadcaster", "broad", "road", "oadster", "baitxbaitxbaitxbaitxbaitxoad")).get(0));
    }

}
