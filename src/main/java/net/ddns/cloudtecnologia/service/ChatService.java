package net.ddns.cloudtecnologia.service;

import net.ddns.cloudtecnologia.rest.dto.ResponseDTO;

public interface ChatService {
    ResponseDTO obterResposta(String texto);
}
