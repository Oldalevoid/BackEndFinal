Per migliorare l'usabilità e la comprensione del tuo progetto, ecco una bozza del README che potresti scrivere:

---

# Sistema di Gestione Clienti

Il Sistema di Gestione Clienti è un'applicazione che consente agli utenti di registrarsi, effettuare il login e gestire i dati anagrafici dei clienti di uno studio professionale. L'autenticazione degli utenti avviene tramite JSON Web Token (JWT).

## Caratteristiche Principali

- Registrazione degli utenti
- Autenticazione tramite JWT
- Gestione dei dati anagrafici dei clienti (creazione, modifica ed eliminazione)

## Tecnologie Utilizzate

- Java
- Spring Boot
- Spring Security
- MySQL
- JSON Web Token (JWT)
- Thymeleaf (per la gestione dei template HTML)

## Configurazione

Per eseguire correttamente l'applicazione, è necessario configurare correttamente il file `application.properties`. Di seguito è riportata un'esempio di configurazione:

```properties
spring.datasource.url = jdbc:mysql://localhost:3306/login_system
spring.datasource.username = root
spring.datasource.password = /
spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
server.max-http-request-header-size=20MB
spring.jpa.hibernate.ddl-auto = update
logging.level.org.springframework.security=DEBUG
app.jwt.secret= daf66e01593f61a15b857cf433aae03a005812b31234e149036bcc8dee755dbb
app.jwt-expiration-milliseconds=604800000
spring.thymeleaf.prefix=classpath:/templates/
```

Assicurati di configurare correttamente anche il database MySQL e di avere un ruolo predefinito per gli utenti.

## Installazione e Avvio

Per eseguire l'applicazione, segui questi passaggi:

1. Clona il repository sul tuo computer.
2. Configura correttamente il file `application.properties`.
3. Compila ed esegui l'applicazione utilizzando Maven o un IDE come IntelliJ o Eclipse.

```bash
git clone https://github.com/utente/nome-progetto.git
cd nome-progetto
mvn spring-boot:run
```

## Utilizzo

1. Registra un nuovo utente inviando una richiesta POST all'endpoint `/api/register/register` con i dati richiesti.
2. Effettua il login inviando una richiesta POST all'endpoint `/api/auth/login` con le credenziali dell'utente.
3. Utilizza il token JWT ottenuto per accedere alle altre funzionalità dell'applicazione.
4. Gestisci i dati anagrafici dei clienti inviando richieste alle API REST relative ai clienti.

## Contributi

Se desideri contribuire a questo progetto, segui questi passaggi:

1. Fai una fork del repository.
2. Crea un branch con una nuova funzionalità (`git checkout -b feature/nome-funzionalità`).
3. Fai commit delle tue modifiche (`git commit -am 'Aggiungi una nuova funzionalità'`).
4. Fai push del branch (`git push origin feature/nome-funzionalità`).
5. Apri una pull request.

## Licenza

Questo progetto è distribuito con licenza [MIT](LICENSE).

---

Assicurati di personalizzare il README in base alle specifiche del tuo progetto e di includere tutte le informazioni necessarie per farlo funzionare correttamente. Buona fortuna con il tuo progetto!
