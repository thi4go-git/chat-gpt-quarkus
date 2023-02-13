package net.ddns.cloudtecnologia.client;


import javax.enterprise.context.ApplicationScoped;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


@ApplicationScoped
public class Chatgpt {


    public static final String URL = "https://api.openai.com/v1/completions";
    private String token;

    public String responderTexto(String texto) {

        try {
            obterToken();
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
                byte[] ptext = response.toString().getBytes(StandardCharsets.ISO_8859_1);
                String formatado = new String(ptext, StandardCharsets.UTF_8);
                return formatado;
            }
        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Sem resposta para essa questão!";
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
