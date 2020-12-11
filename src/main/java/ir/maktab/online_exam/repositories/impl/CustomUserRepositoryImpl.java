package ir.maktab.online_exam.repositories.impl;

import ir.maktab.online_exam.domains.DTO.UserSearchDTO;
import ir.maktab.online_exam.domains.Manager;
import ir.maktab.online_exam.domains.Student;
import ir.maktab.online_exam.domains.Teacher;
import ir.maktab.online_exam.domains.User;
import ir.maktab.online_exam.repositories.CustomUserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

//TODO convert all custom repositories to custom user repository
public class CustomUserRepositoryImpl implements CustomUserRepository {
    //TODO what is persistence context
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> userAdvanceSearch(UserSearchDTO userSearchDTO) {
        List<User> result = new ArrayList<>();
        userSearchDTO.getUserTypes().forEach(
                type ->{
                    switch (type){
                        case "M_": result.addAll(doUserAdvanceSearch(userSearchDTO, Manager.class));break;
                        case "T_": result.addAll(doUserAdvanceSearch(userSearchDTO, Teacher.class));break;
                        case "S_": result.addAll(doUserAdvanceSearch(userSearchDTO, Student.class));break;
                    }
                }
        );
        return result;
    }

    private <E extends User> List<User> doUserAdvanceSearch(UserSearchDTO userSearchDTO, Class<E> destinationClass){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query = criteriaBuilder.createQuery(destinationClass);
        Root<E> root = query.from(destinationClass);
        List<Predicate> predicates = new ArrayList<>();
        setAttributeAdvanceSearch(userSearchDTO, predicates, criteriaBuilder, root);
        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        return (List<User>) entityManager.createQuery(query).getResultList();
    }

    private <E extends User> void setAttributeAdvanceSearch(UserSearchDTO userSearchDTO, List<Predicate> predicates,
                                                            CriteriaBuilder criteriaBuilder, Root<E> root){
        setUsername(userSearchDTO.getUsername(), predicates, criteriaBuilder, root);
        setFirstName(userSearchDTO.getFirstName(), predicates, criteriaBuilder, root);
        setLastName(userSearchDTO.getLastName(), predicates, criteriaBuilder, root);
        setEmail(userSearchDTO.getEmail(), predicates, criteriaBuilder, root);
    }

    private <E extends User> void setEmail(String email, List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Root<E> root) {
        if(email != null && !email.isEmpty())
            predicates.add(
                    criteriaBuilder.like(root.get("email"), "%" + email.trim() + "%")
            );
    }

    private <E extends User> void setLastName(String lastName, List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Root<E> root) {
        if(lastName != null && !lastName.isEmpty())
            predicates.add(
                    criteriaBuilder.like(root.get("lastName"), "%" + lastName.trim() + "%")
            );
    }

    private <E extends User> void setFirstName(String firstName, List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Root<E> root) {
        if(firstName != null && !firstName.isEmpty())
            predicates.add(
                    criteriaBuilder.like(root.get("firstName"), "%" + firstName.trim() + "%")
            );
    }

    private <E extends User> void setUsername(String username, List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Root<E> root) {
        if(username != null && !username.isEmpty())
            predicates.add(
                    criteriaBuilder.like(root.get("username"), "%" + username.trim() + "%")
            );
    }
}
