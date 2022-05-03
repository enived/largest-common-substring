package com.devine.lcs.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class JsonSetResponse {
    private List<Map<String, String>> lcs;
}
