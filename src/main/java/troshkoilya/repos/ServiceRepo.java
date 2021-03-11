package troshkoilya.repos;

import troshkoilya.model.Service;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRepo extends CrudRepository<Service, Long> {
    List<Service> findByCost(String cost);

    Service findByName(String name);

}
