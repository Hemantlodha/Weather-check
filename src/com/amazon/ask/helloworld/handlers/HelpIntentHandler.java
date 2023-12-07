/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.amazon.ask.helloworld.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class HelpIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "To use the weather app, follow these steps: 1. Open the app by saying 'Open weather check.' 2. Introduce yourself by saying 'I am [first name] or [first name].' For example, 'I am John. or John' 3. Ask about the weather in a specific city by saying 'Weather of [city].' For instance, 'Weather of New York'. or you can simply check a particular feature by [Feature] of [city] 4. To terminate session say cancel,stop 5. If you ever need assistance, simply say 'help,' and I'll guide you through the available commands." ;
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Help!", speechText)
                .withReprompt(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
