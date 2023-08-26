package kg.attractor.shareHub.service;

import kg.attractor.shareHub.dao.UserDao;
import kg.attractor.shareHub.dto.UserDto;
import kg.attractor.shareHub.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder encoder;


    public Optional<User> getUserByEmail(String email) {
        log.info("Gol user by email:" + email);
        Optional<User> mayBeUser = userDao.getUserByEmail(email);
        return mayBeUser;
    }

    public String isUserExist(String email) {
        try {
            Optional<User> user = userDao.getUserByEmail(email);
            if (user.isPresent()) {
                log.error("User:" + email + "  exists!");
                return "1";
            } else {
                log.info("User:" + email + " does not exist!");
                return "0";
            }
        } catch (EmptyResultDataAccessException e) {
            log.error("Empty Result!");
            return "error";
        }
    }

    public int save(UserDto userDto) {
        log.info("The user:" + userDto.getEmail() + " is saved!");
        return userDao.save(User.builder()
                .accountName(userDto.getAccountName())
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .password(encoder.encode(userDto.getPassword()))
                .enabled(true)
                .roleId(1)
                .build());

    }


    public UserDto mapToUserDto(User user) {
        if (user != null) {
            return UserDto.builder()
                    .id(user.getId())
                    .accountName(user.getAccountName())
                    .fullName(user.getFullName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();
        } else {
            return null;
        }
    }
}
