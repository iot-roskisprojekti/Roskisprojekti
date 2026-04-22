# Roskisprojekti

Ohjelmistotuotanto 2 kurssin ryhmäprojekti 2026
Itä-Suomen yliopisto

**Tekijät:** Laura Kokko, Mika Ruuhilahti, Aatu Saali, Anttoni Smahl, Nina Takkunen, Jasmin Tikkanen

---

## Lisenssi

MIT License

Copyright (c) [2026] [Laura Kokko, Mika Ruuhilahti, Aatu Saali, Anttoni Smahl, Nina Takkunen, Jasmin Tikkanen]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

---

## Teknologiat

**Frontend:** React, Vite, JavaScript (JSX), CSS, npm  
**Backend:** Spring Boot (Java), Docker, MySQL, MQTT

---

## Vaatimukset

Varmista, että seuraavat ovat asennettuna:

- Node.js (v18 tai uudempi)
- npm
- Git
- Docker tai Docker Desktop
- Vapaavalintainen IDE (esim. Visual Studio Code tai IntelliJ)

---

## Projektin käynnistys

### Vaihe 1 – Kloonaa repositorio ja aseta ympäristömuuttujat

Avaa terminaali ja kloonaa `testailu`-branchi:

```bash
git clone -b testailu https://github.com/iot-roskisprojekti/Roskisprojekti.git
cd Roskisprojekti
```

Nimeä `.env.pohja` tiedosto `.env`:ksi **ennen** kuin avaat projektin Dev Containerina VS Codessa:

```bash
# Linuxilla / macOS:
mv .env.pohja .env

# Windowsilla:
rename .env.pohja .env
```

Avaa `.env` ja aseta vapaavalintainen tietokantasalasana:

```
DB_PASS=päähänsattuu
```

---

### Vaihe 2 – Käynnistä backend, tietokanta ja MQTT Dockerilla

Navigoi juurikansioon ja aja:

```bash
docker compose up --build -d
```

Tämä käynnistää kolme erillistä containeria: backend, MySQL ja MQTT-palvelin.

**Portit:**

| Palvelu   | Host-portti | Container-portti |
|-----------|-------------|------------------|
| Backend   | 8080        | 8080             |
| MySQL     | 3307        | 3306             |
| MQTT      | 1884        | 1883             |

Backendin API löytyy osoitteesta: `http://localhost:8080/api/sites`

> **Huom:** Aja `docker compose up --build -d` aina, kun backend-koodi tai `pom.xml` muuttuu.  
> Muuten riittää: `docker compose up -d`

---

### Vaihe 3 – Frontend (kehityspalvelin)

Asenna riippuvuudet ja käynnistä Vite-kehityspalvelin:

```bash
npm install
npm run dev
```

Käyttöliittymä avautuu selaimeen osoitteeseen, jonka Vite tulostaa terminaaliin – yleensä `http://localhost:5173`.

---

## Dev Container -kehitysympäristö (valinnainen)

Jos haluat kehittää backendiä tai tietokantaa VS Codessa Dev Containerina:

1. Asenna **Dev Containers** -laajennus VS Code Marketplacesta.
2. Avaa kloonattu kansio VS Codessa.
3. VS Code kysyy oikeassa alareunassa: "Reopen in Container" – valitse se.  
   Jos pop-up ei näy, paina `F1` ja kirjoita `Dev Containers: Reopen in Container`.
4. Ensimmäisellä kerralla VS Code buildaa ympäristön – odota rauhassa.
5. Kun ympäristö on käynnissä, valitse `Run → Run without Debugging` käynnistääksesi backendin.  
   VS Coden alareunan toolbaarista voi käynnistää myös frontendin tai kaiken kerralla.

---

## Tietokantayhteys

### IntelliJ IDEA

1. Avaa `View → Tool Windows → Database`
2. Klikkaa `+` → `Data Source` → `MySQL`
3. Syötä tiedot:

   | Kenttä   | Arvo                  |
   |----------|-----------------------|
   | Host     | localhost             |
   | Port     | 3307 *(Dockerin host-portti)* |
   | User     | root                  |
   | Password | `.env`-tiedostoon asettamasi salasana |
   | Database | tietokannan nimi      |

4. Klikkaa `Test Connection` – jos onnistuu, paina `OK`.

### Visual Studio Code

1. Asenna **MySQL**-laajennus (esim. tekijä: cweijan): `Ctrl + Shift + X` → hae "MySQL" → Install.
2. Paina vasemman reunan MySQL-ikonia → `Add Connection`.
3. Täytä tiedot:

   | Kenttä   | Arvo                  |
   |----------|-----------------------|
   | Host     | localhost             |
   | User     | root                  |
   | Password | `.env`-tiedostoon asettamasi salasana |
   | Port     | 3307                  |

4. Klikkaa `Connect` – tietokannat näkyvät vasemmalla paneelissa.

---

## Sähköpostin lähetys (Mailtrap)

Projekti käyttää [Mailtrap](https://mailtrap.io)-sandboxia sähköpostien testaamiseen kehitysvaiheessa.

### Tunnusten luonti

1. Luo ilmainen tili osoitteessa [mailtrap.io](https://mailtrap.io).
2. Mene `Inboxes → My Inbox`.
3. Valitse `SMTP Settings`-välilehdeltä integraatioksi **Spring Boot**.
4. Kopioi `username` ja `password`.

### Tunnusten lisäys projektiin

Lisää kopioimasi tunnukset **molempiin** tiedostoihin:

**`backend/bootstrap/src/main/resources/application.properties`**

```properties
spring.mail.host=sandbox.smtp.mailtrap.io
spring.mail.port=2525
spring.mail.username=SINUN_USERNAME_TÄHÄN
spring.mail.password=SINUN_SALASANA_TÄHÄN
```

**`backend/infrastructure/src/main/resources/application-dev.properties`**

```properties
spring.mail.host=sandbox.smtp.mailtrap.io
spring.mail.port=2525
spring.mail.username=SINUN_USERNAME_TÄHÄN
spring.mail.password=SINUN_SALASANA_TÄHÄN
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

> **Muista:** Älä commitoi oikeita tunnuksia versionhallintaan. Harkitse `.env`-pohjaista ratkaisua tuotantoa varten.
