package hello.room;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EquipmentRepository extends CrudRepository<Equipment, Integer> {
}
