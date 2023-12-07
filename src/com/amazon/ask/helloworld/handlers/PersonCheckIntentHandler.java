package com.amazon.ask.helloworld.handlers;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import static com.amazon.ask.request.Predicates.intentName;

public class PersonCheckIntentHandler implements RequestHandler {
    static boolean flag=false;
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("PersonChecker"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        // Get the color slot from user input.
        Slot AllowedPersonSlot = slots.get("Person");
        String speechText, repromptText;

        // Check for favorite color and create output to user.
        if(AllowedPersonSlot != null
            && AllowedPersonSlot.getResolutions() != null
            && AllowedPersonSlot.getResolutions().toString().contains("ER_SUCCESS_MATCH")) {
            // Store the user's favorite color in the Session and create response.
            String CurrentPerson = AllowedPersonSlot.getValue();
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap("PERSON",CurrentPerson));

            speechText =
                    String.format("Hello %s. Please let me know the passKey which is assigned to you", CurrentPerson);
            repromptText =
                    "You can ask me full details of Weather by saying, what is the weather of city or just temperature by saying, weather of city";
            flag=true;

            // Send the response back to Alexa
            // return responseForElse;

            // Now, you can call WeatherJavaFile or any other logic you need
        } else {
            // Render an error since user input is out of list of color defined in interaction model.
            speechText = "Sorry you are not allowed to use our services " +
                    "Please try again.";
            repromptText =
                    "Please tell me your name to start services";
        }

        return input.getResponseBuilder()
                .withSimpleCard("Authorization", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .withShouldEndSession(false)
                .build();
    }

}
