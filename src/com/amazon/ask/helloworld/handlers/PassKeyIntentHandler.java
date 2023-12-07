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
import static com.amazon.ask.helloworld.handlers.PersonCheckIntentHandler.flag;
import static com.amazon.ask.helloworld.HelloWorldStreamHandler.count;
import static com.amazon.ask.helloworld.HelloWorldStreamHandler.universalCity;;

public class PassKeyIntentHandler  implements RequestHandler {
    public static boolean flag1=false;
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("passkey"));
    }
    
    @Override
    public Optional<Response> handle(HandlerInput input) {
        boolean isSessionend=false;
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        String speechText="You are not authorized to use our services";
        String repromptText="You can contact the author for issues";
        // Get the color slot from user input.
        if (intent.getSlots() != null) {
            // Get slot values
            Map<String, Slot> slots = intent.getSlots();
            Slot mySlot = slots.get("key"); // Replace with the actual slot name
            if (mySlot != null && mySlot.getValue() != null) {
                   String check="1234";
                   if(check.equals(mySlot.getValue())){
                    if(flag==true){
                        flag1=true;
                        speechText="you can access the weather reports of city in way you want";
                        repromptText="give some input please";

                    }
                   }

                // Process the slot value as needed
            }
        //     speechText+="Now your session is ended";
        }
        return input.getResponseBuilder()
                .withSimpleCard("Weather App", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .withShouldEndSession(isSessionend)
                .build();
    }
}