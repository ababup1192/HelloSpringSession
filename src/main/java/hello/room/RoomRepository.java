package hello.room;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoomRepository extends PagingAndSortingRepository<Room, Integer> {
}
