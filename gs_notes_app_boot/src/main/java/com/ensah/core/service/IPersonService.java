package com.ensah.core.service;



import com.ensah.core.bo.Utilisateur;
import com.ensah.core.utils.ExcelExporter;

import java.util.List;

public interface IPersonService {

	public void addPerson(Utilisateur pPerson);

	public void updatePerson(Utilisateur pPerson);

	public List<Utilisateur> getAllPersons();

	public void deletePerson(Long id);

	public Utilisateur getPersonById(Long id);
	
	public Utilisateur getPersonByCin(String cin);

	public List<Utilisateur> getPersonByName(String firstName,String lastName);
	
	public ExcelExporter preparePersonExport(List<Utilisateur> persons);
	
	

}
