package org.errai.samples.errorhandling.server;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.MessageCallback;
import org.jboss.errai.bus.client.api.ResourceProvider;
import org.jboss.errai.bus.client.api.base.MessageBuilder;
import org.jboss.errai.bus.client.api.base.TimeUnit;
import org.jboss.errai.bus.server.annotations.Service;

@Service
public class HelloWorldService implements MessageCallback {
    public void callback(Message message) {
        MessageBuilder.createConversation(message)
                .toSubject("ClientListener")
                .signalling()
                .withProvided("Text", new ResourceProvider<String>() {
                    public String get() {
                        return System.currentTimeMillis() + "";
                    }
                })
                .noErrorHandling().replyRepeating(TimeUnit.MILLISECONDS, 50);
    }
}
