package fi.roskisprojekti.domain.entity;
/**
 * Entiteetit, jotka koostuvat value objecteista. Näissä on vielä sen verran hommaa, että suurimalle osalle value
 * objecteista vain räiskitty private final pääentiteetin kentiksi, mikä ei ole joka tilanteeseen ihan sopiva
 * lähestymistapa.
 * Näiden ei tarvitse olla 1:1 peilikuva tietokannasta, joten tänne voi toteuttaa sellaista logiikkaa, mitä vaikka
 * tietokantaan ei pysty tallentamaan tällä hetkellä. Adapter moduulissa sitten hoidetaan domain entiteetin parsiminen
 * esimerkiksi tietokannan suomaan muotoon.
 * */