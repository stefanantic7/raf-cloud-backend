package rs.raf.cloud.domain.machine.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rs.raf.cloud.domain.machine.request.SearchMachineRequest;
import rs.raf.cloud.domain.machine.entity.Machine;
import rs.raf.cloud.domain.user.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomMachineRepositoryImpl implements CustomMachineRepository{

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    public List<Machine> searchMachines(User user, SearchMachineRequest searchMachineRequest){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Machine> query = criteriaBuilder.createQuery(Machine.class);

        Root<Machine> root = query.from(Machine.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(root.get("active"), true));
        predicates.add(criteriaBuilder.equal(root.get("user"), user));

        if(searchMachineRequest.getStatus() != null) {
            predicates.add(root.get("status").in(searchMachineRequest.getStatus()));
        }
        if(searchMachineRequest.getName() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+ searchMachineRequest.getName().toLowerCase()+"%"));
        }
        if(searchMachineRequest.getDateFrom() != null && searchMachineRequest.getDateTo() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), searchMachineRequest.getDateFrom()));
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), searchMachineRequest.getDateTo()));
        }

        query.orderBy(criteriaBuilder.desc(root.get("createdAt")));

        query.select(root).where(predicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(query).getResultList();
    }
}
