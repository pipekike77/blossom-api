package com.ecommerce.blossom.api.blossom_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncConfig {

    /*
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4); // Número de hilos mínimos
        executor.setMaxPoolSize(10); // Máximo de hilos
        executor.setQueueCapacity(50); // Capacidad de cola
        executor.setThreadNamePrefix("AsyncExecutor-"); // Prefijo de hilos
        executor.initialize();
        return executor;
    }
     */
}
