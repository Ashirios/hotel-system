
# Hotel Management System

Система управления отелем на Spring Boot с REST API, JPA и Docker.

## Содержание
- [Технологии](#технологии)
- [Архитектура](#архитектура)
- [Требования](#требования)
- [Запуск приложения](#запуск-приложения)
  - [Локальный запуск](#локальный-запуск)
  - [Запуск через Docker](#запуск-через-docker)
- [API Endpoints](#api-endpoints)
  - [Пользователи](#пользователи)
  - [Комнаты](#комнаты)
  - [Бронирования](#бронирования)
- [Структура проекта](#структура-проекта)
- [Конфигурация](#конфигурация)
- [Docker](#docker)
- [Тестирование](#тестирование)
- [Авторы](#авторы)

## Технологии

- Java 17
- Spring Boot 3.2.3
- Spring MVC - REST API
- Spring Data JPA - работа с базой данных
- Spring Security - аутентификация и авторизация
- PostgreSQL - база данных
- Maven - сборка проекта
- Lombok - генерация кода
- Docker - контейнеризация
- JWT - токены доступа

## Архитектура

Проект построен по многослойной архитектуре:

```
┌─────────────────┐
│   Controllers   │  REST endpoints
├─────────────────┤
│    Services     │  Бизнес-логика
├─────────────────┤
│    Mappers      │  Преобразование Entity DTO
├─────────────────┤
│   Repositories  │  Работа с БД
├─────────────────┤
│     Entities    │  Модели данных
└─────────────────┘
```

## Требования

- Java 17 или выше
- Maven 3.6+
- PostgreSQL 12+
- Docker (опционально)
- Docker Compose (опционально)

## Запуск приложения

### Локальный запуск

1. Клонируйте репозиторий:
```bash
git clone https://github.com/your-username/hotel-system.git
cd hotel-system
```

2. Настройте базу данных PostgreSQL:
```sql
CREATE DATABASE hotel;
CREATE USER postgres WITH PASSWORD '1234';
GRANT ALL PRIVILEGES ON DATABASE hotel TO postgres;
```

3. Соберите проект:
```bash
mvn clean package
```

4. Запустите приложение:
```bash
mvn spring-boot:run
```

5. Приложение доступно по адресу:
```
http://localhost:8080
```

### Запуск через Docker

1. Соберите образ:
```bash
docker build -t hotel-system:latest .
```

2. Запустите с локальной БД:
```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/hotel \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=1234 \
  hotel-system:latest
```

3. Или используйте Docker Compose (с PostgreSQL в контейнере):
```bash
docker-compose up -d
```

## API Endpoints

### Пользователи (/users)

| Метод | Endpoint | Описание | Тело запроса | Ответ |
|-------|----------|----------|--------------|--------|
| GET | /users | Получить всех пользователей | - | List<UserProfileDto> |
| GET | /users/{id} | Получить пользователя по ID | - | UserProfileDto |
| POST | /users/register | Регистрация | RegistrationDto | UserProfileDto |
| POST | /users/{id} | Обновить пользователя | UserUpdateDto | UserProfileDto |
| POST | /users/login | Вход в систему | LoginRequestDto | UserProfileDto |
| DELETE | /users/{id} | Удалить пользователя | - | void |

Пример запроса регистрации:
```json
POST /users/register
{
  "username": "john",
  "password": "123456",
  "email": "john@example.com"
}
```

Пример ответа:
```json
{
  "id": 1,
  "username": "john",
  "email": "john@example.com"
}
```

### Комнаты (/rooms)

| Метод | Endpoint | Описание | Тело запроса | Ответ |
|-------|----------|----------|--------------|--------|
| GET | /rooms | Получить все комнаты | - | List<RoomResponseDto> |
| GET | /rooms/{id} | Получить комнату по ID | - | RoomResponseDto |
| POST | /rooms | Создать комнату | RoomCreateDto | RoomResponseDto |
| PUT | /rooms/{id} | Обновить комнату | RoomUpdateDto | RoomResponseDto |
| DELETE | /rooms/{id} | Удалить комнату | - | void |

Пример создания комнаты:
```json
POST /rooms
{
  "roomNumber": 101,
  "roomType": "STANDARD",
  "price": 3500.00
}
```

### Бронирования (/bookings)

| Метод | Endpoint | Описание | Тело запроса | Ответ |
|-------|----------|----------|--------------|--------|
| GET | /bookings | Получить все бронирования | - | List<BookingResponseDto> |
| GET | /bookings/{id} | Получить бронирование по ID | - | BookingResponseDto |
| POST | /bookings | Создать бронирование | BookingCreateDto | BookingResponseDto |
| DELETE | /bookings/{id} | Отменить бронирование | - | void |

Пример создания бронирования:
```json
POST /bookings
{
  "startDate": "2026-03-15",
  "endDate": "2026-03-20"
}
```

## Структура проекта

```
hotel-system/
├── src/
│   ├── main/
│   │   ├── java/com/hotel/hotel_system/
│   │   │   ├── api/
│   │   │   │   ├── controllers/          REST контроллеры
│   │   │   │   │   ├── BookingController.java
│   │   │   │   │   ├── RoomController.java
│   │   │   │   │   └── UserController.java
│   │   │   │   ├── dto/                   Data Transfer Objects
│   │   │   │   │   ├── requests/          Входящие DTO
│   │   │   │   │   │   ├── BookingCreateDto.java
│   │   │   │   │   │   ├── LoginRequestDto.java
│   │   │   │   │   │   ├── RegistrationDto.java
│   │   │   │   │   │   ├── RoomCreateDto.java
│   │   │   │   │   │   ├── RoomUpdateDto.java
│   │   │   │   │   │   └── UserUpdateDto.java
│   │   │   │   │   └── responses/         Исходящие DTO
│   │   │   │   │       ├── BookingResponseDto.java
│   │   │   │   │       ├── LoginResponseDto.java
│   │   │   │   │       ├── RoomResponseDto.java
│   │   │   │   │       └── UserProfileDto.java
│   │   │   │   ├── exceptions/            Обработка ошибок
│   │   │   │   │   └── EntityNotFoundException.java
│   │   │   │   ├── mappers/               Мапперы Entity  DTO
│   │   │   │   │   ├── BookingMapper.java
│   │   │   │   │   ├── BookingMapperImpl.java
│   │   │   │   │   ├── RoomMapper.java
│   │   │   │   │   ├── RoomMapperImpl.java
│   │   │   │   │   ├── UserMapper.java
│   │   │   │   │   └── UserMapperImpl.java
│   │   │   │   └── services/              Бизнес-логика
│   │   │   │       ├── BookingService.java
│   │   │   │       ├── RoomService.java
│   │   │   │       └── UserService.java
│   │   │   ├── config/                    Конфигурации Spring
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── store/
│   │   │   │   ├── entities/              JPA сущности
│   │   │   │   │   ├── BookingEntity.java
│   │   │   │   │   ├── RoomEntity.java
│   │   │   │   │   └── UserEntity.java
│   │   │   │   ├── enums/                  Перечисления
│   │   │   │   │   ├── Role.java
│   │   │   │   │   ├── RoomType.java
│   │   │   │   │   └── Status.java
│   │   │   │   └── repositories/           Spring Data репозитории
│   │   │   │       ├── BookingRepository.java
│   │   │   │       ├── RoomRepository.java
│   │   │   │       └── UserRepository.java
│   │   │   └── HotelSystemApplication.java  Главный класс приложения
│   │   └── resources/
│   │       └── application.properties      Конфигурационный файл
│   └── test/                               Тесты
│       └── java/com/hotel/hotel_system/
│           ├── DtoTest.java
│           └── HotelSystemApplicationTests.java
├── Dockerfile                               Docker образ
├── docker-compose.yml                       Docker Compose
├── pom.xml                                  Maven конфигурация
└── README.md                                Документация
```

## Конфигурация

Основные настройки в `application.properties`:

```properties
# База данных
spring.datasource.url=jdbc:postgresql://localhost:5432/hotel
spring.datasource.username=postgres
spring.datasource.password=1234
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
spring.jpa.hibernate.naming.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl

# Логирование
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

## Docker

### Dockerfile
```dockerfile
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY target/hotel-system-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
```

### Docker Compose
```yaml
services:
  app:
    build: .
    container_name: hotel-app
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://host.docker.internal:5432/hotel
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: 1234
    extra_hosts:
      - "host.docker.internal:host-gateway"
```

## Тестирование

Запуск тестов:
```bash
mvn test
```

Проверка API через curl:
```bash
# Получить всех пользователей
curl http://localhost:8080/users

# Создать пользователя
curl -X POST http://localhost:8080/users/register \
  -H "Content-Type: application/json" \
  -d '{"username":"john","password":"123456","email":"john@email.com"}'

# Войти в систему
curl -X POST http://localhost:8080/users/login \
  -H "Content-Type: application/json" \
  -d '{"username":"john","password":"123456"}'

# Создать комнату
curl -X POST http://localhost:8080/rooms \
  -H "Content-Type: application/json" \
  -d '{"roomNumber":101,"roomType":"STANDARD","price":3500.00}'

# Создать бронирование
curl -X POST http://localhost:8080/bookings \
  -H "Content-Type: application/json" \
  -d '{"startDate":"2026-03-15","endDate":"2026-03-20"}'
```
