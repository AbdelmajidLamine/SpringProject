package com.ensah.core.service.impl;


import com.ensah.core.bo.Utilisateur;
import com.ensah.core.dao.IUtilisateurDao;
import com.ensah.core.service.IPersonService;
import com.ensah.core.utils.ExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private IUtilisateurDao personDao;

	public List<Utilisateur> getAllPersons() {

		return personDao.findAll();
	}

	public void deletePerson(Long id) {
		personDao.deleteById(id);

	}

	public Utilisateur getPersonById(Long id) {
		return personDao.getById(id);

	}

	public void addPerson(Utilisateur pPerson) {
		personDao.save(pPerson);

	}

	public void updatePerson(Utilisateur pPerson) {
		personDao.save(pPerson);

	}

	public ExcelExporter preparePersonExport(List<Utilisateur> persons) {
		String[] columnNames = new String[] { "Nom", "Prénom", "CIN", "Email", "Télé" };
		String[][] data = new String[persons.size()][5];

		int i = 0;
		for (Utilisateur u : persons) {
			data[i][0] = u.getNom();
			data[i][1] = u.getPrenom();
			data[i][2] = u.getCin();
			data[i][3] = u.getEmail();
			data[i][4] = u.getTelephone();
			i++;
		}

		return new ExcelExporter(columnNames, data, "persons");

	}

	public Utilisateur getPersonByCin(String cin) {

		return personDao.getEntityByColValue(Utilisateur.class, "cin", cin);

	}

	@Override
	public List<Utilisateur> getPersonByName(String firstName, String lastName) {
		List<Utilisateur> users=personDao.findByName(firstName,lastName);
		return users;
	}

}
