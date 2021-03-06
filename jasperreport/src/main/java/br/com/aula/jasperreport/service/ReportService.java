package br.com.aula.jasperreport.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import br.com.aula.jasperreport.entity.Funcionario;
import br.com.aula.jasperreport.repository.FuncionarioRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {
	
	@Autowired
	private FuncionarioRepository repository;
	
	public String exportReport(String reportFormat)throws FileNotFoundException, JRException {
		
		String path = "C:\\JasperTeste\\Report";
		
		List<Funcionario> funcionarios = repository.findAll();
		File file = ResourceUtils.getFile("classpath:funcionarios.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(funcionarios);		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("Criado por ", "Treinamento CastGroup");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters , datasource); 
		
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\funcionarios.html");
		}
		if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\funcionarios.pdf");
		}
		
		return "report gerado no caminho: " + path;
		
	}

}
