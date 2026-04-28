Delivery - Java Spring Boot Микросервисная система для доставки товаров с самовывозом из пункта выдачи
```
delivery/
├── common/                           # Общие модули, DTO и модели
│   └── src/main/java/dev/andrsn/delivery/common/
│       ├── dto/                     # Data Transfer Objects
│       └── model/                   # Enums и общие модели
├── application-storage/              # Сервис хранения заявок
│   └── src/
│       ├── main/java/dev/com/andrsn/
│       │   ├── entity/              # JPA сущности
│       │   ├── repository/          # Repository слой
│       │   ├── service/             # Business logic
│       │   └── controller/          # REST API
│       └── main/resources/
│           ├── application.yml
│           └── db/changelog/         # Liquibase миграции
│               └── db.changelog-master.yaml
├── build-logic/                      # Логика сборки проекта
├── gradle/                           # Gradle конфигурация
│   ├── libs.versions.toml           # Версии зависимостей
│   └── wrapper/                     # Gradle wrapper
├── build.gradle.kts                  # Корневой build файл
├── settings.gradle.kts               # Настройки проекта
├── gradle.properties                 # Свойства Gradle
├── .gitignore                        # Git ignore файл
├── .gitattributes                    # Git атрибуты
├── gradlew                           # Gradle wrapper (Unix)
├── gradlew.bat                       # Gradle wrapper (Windows)
└── README.md                         # Документация проекта
```

стек Java 17+,
Spring Boot 3.2.0 ,
Spring Data JPA,
Liquibase - управление миграциями БД ,
Lombok - сокращение бойлерплейта ,
Swagger- документация API ,
H2 - in-memory база данных ,
PostgreSQL - основная БД ,
Gradle - система сборки