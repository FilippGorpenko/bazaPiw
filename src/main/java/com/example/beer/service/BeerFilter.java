package com.example.beer.service;

import com.example.beer.mapping.Beer;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class BeerFilter implements Specification<Beer> {

    private final FilterCriteria filterCriteria;

    @Override
    public Predicate toPredicate(Root<Beer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return buildLikePredicate(root, criteriaBuilder);
    }

    private Predicate buildLikePredicate(Root<Beer> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(criteriaBuilder.lower(root.get(filterCriteria.getKey())),
                "%" + filterCriteria.getValue().toString().toLowerCase() + "%");
    }
}
