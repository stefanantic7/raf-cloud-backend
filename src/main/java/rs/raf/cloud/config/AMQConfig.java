package rs.raf.cloud.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQConfig {

    @Bean
    public Queue startMachineQueue() {
        return new Queue("startMachineQueue", false);
    }

    @Bean
    public Queue stopMachineQueue() {
        return new Queue("stopMachineQueue", false);
    }

}
