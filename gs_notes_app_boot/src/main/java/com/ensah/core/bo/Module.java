package com.ensah.core.bo;

import javax.persistence.*;
import java.util.List;

@Entity
public class Module {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idModule;

	private String titre;

	private String code;


	@OneToMany(mappedBy = "module", cascade = CascadeType.ALL, targetEntity = Element.class)
	private List<Element> elements;

	@ManyToOne
	@JoinColumn(name = "idNiveau")
	private Niveau niveau;

	public Long getIdModule() {
		return idModule;
	}

	public void setIdModule(Long idModule) {
		this.idModule = idModule;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}



}