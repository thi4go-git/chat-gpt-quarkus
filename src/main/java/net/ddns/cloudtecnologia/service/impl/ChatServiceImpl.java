package net.ddns.cloudtecnologia.service.impl;

import net.ddns.cloudtecnologia.client.Chatgpt;
import net.ddns.cloudtecnologia.service.ChatService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ChatServiceImpl implements ChatService {

    @Inject
    private Chatgpt client;

    @Override
    public String obterResposta(String texto) {
        return client.responderTexto(texto);
    }

}
