package fi.roskisprojekti.application.port.in.dto;

import java.math.BigDecimal;
//Toistaiseksi turhia?
public record BinDto(Long binId, Long siteId, String name, BigDecimal capacityLiters) {
}
