package com.example.HospitalManagement.mapper;

import com.example.HospitalManagement.Entity.Room;
import com.example.HospitalManagement.dto.RoomDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-23T06:23:42-0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomDto toDto(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomDto roomDto = new RoomDto();

        roomDto.setPatientIds( mapPatientsToPatientIds( room.getPatients() ) );
        roomDto.setRoomNumber( room.getRoomNumber() );
        roomDto.setType( room.getType() );
        roomDto.setAvailable( room.isAvailable() );

        return roomDto;
    }

    @Override
    public Room toEntity(RoomDto roomDto) {
        if ( roomDto == null ) {
            return null;
        }

        Room room = new Room();

        room.setRoomNumber( roomDto.getRoomNumber() );
        room.setType( roomDto.getType() );
        room.setAvailable( roomDto.isAvailable() );

        return room;
    }

    @Override
    public void updateEntityFromDTO(RoomDto roomDto, Room room) {
        if ( roomDto == null ) {
            return;
        }

        room.setRoomNumber( roomDto.getRoomNumber() );
        room.setType( roomDto.getType() );
        room.setAvailable( roomDto.isAvailable() );
    }
}
