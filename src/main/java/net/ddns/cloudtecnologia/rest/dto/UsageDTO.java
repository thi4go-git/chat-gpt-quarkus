package net.ddns.cloudtecnologia.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsageDTO {
    private Integer prompt_tokens;
    private Integer completion_tokens;
    private Integer total_tokens;
}