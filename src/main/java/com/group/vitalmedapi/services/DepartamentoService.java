package com.group.vitalmedapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.vitalmedapi.models.Departamento;
import com.group.vitalmedapi.models.Funcionario;
import com.group.vitalmedapi.models.dtos.AddFuncionarioToDepartamentoDTO;
import com.group.vitalmedapi.repositories.DepartamentoRepository;
import com.group.vitalmedapi.repositories.FuncionarioRepository;

@Service
public class DepartamentoService {
    
    @Autowired
    DepartamentoRepository departamentoRepository;
    
    @Autowired
    FuncionarioRepository funcionarioRepository;

    public List<Departamento> findAll(){
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> findById(Long id) {
		return departamentoRepository.findById(id);
	}

    public Departamento addDepartamento(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public Departamento updateDepartamento(Departamento departamento) {
        return departamentoRepository.save(departamento);
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
