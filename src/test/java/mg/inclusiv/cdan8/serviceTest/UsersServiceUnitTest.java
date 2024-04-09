package mg.inclusiv.cdan8.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mg.inclusiv.cdan8.entity.Users;
import mg.inclusiv.cdan8.repository.UsersRepository;
import mg.inclusiv.cdan8.services.UsersService;

public class UsersServiceUnitTest {

    @Mock
    UsersRepository usersRepository;

    @InjectMocks
    UsersService usersService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginSuccess() {
        Users user = new Users();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        Users userFromRepository = new Users();
        userFromRepository.setId(1L);

        when(usersRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(Optional.of(userFromRepository));

        Users result = usersService.login(user);

        assertNotNull(result);
        assertEquals(userFromRepository.getId(), result.getId());
    }

    @Test
    public void testLoginFailure() {
        Users user = new Users();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        when(usersRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(Optional.empty());

        Users result = usersService.login(user);

        assertNull(result);
    }

    @Test
    public void testCreateUser() {
        Users user = new Users();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        usersService.creat(user);

        verify(usersRepository).save(user);
    }

    @Test
    public void testGetUserInfo() {
        Long userId = 1L;
        Users userFromRepository = new Users();
        userFromRepository.setId(userId);

        when(usersRepository.findById(userId)).thenReturn(Optional.of(userFromRepository));

        Users result = usersService.getInfo(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
    }

    @Test
    public void testGetUserInfoNotFound() {
        Long userId = 1L;

        when(usersRepository.findById(userId)).thenReturn(Optional.empty());

        Users result = usersService.getInfo(userId);

        assertNull(result);
    }
}

