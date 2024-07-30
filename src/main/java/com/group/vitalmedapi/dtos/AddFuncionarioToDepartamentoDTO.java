package com.group.vitalmedapi.dtos;

public class AddFuncionarioToDepartamentoDTO {
    private Long departamentoId;
    private Long funcionarioId;

    public Long getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }

    public Long getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }
}
