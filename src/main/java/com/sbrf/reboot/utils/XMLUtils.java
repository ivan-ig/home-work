package com.sbrf.reboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sbrf.reboot.dto.Request;
import com.sbrf.reboot.dto.Response;

public class XMLUtils {

    private static final XmlMapper MAPPER = new XmlMapper();

    public static String toXML(Request request) throws JsonProcessingException {
        return MAPPER.writeValueAsString(request);
    }

    public static String toXML(Response response) throws JsonProcessingException {
        return MAPPER.writeValueAsString(response);
    }

    public static Request XMLtoRequest(String stringXML) throws JsonProcessingException {
        return MAPPER.readValue(stringXML, Request.class);
    }

    public static Response XMLtoResponse(String stringXML) throws JsonProcessingException {
        return MAPPER.readValue(stringXML, Response.class);
    }
}