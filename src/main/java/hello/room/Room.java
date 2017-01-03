package hello.room;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
public class Room implements Serializable {
    @Id
    @GeneratedValue
    private Integer roomId;

    @NotNull
    private String roomName;

    @NotNull
    private Integer capacity;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Equipment> equipments;

    public Room() {
    }

    public Room(Integer roomId, String roomName, Integer capacity, List<Equipment> equipments) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
        this.equipments = equipments;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }
}
