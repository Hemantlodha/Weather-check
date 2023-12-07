package com.amazon.ask.helloworld.handlers;

import com.amazon.ask.dispatcher.exception.ExceptionHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import java.util.Optional;

public class MyExceptionHandler implements ExceptionHandler {

    @Override
    public boolean canHandle(HandlerInput input, Throwable throwable) {
        
        return true;
    }

    @Override
    public Optional<Response> handle(HandlerInput input, Throwable throwable) {
     
        throwable.printStackTrace();

       
        String errorMessage = "Sorry, something went wrong. Please try again later.";

        return input.getResponseBuilder()
                .withSpeech(errorMessage)
                .withShouldEndSession(true) 
                .build();
    }
}
