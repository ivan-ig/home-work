package com.sbrf.reboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbrf.reboot.dto.Request;
import com.sbrf.reboot.dto.Response;

public class JSONUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJSON(Request request) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(request);
    }

    public static String toJSON(Response response) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(response);
    }

    public static Request JSONtoRequest(String stringJSON) throws JsonProcessingException {
         return OBJECT_MAPPER.readValue(stringJSON, Request.class);
    }

    public static Response JSONtoResponse(String stringJSON) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(stringJSON, Response.class);
    }
}