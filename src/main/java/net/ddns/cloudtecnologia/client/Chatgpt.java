package net.ddns.cloudtecnologia.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.internal.logging.WarnThenDebugLogger;
import net.ddns.cloudtecnologia.rest.dto.ResponseDTO;

import javax.enterprise.context.ApplicationScoped;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


@ApplicationScoped
public class Chatgpt {


    public static final String URL = "https://api.openai.com/v1/completions";
    private String token;

    public ResponseDTO responderTexto(String texto) {

        try {
            // obterToken();

            URL loginUrl = new URL(URL);
            HttpURLConnection conexao = (HttpURLConnection) loginUrl.openConnection();
            //
            conexao.setRequestMethod("POST");
            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Authorization", "Bearer ");
            // Send post request
            String jsonInputString = "{\"model\": \"text-davinci-003\","
                    + "\"prompt\": \"" + texto + "\","
                    + "\"temperature\": 0,\"max_tokens\": 2000}";
            //
            conexao.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(conexao.getOutputStream())) {
                wr.writeBytes(jsonInputString);
                wr.flush();
            }
            StringBuilder response;
            try (
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conexao.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                try {
                    var mapper = new ObjectMapper();
                    ResponseDTO responseDTO =
                            mapper.readValue(response.toString(), ResponseDTO.class);

                    return responseDTO;
                } catch (Exception e) {
                    System.out.println("Erro ao Gerar responseDTO (Jackzonized) " + e.getMessage());
                }
            }
        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseDTO();
    }


    public void obterToken() {
        try {
            File txt = new File("token.txt");
            FileInputStream stream = new FileInputStream(txt);
            InputStreamReader streamReader = new InputStreamReader(stream, "UTF-8");
            BufferedReader buffer = new BufferedReader(streamReader);
            String linha;
            StringBuilder response = new StringBuilder();
            while ((linha = buffer.readLine()) != null) {
                response.append(linha.trim());
            }
            token = response.toString().trim();
        } catch (IOException e) {
            System.out.println("ERRO " + e.getMessage());
        }
    }

}
