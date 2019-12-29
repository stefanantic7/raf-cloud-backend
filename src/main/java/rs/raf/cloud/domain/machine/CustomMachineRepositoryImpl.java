package rs.raf.cloud.domain.machine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomMachineRepositoryImpl implements CustomMachineRepository{

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    public List<Machine> searchMachines(MachineSearchQuery machineSearchQuery){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Machine> query = criteriaBuilder.createQuery(Machine.class);

        Root<Machine> root = query.from(Machine.class);
        List<Predicate> predicates = new ArrayList<>();

        if(machineSearchQuery.getStatus() != null) {
            predicates.add(criteriaBuilder.equal(root.get("status"), machineSearchQuery.getStatus()));
        }
        if(machineSearchQuery.getName() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+machineSearchQuery.getName().toLowerCase()+"%"));
        }
        if(machineSearchQuery.getDateFrom() != null && machineSearchQuery.getDateTo() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), machineSearchQuery.getDateFrom()));
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), machineSearchQuery.getDateTo()));
        }

        query.select(root).where(predicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(query).getResultList();
    }
}
