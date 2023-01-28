package ru.liner.vksign;

import ru.liner.vksign.request.PushRequest;

public class Main {
    public static void main(String[] args) {
        PushRequest defaultRequest = new PushRequest();
        String generatedURL = defaultRequest.buildRequestUrl();
        String generatedSignature = defaultRequest.getSignature();
        System.out.printf("\nGenerated URL:%s\nGenerated Signature:%s%n",
                generatedURL,
                generatedSignature
        );
    }
}
