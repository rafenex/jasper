package br.com.aula.jasperreport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aula.jasperreport.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

}
