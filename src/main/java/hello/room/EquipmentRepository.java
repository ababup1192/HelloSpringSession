package hello.room;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EquipmentRepository extends PagingAndSortingRepository<Equipment, Integer> {
}
