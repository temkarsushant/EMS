package com.ems.repository.impl;

import javax.persistence.EntityManager;

import com.ems.entity.QEmployee;
import com.ems.repository.BaseRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	protected final QEmployee emloyee = QEmployee.employee;
	private final EntityManager em;
	protected final JPAQueryFactory queryFactory;

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		// TODO Auto-generated constructor stub
		this.em = em;
		this.queryFactory = new JPAQueryFactory(em);
	}

}
