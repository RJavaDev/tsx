package uz.tsx.exception.interfaces;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Type;
import uz.tsx.constants.EntityStatus;

import java.time.LocalDateTime;
import java.util.List;

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

}
