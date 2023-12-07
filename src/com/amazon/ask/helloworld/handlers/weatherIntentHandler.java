package com.amazon.ask.helloworld.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import java.util.Iterator;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.helloworld.handlers.PassKeyIntentHandler.flag1;
import static com.amazon.ask.helloworld.HelloWorldStreamHandler.count;
import static com.amazon.ask.helloworld.HelloWorldStreamHandler.universalCity;;

public class weatherIntentHandler  implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("weather"));
    }
    
    @Override
    public Optional<Response> handle(HandlerInput input) {
        boolean isSessionend=false;
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        String city="";
        // Get the color slot from user input.
        if (intent.getSlots() != null) {
            // Get slot values
            Map<String, Slot> slots = intent.getSlots();
            Slot mySlot = slots.get("city"); // Replace with the actual slot name

            if (mySlot != null && mySlot.getValue() != null) {
                city = mySlot.getValue();
                // Process the slot value as needed
            }
        }
        universalCity=city;
        String speechText="something wrong", repromptText="";
        // Check for favorite color and create output to user.
        if(flag1){
        if(city.length()!=0) {
            String apiKey = "367ca3b077msha9c3423a457823cp11f821jsn91f243408902";

        try {
            String url = "https://weather-by-api-ninjas.p.rapidapi.com/v1/weather?city=" + city;
            URL apiUrl = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-RapidAPI-Key", apiKey);
            connection.setRequestProperty("X-RapidAPI-Host", "weather-by-api-ninjas.p.rapidapi.com");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                Map<String,String> weatheMap=parseJsonResponse(response.toString());
                String rise=(int)(Math.floor(((Integer.parseInt(weatheMap.get("sunrise"))+330*60)%(60*60*24))/3600))+" "+(int)Math.floor(((Integer.parseInt(weatheMap.get("sunrise"))+330*60)%(60*60))/60)+" am";
                String set=((int)Math.floor(((Integer.parseInt(weatheMap.get("sunset"))+330*60)%(60*60*24))/3600)%12)+" "+(int)Math.floor(((Integer.parseInt(weatheMap.get("sunset"))+330*60)%(60*60))/60)+" pm";
                // Process the response as needed
                speechText="The Temperature of "+ city+ " is " +weatheMap.get("temp") +", the Temperature feels like "+weatheMap.get("feels_like")+", the wind speed is "+weatheMap.get("wind_speed")+", the humidity is "+weatheMap.get("humidity")+" , the sun rises at "+rise+" and the sunset @ "+set+" \n"+" there was so cold outside please take you sweater with you ";
                count++;
                // speechText=response.toString();
            } else {
                speechText="HTTP Request Failed with response code: " + responseCode;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



            repromptText =
                    "You can ask me my choices";

        } else {
            // Render an error since user input is out of list of color defined in interaction model.
            speechText = "Please provide a valid city name. " +
                    "Please try again.";
            repromptText =
                    "I'm not sure what you trying to do";
        }
    }
    else{
        speechText="You are not authorized";

    }
        // if(count>=2){
        //     isSessionend=true;
        //     speechText+="Now your session is ended";
        // }
        return input.getResponseBuilder()
                .withSimpleCard("Weather App", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .withShouldEndSession(isSessionend)
                .build();
    }
    private static Map<String, String> parseJsonResponse(String jsonResponse) {
        Map<String, String> apiResponseMap = new HashMap<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            // Iterate over the fields in the JSON response
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                apiResponseMap.put(entry.getKey(), entry.getValue().asText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return apiResponseMap;
    }

}