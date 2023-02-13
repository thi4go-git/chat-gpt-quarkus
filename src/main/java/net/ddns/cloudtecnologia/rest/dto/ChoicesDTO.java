package net.ddns.cloudtecnologia.rest.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChoicesDTO {
    private String text = "Não encontrei resposta para isso!";
    private Integer index;
    private String logprobs;
    private String finish_reason;
}