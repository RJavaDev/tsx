package uz.tsx.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uz.tsx.constants.TableNames;
import uz.tsx.entity.base.BaseServerModifierEntity;

import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = TableNames.TSX_USER)
public class UsersEntity extends BaseServerModifierEntity implements UserDetails {

    @Column(unique = true, nullable = false)
    private String gmail;

    private String firstname;

    private String lastname;

    @OneToOne
    private AttachEntity attach;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    private String password;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return gmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
