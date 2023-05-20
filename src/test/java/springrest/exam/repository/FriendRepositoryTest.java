package springrest.exam.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

import com.example.springedu2.Springedu2Application;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import springrest.exam.Utils.DtoConvertor;
import springrest.exam.dto.FriendCreateDto;
import springrest.exam.entity.Friend;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = FriendRepository.class))
@ContextConfiguration(classes = Springedu2Application.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(properties = {
    "spring.main.banner-mode=off"
})
public class FriendRepositoryTest {

    @Autowired
    FriendRepository friendRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void insertFriendTest() {
        String fname = "친구1";
        Integer fage = 10;
        FriendCreateDto friendCreateDto = new FriendCreateDto();
        friendCreateDto.setFname(fname);
        friendCreateDto.setFage(fage);
        Friend convertFriend = DtoConvertor.convert(friendCreateDto);

        Friend saveFriend = friendRepository.save(convertFriend);

        assertThat(saveFriend).isNotNull();
        assertThat(saveFriend.getId()).isNotNull();
        assertThat(saveFriend.getFname()).isEqualTo(fname);
        assertThat(saveFriend.getFage()).isEqualTo(fage);
    }


    @Test
    @Transactional
    @Rollback(value = false)
    void findByFnameTest() {
        String fname = "친구1";
        Integer fage = 10;
        Friend friend = new Friend();
        friend.setFname(fname);
        friend.setFage(fage);
        Friend saveFriend = friendRepository.save(friend);

        Friend foundFriend = friendRepository.findById(saveFriend.getId()).orElse(null);

        assertThat(foundFriend).isNotNull();
        assertThat(foundFriend.getFname()).isEqualTo(fname);
        assertThat(foundFriend.getFage()).isEqualTo(fage);

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void findById() {
        Friend friend = new Friend();
        friend.setFname("fname");
        friend.setFage(10);
        friendRepository.save(friend);

        Friend foundFriend = friendRepository.findById(friend.getId()).orElse(null);

        assertThat(foundFriend).isNotNull();
        assertThat(foundFriend.getFname()).isEqualTo("fname");
        assertThat(foundFriend.getFage()).isEqualTo(10);

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void findAllTest() {
        Friend friend1 = Friend.builder()
            .fname("친구1")
            .fage(20)
            .build();
        Friend friend2 = Friend.builder()
            .fname("친구2")
            .fage(25)
            .build();

        friendRepository.save(friend1);
        friendRepository.save(friend2);

        List<Friend> allFriends = friendRepository.findAll();

        assertThat(allFriends).contains(friend1, friend2);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void deleteTest() {
        Friend friend = new Friend();
        friend.setFname("친구1");
        friend.setFage(20);
        Friend savedFriend = friendRepository.save(friend);
        friendRepository.deleteById(savedFriend.getId());


        Optional<Friend> deletedFriend = friendRepository.findById(savedFriend.getId());
        assertThat(deletedFriend).isEmpty();
    }
}