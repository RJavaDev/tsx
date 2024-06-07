package uz.tsx.bot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.tsx.bot.enums.StateEnum;
import uz.tsx.constants.TableNames;
import uz.tsx.entity.UserEntity;
import uz.tsx.entity.base.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = TableNames.TSX_USER_BOT)
public class UserBotEntity extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String chatId;
    private String language;

    @Enumerated(EnumType.STRING)
    private StateEnum state;
    @OneToOne
    UserEntity userEntity;

}
