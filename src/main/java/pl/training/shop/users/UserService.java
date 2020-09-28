package pl.training.shop.users;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.training.shop.common.PagedResult;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public User add(User user) {
        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public PagedResult<User> getAll(int pageNumber, int pageSize) {
        Page<User> all = userRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new PagedResult<>(all.getContent(), pageNumber, all.getTotalPages());
    }

    public PagedResult<User> getBySurname(String surnameFragment, int pageNumber, int pageSize) {
        Page<User> all = userRepository.findBySurnameContaining(surnameFragment, PageRequest.of(pageNumber, pageSize));
        return new PagedResult<>(all.getContent(), pageNumber, all.getTotalPages());
    }

}
