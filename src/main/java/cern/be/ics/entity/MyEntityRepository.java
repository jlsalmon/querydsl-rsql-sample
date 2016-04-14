package cern.be.ics.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * @author Justin Lewis Salmon
 */
public interface MyEntityRepository extends JpaRepository<MyEntity, Long>, QueryDslPredicateExecutor<MyEntity> {}
