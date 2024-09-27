package com.study.coroutines_crud.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers
import java.util.concurrent.Executors

@Configuration
class SchedulerConfig {
    // @param:Value("\${spring.datasource.maximum-pool-size}") private val connectionPoolSize: Int) {

    @Bean
    fun jdbcScheduler(): Scheduler = Schedulers.fromExecutor(Executors.newFixedThreadPool(1000))
}
