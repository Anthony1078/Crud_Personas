package com.curso.devops.service.impl;

import com.curso.devops.commons.GenericServiceImpl;
import com.curso.devops.model.Persona;
import com.curso.devops.service.api.PersonaServiceApi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class PersonaServiceImpl extends GenericServiceImpl<Persona, Integer> implements PersonaServiceApi {
	
	public PersonaServiceImpl() {
		super(Persona.class);
	}
	
	@Inject
	EntityManager em;

	@Override
	public EntityManager getEm() {
		return em;
	}

}
