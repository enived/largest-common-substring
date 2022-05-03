package com.devine.lcs.handlers;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class LargestCommonSubstringHandler {

    public List<String> getLargestCommonSubstring(List<String> stringList) {
        int stringListSize = stringList.size();
        // edge case: empty list
        if (stringListSize == 0) {
            return new ArrayList<>();
        }

        final String firstString = stringList.get(0);
        // edge case: only 1 string
        if (stringListSize == 1) {
            return Arrays.asList(firstString);
        }

        String largestCommonSubstring = "";
        final List<String> allCommonSubstrings = new ArrayList<>();

        // Iterate through the offset starting positions
        for (int startIndex = 0; startIndex < firstString.length(); startIndex++) {
            // iterate through the offset ending positions
            for (int endIndex = startIndex + 1; endIndex <= firstString.length(); endIndex++) {
                // get the substring to compare against all other strings
                final String substring = firstString.substring(startIndex, endIndex);
                // Tally how many times the substring is found
                int hitCounter;
                for (hitCounter = 1; hitCounter < stringListSize; hitCounter++)
                    if (!stringList.get(hitCounter).contains(substring))
                        // substring is not common so stop comparing
                        break;
                // if all strings contain the substring then hit counter will equal the stringList size
                if (hitCounter == stringListSize && largestCommonSubstring.length() <= substring.length()) {
                    largestCommonSubstring = substring;
                    if (!allCommonSubstrings.contains(substring)) {
                        allCommonSubstrings.add(substring);
                    }
                }

            }
        }

        return filterLargestCommonSubstrings(allCommonSubstrings);
    }

    // filter out smaller common substrings
    private List<String> filterLargestCommonSubstrings(List<String> allCommonSubstrings) {
        final Optional<String> largestSubstring = allCommonSubstrings.stream().max(Comparator.comparingInt(String::length));
        if (largestSubstring.isPresent()) {
            int largestLen = largestSubstring.get().length();
            return allCommonSubstrings.stream()
                    .filter(s -> s.length() >= largestLen)
                    .sorted() // alphabetically sorted per requirements
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

}
