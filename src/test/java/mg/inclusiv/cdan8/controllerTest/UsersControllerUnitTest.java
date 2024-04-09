package mg.inclusiv.cdan8.controllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mg.inclusiv.cdan8.controller.UsersController;
import mg.inclusiv.cdan8.entity.Users;
import mg.inclusiv.cdan8.services.UsersService;

public class UsersControllerUnitTest {

    @Mock
    UsersService usersService;

    @Mock
    HttpSession session;

    @InjectMocks
    UsersController usersController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginSuccess() {
        Users user = new Users();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        Users loggedUser = new Users();
        loggedUser.setId(1L);

        when(usersService.login(user)).thenReturn(loggedUser);

        String result = usersController.login(user, session);

        assertEquals("redirect:/todo", result);
        verify(session).setAttribute("userid", loggedUser.getId());
    }

    @Test
    public void testLoginFailure() {
        Users user = new Users();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        when(usersService.login(user)).thenReturn(null);

        String result = usersController.login(user, session);

        assertEquals("redirect:/", result);
        verify(session, never()).setAttribute("userid", any());
    }

    @Test
    public void testLogout() {
        String result = usersController.logout(session);

        assertEquals("redirect:/", result);
        verify(session).removeAttribute("userid");
    }


}

