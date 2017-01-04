package hello.room;

public interface RoomService {
    Iterable<Room> findAllRooms();
    Room saveRoom(Room room);
    Iterable<Equipment> findAllEquipments();
    Equipment saveEquipment(Equipment equipment, Integer roomId);
    Iterable<Room> findByRoomName(String roomName);
    Iterable<Room> findByEquipmentName(String equipmentName);
}


