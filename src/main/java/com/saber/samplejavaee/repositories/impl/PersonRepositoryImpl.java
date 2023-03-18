package com.saber.samplejavaee.repositories.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saber.samplejavaee.model.query.Person;
import com.saber.samplejavaee.model.query.QPerson;
import com.saber.samplejavaee.repositories.PersonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;
    public static final QPerson person = new QPerson("person");

    public PersonRepositoryImpl() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sample_javaEE");
        entityManager = factory.createEntityManager();
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Person> findAll() {
        return queryFactory.selectFrom(person).fetch();
    }

    @Override
    public Person findByNationalCode(String nationalCode) {
        return queryFactory.selectFrom(person)
                .where(person.nationalCode.eq(nationalCode))
                .fetchOne();
    }

    @Override
    public Person findById(Long id) {
        return queryFactory.selectFrom(person)
                .where(person.id.eq(id))
                .fetchOne();
    }

    @Override
    public Person save(Person person) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(person);
        entityManager.flush();
        transaction.commit();
        return person;
    }

    @Override
    public Person update(Person person) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(person);
        entityManager.flush();
        transaction.commit();
        return person;
    }

    @Override
    public Boolean deleteById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Person person = findById(id);
        boolean result = false;
        if (person != null) {
            entityManager.remove(person);
            entityManager.flush();
            transaction.commit();
            result = true;
        }
        return result;
    }
}
