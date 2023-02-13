package net.ddns.cloudtecnologia.rest.controler;


import net.ddns.cloudtecnologia.service.impl.ChatServiceImpl;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


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
        return Response.ok(chatService.obterResposta(value)).build();
    }


}
