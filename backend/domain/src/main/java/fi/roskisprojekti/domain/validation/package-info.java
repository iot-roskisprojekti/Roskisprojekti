package fi.roskisprojekti.domain.validation;

/**
 * DomainValidator = niputtaa yleiset validaatiot value objectien sisältä yleiskäyttöisemmiksi metodeiksi. Ihan raakile
 * vielä, mutta poistanut jo kivasti turhaa toistoa valua objecteista.
 * DomainValidationException = heittää poikkeuksen, kun DomainValidatorin metodien sääntöjä rikkova ehto toteutuu. Ihan
 * kätevän oloinen, heittää siis DomainValidator luokan metodien sisällä määriteltyjä virheherjoja konsoliin ja heittää
 * ne jopa tuonne frontille asti ohjelman ollessa käynnissä!
 * */