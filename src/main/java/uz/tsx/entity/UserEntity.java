package uz.tsx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.tsx.constants.ByLoginEnumType;
import uz.tsx.constants.TableNames;
import uz.tsx.dto.UserDto;
import uz.tsx.entity.base.BaseEntity;
import uz.tsx.entity.role.RoleEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = TableNames.TSX_USER)
public class UserEntity extends BaseEntity implements UserDetails {

    private String firstname;

    private String lastname;

    @OneToOne
    private AttachEntity attach;

    @Column(unique = true, nullable = false)
    private String emailOrPhone;

    private String password;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private RegionEntity region;

    @Enumerated(EnumType.STRING)
    private List<RoleEnum> roleEnumList;

    @Column(name = "last_active_date")
    private Date lastActiveDate;

    @Enumerated(EnumType.STRING)
    private ByLoginEnumType byLogin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roleEnumList.forEach((rol) -> {
            roles.add(new SimpleGrantedAuthority("ROLE_" + rol.name()));
        });

        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return emailOrPhone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    /************************************************************
     * ******************** CONVERT TO DTO ***********************
     * ***********************************************************/
    @JsonIgnore
    public UserDto toDto(String... ignoreProperties) {
        return toDto(this, new UserDto(), ignoreProperties);
    }



}
