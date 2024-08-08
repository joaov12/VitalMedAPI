package com.group.vitalmedapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.vitalmedapi.dtos.AddFuncionarioToDepartamentoDTO;
import com.group.vitalmedapi.models.Departamento;
import com.group.vitalmedapi.models.Funcionario;
import com.group.vitalmedapi.repositories.DepartamentoRepository;
import com.group.vitalmedapi.repositories.FuncionarioRepository;

@Service
public class DepartamentoService {

    @Autowired
    DepartamentoRepository departamentoRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public List<Departamento> findAll() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        if (departamentos.isEmpty()) {
            throw new RuntimeException("Nenhum departamento encontrado.");
        }
        return departamentos;
    }

    public Departamento findById(Long id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe departamento com o ID: " + id));
    }

    public Departamento addDepartamento(Departamento departamento) {
        try {
            return departamentoRepository.save(departamento);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar departamento", e);
        }
    }

    public Departamento updateDepartamento(Departamento departamento) {
        if (!departamentoRepository.existsById(departamento.getId())) {
            throw new RuntimeException("Departamento não encontrado para o ID: " + departamento.getId());
        }

        try {
            return departamentoRepository.save(departamento);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar departamento", e);
        }
    }

    public void deleteDepartamento(Long id) {
        departamentoRepository.deleteById(id);
    }

    public Departamento addFuncionarioToDepartamento(AddFuncionarioToDepartamentoDTO dto) {
        Optional<Departamento> departamentoOptional = departamentoRepository.findById(dto.getDepartamentoId());
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(dto.getFuncionarioId());

        if (departamentoOptional.isPresent() && funcionarioOptional.isPresent()) {
            Departamento departamento = departamentoOptional.get();
            Funcionario funcionario = funcionarioOptional.get();
            departamento.addFuncionario(funcionario);
            return departamentoRepository.save(departamento);
        }

        throw new RuntimeException("Departamento ou Funcionário não encontrado");
    }
}
