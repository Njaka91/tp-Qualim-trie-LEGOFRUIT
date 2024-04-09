package mg.inclusiv.cdan8.services;

import mg.inclusiv.cdan8.entity.Users;
import mg.inclusiv.cdan8.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public Users login(Users user){
        Optional<Users> usersOptional = usersRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        return  usersOptional.orElse(null);
    }

    public void creat(Users user){
        usersRepository.save(user);
    }

    public Users getInfo(Long id){
        return  usersRepository.findById(id).orElse(null);
    }

}
