package fi.roskisprojekti.domain.entity.employee;

import java.util.Objects;

import static fi.roskisprojekti.domain.validation.DomainValidator.notNull;

public record Email(String value) {
    public Email{
        notNull(value, "Email ");

        value = value.toLowerCase().trim();


        //Todo tää domainvalidaattoriin tjsp
        if (!value.endsWith("@roskis.fi")) {
            throw new IllegalArgumentException("Email must belong to roskis.fi organization spurdo spärde jekku :D");
        }

    }
}
