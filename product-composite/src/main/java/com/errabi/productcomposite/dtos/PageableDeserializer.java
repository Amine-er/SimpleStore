package com.errabi.productcomposite.dtos;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public class PageableDeserializer extends JsonDeserializer<Pageable> {

    @Override
    public Pageable deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode pageNode = node.get("page");
        JsonNode sizeNode = node.get("size");

        int page = (pageNode != null) ? pageNode.asInt() : 0;
        int size = (sizeNode != null) ? sizeNode.asInt() : 10;

        return PageRequest.of(page, size);
    }
}
