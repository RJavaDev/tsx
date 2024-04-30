package uz.tsx.validation;

import lombok.experimental.UtilityClass;
import uz.tsx.constants.ByLoginEnumType;
import uz.tsx.constants.Characters;
import uz.tsx.entity.UserEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class UserValidation {


    public void validateUsername(UserEntity user){
        String emailOrPhone = user.getEmailOrPhone();

        if(isUserEmailAddress(emailOrPhone)){
            user.setByLogin(ByLoginEnumType.EMAIL);
        }else {
            Matcher matcherPhoneNumber = getMatcherPhoneNumber(emailOrPhone);
            if(matcherPhoneNumber.matches()){
                user.setEmailOrPhone(Characters.PHONE_NUMBER_PREFIX + getPhoneNumberByPattern(matcherPhoneNumber));
                user.setByLogin(ByLoginEnumType.PHONE_NUMBER);
            }else {
                throw new IllegalArgumentException(emailOrPhone + " Email or phone number is incorrect");
            }
        }
    }

    public boolean checkUserPhoneNumber(String phoneNumber){
        return getMatcherPhoneNumber(phoneNumber).matches();
    }

    public String getPhoneNumberByPattern(String phoneNumber) {
        Matcher matcher = getMatcherPhoneNumber(phoneNumber);
        return matcher.matches() ? getPhoneNumberByPattern(matcher): null;
    }

    public String getPhoneNumberByPattern(Matcher matcher) {
        return matcher.group(2) + matcher.group(3);
    }
    public Matcher getMatcherPhoneNumber(String phoneNumber){
        return Pattern.compile(Characters.REGEX_PHONE_NUMBER_PATTERN).matcher(phoneNumber.replaceAll("[\\-()\\s]", ""));
    }

    public boolean isUserEmailAddress(String email){
        return Pattern.compile(Characters.REGEX_EMAIL_PATTERN).matcher(email).matches();
    }

    public String getRegistrationUserPhoneNumberWithoutPrefix(String phoneNumber){
        return phoneNumber.substring(Characters.PHONE_NUMBER_PREFIX.length());
    }
}
