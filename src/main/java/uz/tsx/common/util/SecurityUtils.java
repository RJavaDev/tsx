package uz.tsx.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import uz.tsx.entity.UserEntity;

@UtilityClass
public class SecurityUtils {
    public String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof UsernamePasswordAuthenticationToken token) {
            if(token.getPrincipal() instanceof UserDetails userDetails){
                return userDetails.getUsername();
            }
        }
        return null;
    }

    public UserEntity getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof UsernamePasswordAuthenticationToken token) {
            if(token.getPrincipal() instanceof UserEntity user){
                return user;
            }
        }
        return null;
    }

    public Long getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof UsernamePasswordAuthenticationToken token) {

            if(token.getPrincipal() instanceof UserEntity userDetails){
                return userDetails.getId();
            }
        }
        return null;
    }
}
