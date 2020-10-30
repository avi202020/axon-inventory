package com.example.inventory.configuration;

import com.example.inventory.aggregates.InventoryAggregate;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.modelling.command.GenericJpaRepository;
import org.axonframework.modelling.command.Repository;
import org.axonframework.springboot.autoconfig.AxonAutoConfiguration;
import org.axonframework.springboot.util.RegisterDefaultEntities;
import org.axonframework.springboot.util.jpa.ContainerManagedEntityManagerProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RegisterDefaultEntities(packages = {
        "org.axonframework.eventsourcing.eventstore.jpa"
})
public class AxonConfiguration {

    @Bean
    public EntityManagerProvider entityManagerProvider() {
        return new ContainerManagedEntityManagerProvider();
    }

    @Bean
    public Repository<InventoryAggregate> publicationRepository(EntityManagerProvider entityManagerProvider, EventBus eventBus) {
        return GenericJpaRepository.builder(InventoryAggregate.class).entityManagerProvider(entityManagerProvider).eventBus(eventBus).build();
    }

    @Bean
    public EventStorageEngine eventStorageEngine(EntityManagerProvider entityManagerProvider, TransactionManager transactionManager) {
        return JpaEventStorageEngine.builder().entityManagerProvider(entityManagerProvider).transactionManager(transactionManager).build();
    }
}
