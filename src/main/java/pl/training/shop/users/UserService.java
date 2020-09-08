package pl.training.shop.users;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.training.shop.common.PagedResult;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getById(Long id){
        return userRepository.findById(id);
    }

    public List<User> getByName(String name){
        return userRepository.findByName(name);
    }

    public PagedResult<User> getAll(int pageNumber, int pageSize){
        Page<User> all = userRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new PagedResult<>(all.getContent(), pageNumber, all.getTotalPages());
    }
}
