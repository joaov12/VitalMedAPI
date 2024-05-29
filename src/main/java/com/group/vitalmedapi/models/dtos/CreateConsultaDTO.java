package com.group.vitalmedapi.models.dtos;

import java.util.Date;

public class CreateConsultaDTO {
    private Date dataHora;
    private Long medicoId;
    private Long pacienteId;
    private String motivoDaConsulta;
    
    public Date getDataHora() {
        return dataHora;
    }
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }
    public Long getMedicoId() {
        return medicoId;
    }
    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }
    public Long getPacienteId() {
        return pacienteId;
    }
    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }
    public String getMotivoDaConsulta() {
        return motivoDaConsulta;
    }
    public void setMotivoDaConsulta(String motivoDaConsulta) {
        this.motivoDaConsulta = motivoDaConsulta;
    }

    

}
