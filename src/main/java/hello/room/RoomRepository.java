package hello.room;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoomRepository extends PagingAndSortingRepository<Room, Integer> {
    Iterable<Room> findByRoomName(String equipmentName);
}

