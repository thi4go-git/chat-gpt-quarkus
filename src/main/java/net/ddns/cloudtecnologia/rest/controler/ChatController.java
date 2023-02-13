package net.ddns.cloudtecnologia.rest.controler;


import net.ddns.cloudtecnologia.rest.dto.ChoicesDTO;
import net.ddns.cloudtecnologia.rest.dto.ResponseDTO;
import net.ddns.cloudtecnologia.service.impl.ChatServiceImpl;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Path("/chat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChatController {

    @Inject
    private ChatServiceImpl chatService;


    @GET
    public Response responderQuestion(@QueryParam("texto") String texto) throws UnsupportedEncodingException {
        byte[] ptext = texto.getBytes(StandardCharsets.UTF_8);
        String value = new String(ptext, StandardCharsets.ISO_8859_1);
        ResponseDTO dto = chatService.obterResposta(value);
        StringBuilder respostaMsg = new StringBuilder();
        for (ChoicesDTO c : dto.getChoices()) {
            respostaMsg.append(c.getText());
        }
        return Response.ok(respostaMsg.toString().trim()).build();
    }


}
