package ru.itupolev.hackaton.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync // Можно поставить здесь вместо Main класса
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);        // 5 потоков работают всегда
        executor.setMaxPoolSize(10);        // Максимум 10 потоков при нагрузке
        executor.setQueueCapacity(500);     // Очередь задач (если потоки заняты)
        executor.setThreadNamePrefix("EmailThread-");
        executor.initialize();
        return executor;
    }
}