package hello.room;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.TypedQuery;

@Controller
public class RoomController {
    @PersistenceContext
    private EntityManager entityManager;
    private final RoomRepository roomRepository;
    private final EquipmentRepository equipmentRepository;

    @Inject
    public RoomController(RoomRepository roomRepository, EquipmentRepository equipmentRepository) {
        this.roomRepository = roomRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Iterable<Room>> findAllRooms(
            @RequestParam(name = "room_name", defaultValue = "") String roomName,
            @RequestParam(name = "equipment_name", defaultValue = "") String equipmentName
    ) {
        if (roomName.isEmpty() == false) {
            return ResponseEntity.ok(roomRepository.findByRoomName(roomName));
        } else if (equipmentName.isEmpty() == false) {
            final String jpql = "SELECT DISTINCT r FROM Room r LEFT JOIN FETCH r.equipments AS e " +
                    "WHERE e.equipmentName = :equipmentName";
            TypedQuery<Room> query = entityManager.createQuery(jpql, Room.class);
            query.setParameter("equipmentName", equipmentName);
            return ResponseEntity.ok(query.getResultList());
        } else {
            return ResponseEntity.ok(roomRepository.findAll());
        }
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Room> saveRoom(@RequestBody Room room) {
        return ResponseEntity.ok(roomRepository.save(room));
    }

    @RequestMapping(value = "/equipments", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Iterable<Equipment>> findAllEquipments() {
        return ResponseEntity.ok(equipmentRepository.findAll());
    }

    @RequestMapping(value = "/equipments", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Equipment> saveEquipments(
            @RequestBody Equipment equipment,
            @RequestParam(name = "room_id") Integer roomId) {

        final Room room = roomRepository.findOne(roomId);
        equipment.setRoom(room);
        return ResponseEntity.ok(equipmentRepository.save(equipment));
    }
}



