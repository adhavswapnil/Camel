package org.camel.transform;

public class TransformationBean {
    public String makeUpperCase(String body) {
        String transformedBody = body.toUpperCase();
        return transformedBody;
    }
}
