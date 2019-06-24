package micronaut.conference.presentation.event;

import io.micronaut.configuration.rabbitmq.annotation.Queue;
import io.micronaut.configuration.rabbitmq.annotation.RabbitListener;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;

@Requires(notEnv = Environment.TEST)
@RabbitListener
public class ConferenceConsumer {

    @Queue(value = "micronaut.channels.conference.consumerx")
    public void listenMessage(byte[] message) {
        System.out.println(new String(message));
    }

}
