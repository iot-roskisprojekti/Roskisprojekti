# Roskisprojekti 
Ohjelmistotuotanto 2 ryhmäprojekti

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

## Frontend
Reactilla (Vite) toteutettu frontend roskasäiliön IoT-seurantaprojektiin.  
Tässä vaiheessa repositorio sisältää vain käyttöliittymän lähdekoodin.

### Teknologiat

- React
- Vite
- JavaScript (JSX)
- CSS
- npm

### Vaatimukset

Varmista, että seuraavat ovat asennettuna:

- Node.js (suositus v18 tai uudempi)
- npm
- Git
- Visual Studio Code

### Projektin kloonaus

Kloonaa repositorio omalle koneellesi:

`git clone https://github.com/USERNAME/REPOSITORY_NAME.git`

`cd REPOSITORY_NAME`

### Asenna projektin riippuvuudet:

`npm install`

### Käynnistä Vite kehityspalvelin:

`npm run dev`

Käyttöliittymä avautuu selaimeen osoitteeseen, jonka Vite tulostaa terminaaliin, yleensä:
`http://localhost:xxxx`

TIETOKANTAYHTEYS OHJEET:

INTELLJ:
Avaa View → Tool Windows → Database.

Klikkaa + → Data Source → MySQL.

Syötä:

Host: localhost

Port: 3306

User: root

Password: oma MySQL-salasana

Database: tietokannan nimi

Klikkaa Test Connection → jos onnistuu, paina OK.

Visual Studio Code
Yhdistä MySQL VS Code -laajennuksella (Helpoin)
Asenna MySQL (jos ei vielä ole)

Lataa ja asenna:

👉 MySQL Community Server

Asennuksen aikana:

Muista root-salasana

Oletusportti on yleensä 3306

2. Asenna MySQL-laajennus VS Codeen

Avaa VS Code

Mene Extensions (Ctrl + Shift + X)

Hae:

👉 MySQL (tekijä esim. cweijan)

Paina Install

3.Luo yhteys MySQL-palvelimeen

Paina vasemman reunan MySQL-ikonia

Valitse Add Connection

Täytä tiedot:

Kenttä	Arvo
Host	localhost
User	root
Password	(se jonka asetit)
Port	3306

Klikkaa Connect

Jos kaikki meni oikein, näet tietokannat vasemmalla 🎉

-----------------------------------------------------------------
Sähköpostin lähetys


1. Mailtrap-tunnusten luonti
Mene osoitteeseen mailtrap.io ja luo ilmainen tili.

Mene kohtaan Inboxes -> My Inbox.

Valitse SMTP Settings -välilehdeltä integraatioksi Spring Boot.

2. Tunnusten lisäys koodiin
Etsi sieltä username ja password ja kopioi ne.
--------------------------------------------------------------------------------------------------------------
3. Profiilien päivitys (application.properties)
Lisää kopioimasi tiedot projektin asetuksiin. Jotta homma toimii oikein kehitysvaiheessa, päivitä nämä molempiin tiedostoihin:

Tiedosto 1: backend/infrastructure/src/main/resources/application.properties

Properties
spring.mail.host=sandbox.smtp.mailtrap.io
spring.mail.port=2525
spring.mail.username=SINUN_USERNAM_TÄHÄN
spring.mail.password=SINUN_SALASANA_TÄHÄN
------------------------------------------------------------------------------------
Tiedosto 2: backend/infrastructure/src/main/resources/application-dev.properties

Asetukset:

# Sähköpostipalvelimen osoite (Mailtrap sandbox)
spring.mail.host=sandbox.smtp.mailtrap.io
spring.mail.port=2525

# Mailtrapista haetut henkilökohtaiset tunnukset
spring.mail.username=Kopioi_tähän_tunnus_mailtrapista
spring.mail.password=Kopioi_tähän_salasana_mailtrapista

# Lisäasetukset, jotta yhteys toimii suojatusti
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
------------------------------------------------------------------
