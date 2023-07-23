package com.productcatalog.dao;

import com.productcatalog.entity.Address;
import com.productcatalog.entity.Customer;
import com.productcatalog.mapping.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDaoImpl implements  CustomerDao{

    @Autowired
    private EntityManager em;
    @Autowired
    private EntityMapper mapper;
    @Override
    public List<Customer> fetchCustomerByLastnameAndCityAndState(String lastname, String city, String state) {
        CriteriaBuilder criteriaBuilder =em.getCriteriaBuilder();
        List<Predicate> predicates= new ArrayList<Predicate>();
        CriteriaQuery customerCriteria = criteriaBuilder.createQuery(Customer.class);
          Root<Customer> root = customerCriteria.from(Customer.class);
        Predicate lastnamePredicate=null;
        Predicate cityPredicate = null;
        Predicate statePredicate = null;
        if(lastname !=null) {
                lastnamePredicate = criteriaBuilder.equal(root.get("lastname"),lastname);
               predicates.add(lastnamePredicate);
           }

        if(city !=null && state !=null){
            Join<Customer,Address> addressJoin= root.join("address");
            cityPredicate =criteriaBuilder.equal(addressJoin.get("city"),city);
            statePredicate =criteriaBuilder.equal(addressJoin.get("state"),state);
            predicates.add(statePredicate);
            predicates.add(cityPredicate);
        } else if(city !=null ){
               Join<Customer,Address> addressJoin= root.join("address");
                cityPredicate =criteriaBuilder.equal(addressJoin.get("city"),city);
               predicates.add(cityPredicate);
           }
        else {
            Join<Customer,Address> addressJoin= root.join("address");
            statePredicate =criteriaBuilder.equal(addressJoin.get("state"),state);
            predicates.add(statePredicate);
        }

         customerCriteria.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Customer>  typedQuery= em.createQuery(customerCriteria);
        return typedQuery.getResultList();
    }
}
