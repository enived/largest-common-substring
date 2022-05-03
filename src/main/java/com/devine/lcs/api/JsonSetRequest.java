package com.devine.lcs.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class JsonSetRequest {
    // Did this for the sake of speed. Fastest way to parse the list without using a json object mapper and pulling in the simple json lib
    private List<Map<String, String>> setOfStrings;
}
