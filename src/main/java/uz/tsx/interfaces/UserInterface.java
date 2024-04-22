package uz.tsx.interfaces;

import uz.tsx.constants.EntityStatus;

import java.time.LocalDateTime;

public interface UserInterface {

    Long getId();

    EntityStatus getStatus();

    Long getCreated_by();

    LocalDateTime getCreated_date();

    Long getModified_by();

    LocalDateTime getUpdated_date();

    String getFirstname();

    String getLastname();

    String getPassword();

    String getRole_enum_list();

    String getEmail_or_phone();

    Integer getRegion_id();

    String getAddress();

    String getAttach_id();

    String getPath();

    String getType();

    Integer getCount_unread();
}
