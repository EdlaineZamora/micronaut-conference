package micronaut.conference.presentation.event;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import io.micronaut.configuration.rabbitmq.connect.ChannelInitializer;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class ChannelPoolListener extends ChannelInitializer {

    @Override
    public void initialize(Channel channel) throws IOException {
        channel.exchangeDeclare("micronaut.channels.conference", BuiltinExchangeType.TOPIC, true);
        channel.queueDeclare("micronaut.channels.conference.consumerx", true, false, false, null);
        channel.queueBind("micronaut.channels.conference.consumerx", "micronaut.channels.conference", "micronaut.channels.conference.consumerx");
    }

}
