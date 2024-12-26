package com.restdev.apirestcompleta.repository.Projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@JsonInclude(JsonInclude.Include.NON_NULL)

public interface ClienteVagaProjection {
  String getplaca();
  String getmarca();
  String getmodelo();
  String getcor();
  String getrecibo();
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  LocalDateTime getdtEntrada();
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  LocalDateTime getdtSaida();
  String getvagaCodigo();
  BigDecimal getvalor();
  BigDecimal getdesconto();
}
