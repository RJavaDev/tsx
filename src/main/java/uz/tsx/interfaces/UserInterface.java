package uz.tsx.interfaces;

import uz.tsx.constants.EntityStatus;

import java.time.LocalDateTime;

public interface UserInterface {

    Integer getId();

    EntityStatus getStatus();

    Integer getCreated_by();

    LocalDateTime getCreated_date();

    Integer getModified_by();

    LocalDateTime getUpdated_date();

    String getFirstname();

    String getPassword();

    String getPhone_number();

    String getRole_enum_list();

    String getUsername();

    Integer getRegion_id();

    String getAddress();

    String getAttach_id();

    String getPath();

    String getType();


}
