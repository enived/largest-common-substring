package com.devine.lcs.controllers;

import com.devine.lcs.api.JsonSetRequest;
import com.devine.lcs.api.JsonSetResponse;
import com.devine.lcs.api.exceptions.DuplicateEntriesInSetException;
import com.devine.lcs.api.exceptions.InvalidFormDataException;
import com.devine.lcs.api.exceptions.SetOfStringsEmptyException;
import com.devine.lcs.handlers.LargestCommonSubstringHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LargestCommonSubstringController {

    private final LargestCommonSubstringHandler largestCommonSubstringHandler;
    private final ObjectMapper mapper;

    @PostMapping(path = "/lcs",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_FORM_URLENCODED_VALUE
            },
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public JsonSetResponse getLargestCommonSubstring(@RequestBody String jsonSet, HttpServletRequest request) {
        // Handles the post via form from '/' endpoint
        String formData = request.getParameter("jsonSet");
        // if the parameter from the form post isn't there use the requestbody
        if (formData == null || "".equals(formData)) {
            formData = jsonSet;
        }

        JsonSetRequest setOfStrings;
        try {
            setOfStrings = mapper.readValue(formData, JsonSetRequest.class);
        } catch (JsonProcessingException e) {
            throw new InvalidFormDataException();
        }


        // Must be provided and not  empty
        if (setOfStrings.getSetOfStrings() == null || setOfStrings.getSetOfStrings().isEmpty()) {
            throw new SetOfStringsEmptyException();
        }

        final List<String> stringList = new ArrayList<>();
        // Must not have any duplicates
        setOfStrings.getSetOfStrings().forEach(a -> {
            String nextString = a.get("value");
            if (!stringList.contains(nextString)) {
                stringList.add(nextString);
            } else {
                throw new DuplicateEntriesInSetException();
            }
        });
        return responseAsJSON(largestCommonSubstringHandler.getLargestCommonSubstring(stringList));
    }

    private JsonSetResponse responseAsJSON(List<String> lcsList) {
        final JsonSetResponse response = new JsonSetResponse();
        final List<Map<String, String>> lcs = new ArrayList<>(lcsList.size());
        lcsList.forEach( str ->  {
            Map<String, String> kv = new HashMap<>(1);
            kv.put("value", str);
            lcs.add(kv);
        });
        response.setLcs(lcs);
        return response;
    }
}
