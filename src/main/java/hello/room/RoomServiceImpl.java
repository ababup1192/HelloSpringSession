package hello.room;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Service
public class RoomServiceImpl implements RoomService {
    @PersistenceContext
    private EntityManager entityManager;
    private final RoomRepository roomRepository;
    private final EquipmentRepository equipmentRepository;

    @Inject
    public RoomServiceImpl(RoomRepository roomRepository, EquipmentRepository equipmentRepository) {
        this.roomRepository = roomRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Iterable<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Iterable<Equipment> findAllEquipments() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment saveEquipment(Equipment equipment, Integer roomId) {
        final Room room = roomRepository.findOne(roomId);
        equipment.setRoom(room);
        return equipmentRepository.save(equipment);
    }

    @Override
    public Iterable<Room> findByRoomName(String roomName) {
        return roomRepository.findByRoomName(roomName);
    }

    @Override
    public Iterable<Room> findByEquipmentName(String equipmentName) {
        final String jpql = "SELECT DISTINCT r FROM Room r LEFT JOIN FETCH r.equipments AS e " +
                "WHERE e.equipmentName = :equipmentName";
        TypedQuery<Room> query = entityManager.createQuery(jpql, Room.class);
        query.setParameter("equipmentName", equipmentName);
        return query.getResultList();
    }
}
