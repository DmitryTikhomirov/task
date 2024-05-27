package com.kupreychik.config;

import com.kupreychik.listeners.TaskEntityListener;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

//@Configuration
@RequiredArgsConstructor
public class HibernateListenerConfigurer {

    private final TaskEntityListener customPostPersistListener;
    private final EntityManagerFactory entityManagerFactory;

    //TODO add correct bean for hibernate listener in spring
}