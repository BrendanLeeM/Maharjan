package com.example.demo.websockets;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
/**
 * Configurator for websockets
 * @author 
 *
 */
@ConditionalOnWebApplication
@Configuration 
public class WebSocketConfig {
@Bean
public ServerEndpointExporter serverEndpointExporter() {
	return new ServerEndpointExporter();
}

@Bean
public CustomConfigurator customConfigurator() {
	return new CustomConfigurator();
}
}