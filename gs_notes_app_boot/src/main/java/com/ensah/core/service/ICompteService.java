package com.ensah.core.service;



import com.ensah.core.bo.Compte;
import com.ensah.core.bo.Role;
import com.ensah.core.utils.ExcelExporter;

import java.util.List;


public interface ICompteService {
	
	public List<Role> getAllRoles();
	
	public List<Compte> getAllAccounts();

	
	public Compte getAccountByUserName(String login);
	
	public String createUser(Long idRole, Long idPerson);

	public int getRole(Long idPerson);
	
	public ExcelExporter prepareCompteExport(List<Compte> comptes) ;
}
