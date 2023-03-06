package com.example.dialogalisa.util;

import com.example.dialogalisa.dto.yandexAlice.request.YAEntity;
import com.example.dialogalisa.dto.yandexAlice.request.YASkillRequest;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class YARequestUtil {

    public String definedEntityFIO(YASkillRequest skillRequest) {
        if (skillRequest == null || skillRequest.getNlu() == null || skillRequest.getNlu().getEntities() == null
                || skillRequest.getNlu().getEntities().isEmpty()) {
            return null;
        }
        Optional<YAEntity> entity = skillRequest.getNlu().getEntities().stream().filter(t -> t != null && "YANDEX.FIO".equals(t.getType())
                        && t.getValue().isObject())
                .findFirst();
        if (entity.isPresent()) {
            JsonNode nodeLastName = entity.get().getValue().get("last_name");
            JsonNode nodeFirstName = entity.get().getValue().get("first_name");
            JsonNode nodePatronymicName = entity.get().getValue().get("patronymic_name");
            List<JsonNode> nameParts = Arrays.asList(nodeFirstName, nodePatronymicName, nodeLastName);
            return nameParts.stream().filter(
                    t -> t != null && !t.isNull() && t.isValueNode() && t.isTextual() && !t.asText().trim().isEmpty()
            ).map(JsonNode::asText).map(t -> t.substring(0, 1).toUpperCase() + t.substring(1)).collect(Collectors.joining(" "));
        }

        return null;
    }
}
