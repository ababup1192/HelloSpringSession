package hello.room;

import hello.session.Person;
import hello.session.PersonRepository;
import hello.session.SessionID;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@Controller
public class RoomController {
    private final RoomRepository roomRepository;
    private final EquipmentRepository equipmentRepository;

    @Inject
    public RoomController(RoomRepository roomRepository, EquipmentRepository equipmentRepository) {
        this.roomRepository = roomRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Iterable<Room>> findAllRooms() {
        return ResponseEntity.ok(roomRepository.findAll());
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



