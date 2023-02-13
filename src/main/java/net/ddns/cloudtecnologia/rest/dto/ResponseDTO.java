package net.ddns.cloudtecnologia.rest.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private String id;
    private String object;
    private Integer created;
    private String model;
    private ChoicesDTO[] choices;
    private UsageDTO usage;
}