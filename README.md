

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

Per eseguire correttamente l'applicazione, è necessario configurare il file `application.properties` con le credenziali di accesso corrette per il database MySQL. Di seguito è riportato un esempio di configurazione, con la parte relativa all'username e alla password evidenziata:

```properties
spring.datasource.url = jdbc:mysql://localhost:3306/login_system
spring.datasource.username = [Inserisci qui l'username del tuo database MySQL]
spring.datasource.password = [Inserisci qui la password del tuo database MySQL]
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

Assicurati di sostituire `[Inserisci qui l'username del tuo database MySQL]` e `[Inserisci qui la password del tuo database MySQL]` con le credenziali effettive utilizzate per accedere al tuo database MySQL.


## Installazione e Avvio

Per eseguire l'applicazione, segui questi passaggi:

1. Clona il repository sul tuo computer.
2. Configura correttamente il file `application.properties`.
3. Compila ed esegui l'applicazione utilizzando Maven o un IDE come IntelliJ o Eclipse (Progetto sviluppato utilizzando SpringToolSuite 4).

```bash
git clone https://github.com/utente/nome-progetto.git
cd nome-progetto
mvn spring-boot:run
```

Certamente, ampliamo punto per punto le istruzioni per l'utilizzo:

1. **Registra un nuovo utente inviando una richiesta POST all'endpoint `/api/register/register` con i dati richiesti:**

    - Utilizzando un'applicazione per fare richieste HTTP come Postman, crea una nuova richiesta di tipo POST.
    - Imposta l'URL dell'endpoint su `http://localhost:8080/api/register/register`.
    - Seleziona il formato del corpo della richiesta come JSON.
    - Inserisci i dati dell'utente nel corpo della richiesta. Ad esempio:

    ```json
    {
        "name": "Nome Utente",
        "username": "username",
        "email": "email@example.com",
        "password": "password"
    }
    ```

    - Invia la richiesta. Se i dati inseriti sono validi e non esistono già altri utenti con lo stesso username o email, riceverai una risposta con lo status code 200 OK e il messaggio "User registered successfully". In caso contrario, riceverai un messaggio di errore indicante il problema riscontrato.

2. **Effettua il login inviando una richiesta POST all'endpoint `/api/auth/login` con le credenziali dell'utente:**

    - Crea una nuova richiesta di tipo POST su Postman.
    - Imposta l'URL dell'endpoint su `http://localhost:8080/api/auth/login`.
    - Seleziona il formato del corpo della richiesta come JSON.
    - Inserisci le credenziali dell'utente nel corpo della richiesta. Ad esempio:

    ```json
    {
        "usernameOrEmail": "username",
        "password": "password"
    }
    ```

    - Invia la richiesta. Se le credenziali sono corrette, riceverai una risposta con lo status code 200 OK e un token JWT nel corpo della risposta. Questo token sarà utilizzato per autenticare le successive richieste.

3. **Utilizza il token JWT ottenuto per accedere alle altre funzionalità dell'applicazione:**

    - Dopo aver ottenuto il token JWT dalla richiesta di login, includilo nell'header Authorization di tutte le richieste successive.
    - Quando invii una richiesta per gestire i dati anagrafici dei clienti o accedere ad altre funzionalità protette, assicurati di includere il token JWT nell'header Authorization come segue:

    ```
    Authorization: Bearer [token JWT]
    ```

    - Includi questo header nelle tue richieste POST, PUT, GET e DELETE per accedere alle API REST relative ai clienti o ad altre funzionalità dell'applicazione.

Queste istruzioni forniranno agli utenti una guida dettagliata su come registrarsi, effettuare il login e utilizzare l'applicazione per gestire i dati anagrafici dei clienti.

## Gestione Anagrafica 

Per utilizzare correttamente le API REST relative ai clienti utilizzando Postman, segui le istruzioni dettagliate di seguito:

1. **Creazione di un Cliente:**
   - Crea una nuova richiesta di tipo POST su Postman.
   - Imposta l'URL dell'endpoint su `http://localhost:8080/api/customer`.
   - Seleziona il formato del corpo della richiesta come JSON.
   - Inserisci i dati del cliente nel corpo della richiesta. Ad esempio:

     ```json
     {
         "nome": "Mario",
         "cognome": "Rossi",
         "email": "mario.rossi@example.com",
         "residenza": "Via Roma 123",
         "codiceFiscale": "RSSMRA80A01H501T"
     }
     ```

   - Invia la richiesta. Se i dati sono validi e non esiste già un cliente con lo stesso codice fiscale, riceverai una risposta con lo status code 200 OK e i dettagli del cliente creato.

2. **Aggiornamento di un Cliente:**
   - Crea una nuova richiesta di tipo PUT su Postman.
   - Imposta l'URL dell'endpoint su `http://localhost:8080/api/customer/{id}`, dove `{id}` è l'ID del cliente che desideri aggiornare.
   - Seleziona il formato del corpo della richiesta come JSON.
   - Inserisci i nuovi dati del cliente nel corpo della richiesta. Ad esempio:

     ```json
     {
         "nome": "Mario",
         "cognome": "Bianchi",
         "email": "mario.bianchi@example.com",
         "residenza": "Via Milano 456",
         "codiceFiscale": "RSSMBN80A01H501T"
     }
     ```

   - Invia la richiesta. Se i dati sono validi e l'ID del cliente esiste nel database, riceverai una risposta con lo status code 200 OK e i dettagli del cliente aggiornato.

3. **Recupero di Tutti i Clienti:**
   - Crea una nuova richiesta di tipo GET su Postman.
   - Imposta l'URL dell'endpoint su `http://localhost:8080/api/customer`.
   - Invia la richiesta. Riceverai una risposta con lo status code 200 OK e una lista contenente tutti i clienti presenti nel database.

4. **Recupero di un Cliente Specifico:**
   - Crea una nuova richiesta di tipo GET su Postman.
   - Imposta l'URL dell'endpoint su `http://localhost:8080/api/customer/{id}`, dove `{id}` è l'ID del cliente che desideri recuperare.
   - Invia la richiesta. Se l'ID del cliente esiste nel database, riceverai una risposta con lo status code 200 OK e i dettagli del cliente specifico.

5. **Eliminazione di un Cliente:**
   - Crea una nuova richiesta di tipo DELETE su Postman.
   - Imposta l'URL dell'endpoint su `http://localhost:8080/api/customer/{id}`, dove `{id}` è l'ID del cliente che desideri eliminare.
   - Invia la richiesta. Se l'ID del cliente esiste nel database e l'eliminazione è avvenuta con successo, riceverai una risposta con lo status code 204 No Content.

Seguendo queste istruzioni, potrai interagire correttamente con le API REST relative ai clienti utilizzando Postman. Assicurati di includere tutti i dettagli necessari nei dati inviati e di utilizzare gli endpoint corretti per ogni operazione.

--
