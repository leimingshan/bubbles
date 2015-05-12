package com.bubbles.server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by LMSH on 2015/5/12.
 */
@Configuration
@ComponentScan(basePackages = "com.bubbles.server")
@EnableJpaRepositories("com.bubbles.server.domain")
public class AppConfig {
}
