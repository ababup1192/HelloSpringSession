package hello.room;

import org.springframework.beans.factory.annotation.Autowired;
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
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Iterable<Room>> findAllRooms(
            @RequestParam(name = "room_name", defaultValue = "") String roomName,
            @RequestParam(name = "equipment_name", defaultValue = "") String equipmentName
    ) {
        if (roomName.isEmpty() == false) {
            return ResponseEntity.ok(roomService.findByRoomName(roomName));
        } else if (equipmentName.isEmpty() == false) {
            return ResponseEntity.ok(roomService.findByEquipmentName(equipmentName));
        } else {
            return ResponseEntity.ok(roomService.findAllRooms());
        }
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Room> saveRoom(@RequestBody Room room) {
        return ResponseEntity.ok(roomService.saveRoom(room));
    }

    @RequestMapping(value = "/equipments", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Iterable<Equipment>> findAllEquipments() {
        return ResponseEntity.ok(roomService.findAllEquipments());
    }

    @RequestMapping(value = "/equipments", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Equipment> saveEquipments(
            @RequestBody Equipment equipment,
            @RequestParam(name = "room_id") Integer roomId) {

        return ResponseEntity.ok(roomService.saveEquipment(equipment, roomId));
    }
}



