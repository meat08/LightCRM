package ru.lightcrm.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.lightcrm.repositories.SearchableEntityRepositoryImpl;

/**
 * Конфигурации требуется, чтобы подменить стандартный класс репозитория.
 * basePackages можно не указывать, но тогда долгий запуск приложения.
 */
@Configuration
@EnableJpaRepositories(basePackages = "ru.lightcrm.repositories",
        repositoryBaseClass = SearchableEntityRepositoryImpl.class)
public class SearchableEntityRepositoryConfig {
}
