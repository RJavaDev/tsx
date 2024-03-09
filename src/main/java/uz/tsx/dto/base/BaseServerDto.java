package uz.tsx.dto.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.tsx.constants.EntityStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseServerDto {
    private Long id;
    private EntityStatus status;
}
