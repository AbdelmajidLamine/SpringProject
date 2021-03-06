package com.ensah.core.service.impl;


import com.ensah.core.bo.Compte;
import com.ensah.core.bo.Role;
import com.ensah.core.bo.Utilisateur;
import com.ensah.core.dao.ICompteDao;
import com.ensah.core.dao.IRoleDao;
import com.ensah.core.dao.IUtilisateurDao;
import com.ensah.core.service.ICompteService;
import com.ensah.core.utils.ExcelExporter;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompteServiceImpl implements ICompteService {

	@Autowired
	private ICompteDao userDao;

	@Autowired
	private IRoleDao roleDao;

	@Autowired
	private IUtilisateurDao personDao;

	private PasswordEncoder passwordEncoder;
	/*@Autowired
	public void CompteServiceImpl(PasswordEncoder passwordEncoder){
		this.passwordEncoder=passwordEncoder;
	}*/



	public List<Role> getAllRoles() {
		return roleDao.findAll();
	}

	public List<Compte> getAllAccounts() {
		return userDao.findAll();
	}

	public String createUser(Long idRole, Long idPerson) {

		// récupérer la personne de la base de données
		Utilisateur person = personDao.getById(idPerson);

		// Créer le compte
		Compte userAccount = new Compte();

		// determiner la personne
		userAccount.setProprietaire(person);

		// Affecter le role
		userAccount.setRole(roleDao.getById(idRole));

		// génrer le mot de passe aléatoirement
		String generatedPass = generatePassayPassword();

		// hachage du mot de passe + gain de sel
		String encodedPass = passwordEncoder.encode(generatedPass);

		// affecter ce mot de passe
		userAccount.setPassword(encodedPass);

		// On construit un login de type "nom+prenom " s'il est dispo
		String login = person.getNom() + person.getPrenom();

		Compte account = userDao.searchByLogin(login);

		if (account == null) {

			userAccount.setLogin(login);

			// Créer le compte
			userDao.save(userAccount);
			return generatedPass;
		}

		int i = 0;

		// sinon, on cherche un login de type nom+prenom+"_"+ entier
		while (true) {

			login = person.getNom() + person.getPrenom() + "_" + i;
			account = userDao.searchByLogin(login);
			if (account == null) {
				userAccount.setLogin(login);

				// Créer le compte
				userDao.save(userAccount);
				return generatedPass;
			}

			i++;
		}
	}

	@Override
	public int getRole(Long idPerson) {
		return userDao.getRole(idPerson);
	}

	public ExcelExporter prepareCompteExport(List<Compte> comptes) {
		String[] columnNames = new String[] { "Login", "Rôle", "Nom & Prénom" };
		String[][] data = new String[comptes.size()][3];

		int i = 0;
		for (Compte u : comptes) {
			data[i][0] = u.getLogin();
			data[i][1] = u.getRole().getNomRole();
			data[i][2] = u.getProprietaire().getNom() + " " + u.getProprietaire().getPrenom();
			i++;
		}

		return new ExcelExporter(columnNames, data, "comptes");

	}

	// génère le mot de passe. Il se base sur Passay
	public String generatePassayPassword() {
		CharacterRule digits = new CharacterRule(EnglishCharacterData.Digit);

		PasswordGenerator passwordGenerator = new PasswordGenerator();
		String password = passwordGenerator.generatePassword(10, digits);

		return password;
	}

	@Override
	public Compte getAccountByUserName(String login) {
	

		return userDao.searchByLogin(login);
	}




}
