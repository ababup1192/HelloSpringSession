package hello.room;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Equipment implements Serializable {
    @Id
    @GeneratedValue
    private Integer equipmentId;

    private String equipmentName;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;

    public Equipment() {
    }

    public Equipment(Integer equipmentId, String equipmentName, Room room) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.room = room;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public Room getRoom() {
        return room;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
