package com.sks.secondkillstore.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author LZ
 * @Date 2024/4/25 16:19
 * @Version 1.0
 */
public class RabbitMQConfig {

//    private static final String QUEUE01 = "queue_fanout01";
//    private static final String QUEUE02 = "queue_fanout02";
//    private static final String EXCHANGE = "fanoutExchange";
//
//    private static final String DIRECT_QUEUE01 = "direct_queue01";
//    private static final String DIRECT_QUEUE02 = "direct_queue02";
//    private static final String DIRECT_EXCHANGE = "directExchange";
//    private static final String ROUTING_KEY01 = "queue.red";
//    private static final String ROUTING_KEY02 = "queue.green";
//
//
//    @Bean
//    public Queue queue() {
//        return new Queue("queue", true);
//    }
//
//    @Bean
//    public Queue queue01() {
//        return new Queue(QUEUE01);
//    }
//
//    @Bean
//    public Queue queue02() {
//        return new Queue(QUEUE02);
//    }
//
//    @Bean
//    public FanoutExchange fanoutExchange() {
//        return new FanoutExchange(EXCHANGE);
//    }
//
//    @Bean
//    public Binding binding01() {
//        return BindingBuilder.bind(queue01()).to(fanoutExchange());
//    }
//
//    @Bean
//    public Binding binding02() {
//        return BindingBuilder.bind(queue02()).to(fanoutExchange());
//    }
//
//    @Bean
//    public Queue dqueue01() {
//        return new Queue(DIRECT_QUEUE01);
//    }
//
//    @Bean
//    public Queue dqueue02() {
//        return new Queue(DIRECT_QUEUE02);
//    }
//
//    @Bean
//    public DirectExchange directExchange() {
//        return new DirectExchange(DIRECT_EXCHANGE);
//    }
//
//    @Bean
//    public Binding dbinding01() {
//        return BindingBuilder.bind(dqueue01()).to(directExchange()).with(ROUTING_KEY01);
//    }
//
//    @Bean
//    public Binding dbinding02() {
//        return BindingBuilder.bind(dqueue02()).to(directExchange()).with(ROUTING_KEY02);
//    }
}
