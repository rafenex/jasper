package br.com.aula.jasperreport.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aula.jasperreport.entity.Funcionario;
import br.com.aula.jasperreport.repository.FuncionarioRepository;
import br.com.aula.jasperreport.service.ReportService;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping
public class Jasper {
	
	@Autowired
	private FuncionarioRepository repository;
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/getFuncionarios")
	private List<Funcionario> getFuncionarios(){
		return repository.findAll();		
		
	}
	
	@GetMapping("/report/{format}")
	private String generateReport(@PathVariable String format) throws FileNotFoundException, JRException{
		return service.exportReport(format);		
		
	}

}
