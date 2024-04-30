package uz.tsx.SMS.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import uz.tsx.SMS.SMSConstant;
import uz.tsx.SMS.model.EskizLoginDto;
import uz.tsx.SMS.model.EskizToken;
import uz.tsx.SMS.model.SMSModel;

@Service
public class SMSService {

    private void login(){

        EskizLoginDto loginModel = new EskizLoginDto();

        loginModel.setEmail(SMSConstant.ESKIZ_EMAIL);
        loginModel.setPassword(SMSConstant.ESKIZ_PASSWORD);

        WebClient.Builder client = WebClient.builder();

        String response=null;

        try {
            response = client
                    .build()
                    .post().uri(SMSConstant.ESKIZ_LOGIN_URL)
                    .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(loginModel), EskizLoginDto.class)
                    .retrieve()
                    .bodyToMono(String.class).block();
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e);
        }
        //update eskiz token
        EskizToken.setToken(getAccessToken(response));
        System.out.println(response);
    }

    public String sendSMS(String phoneNumber, String code) {
        WebClient.Builder client = WebClient.builder();
        String text = String.format("tez-sotish.uz uchun tastiqlash kodi:%s", code);
        SMSModel smsModel = new SMSModel(phoneNumber, text);

        String headerToken = EskizToken.getToken();
        String res=null;

        try {
            res = client.filter(addBearerToken(headerToken))
                    .build()
                    .post().uri(SMSConstant.ESKIZ_SEND_SMS_URL)
                    .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(smsModel), SMSModel.class)
                    .retrieve()
                    .bodyToMono(String.class).block();
        }catch (Exception e){
            if((((WebClientResponseException.Unauthorized) e).getStatusCode()).value()==401){
                try {
                    login();
                }catch (Exception t){
                    System.out.println("send email message tsx admin ");
                }
                sendSMS(phoneNumber, code);
            }
            System.out.println(e.getMessage());
            System.out.println(e);
        }
        return res;
    }

    private String getAccessToken(String response){

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        String accessToken = null;

        try {
            jsonNode = objectMapper.readTree(response);
            accessToken = jsonNode.get("access_token").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


       return accessToken;
    }

    private ExchangeFilterFunction addBearerToken(String token) {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            ClientRequest authorizedRequest = ClientRequest.from(clientRequest)
                    .headers(headers -> headers.setBearerAuth(token))
                    .build();
            return Mono.just(authorizedRequest);
        });
    }


}
