package hello.room;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Iterable<Room> findByRoomName(String equipmentName);
}
