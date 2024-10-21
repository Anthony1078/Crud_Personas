package com.curso.devops.commons;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericServiceAPI<T, ID> {
	
	public Class<T> clazz;
	
	
	public GenericServiceImpl(Class<T> entityClass) {
		this.clazz = entityClass;
	}

	@Override
	@Transactional
	public T save(T entity) {
		getEm().persist(entity);
		return entity;
	}
	
	@Override
	@Transactional
	public T update(T entity) {
		getEm().merge(entity);
		return entity;
	}

	@Override
	@Transactional
	public void delete(ID id) {
		EntityManager em = getEm();
		em.remove(em.find(this.clazz, id));
	}

	@Override
	@Transactional
	public T get(ID id) {
		EntityManager em = getEm();
		return em.find(clazz, id);
	}

	@Override
	@Transactional
	public List<T> getAll() {
		EntityManager em = getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> rootEntry = cq.from(clazz);
		CriteriaQuery<T> all = cq.select(rootEntry);
		TypedQuery<T> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}
	
	public abstract EntityManager getEm();

}
