/**
 * Lombokki apuna, ettei pää säry getterien, setterien ja alustajien säädön kanssa. Muuten ultrapuristi -javailua :D
 * Tämä moduuli on sellainen autuaan tietämätön, omassa kuplassaan elävä kokonaisuus, jonka tehtävä on vain mallintaa
 * ja toteuttaa vaatimusmäärittelyn (jota ei kauheasti tarvinnut tehdä asiantuntevan asiakkaan viskoessa vaatimukset
 * viikottain päin pläsiä) pohjalta esiin nousseet vaatimukset/käyttötapaukset/logiikka mahdollisimman yksinkertaisena
 * Javana ja sellaisella tasolla, jossa ei tarvitse välittää lainkaan vaikkapa siitä, että millaiseen tietokantaan tietoa
 * tallennetaan, mistä telemetria tulee ja missä muodossa jnejne.
 * Em asioiden yhteensovittaminen tapahtuu täysin muiden moduulien välityksellä.
 *
 * Täällä voi siis rauhassa miettiä vaikkapa älyroskiksen syvintä olemusta ja sitä, että mistä se koostuu ja mallintaa
 * sen ominaisuudet roskis tai vaikkapa aggregate root -entiteettinä, eikä tarvitse säryttää päätänsä sen suhteen,
 * että miten entiteetin saa vuorovaikutukseen... Minkään kanssa.
 * Tiivistettynä: täällä määritellään miten ja millaisista entiteeteistä älykäs roskasäiliön pinnantarkkailu rakentuu,
 * prosessin säännöt ja tylysti myös se, että mitä domain-moduulin entiteeteille on pakko syöttää, että ne suostuvat
 * edes kääntämään kylkeään.
 * */

module domain {
    requires static lombok;
}