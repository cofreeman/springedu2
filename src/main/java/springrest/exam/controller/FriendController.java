package springrest.exam.controller;

import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springrest.exam.Utils.DtoConvertor;
import springrest.exam.dto.FriendCreateDto;
import springrest.exam.dto.FriendModifyDto;
import springrest.exam.entity.Friend;
import springrest.exam.repository.FriendRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendController {

    public static final String MESSAGE = "MESSAGE";
    public static final String BAD_ID = "BAD_ID";
    public static final String BAD_NAME = "BAD_NAME";

    private final FriendRepository friendRepository;

    @PostMapping
    public ResponseEntity insertFriend(@RequestBody FriendCreateDto friendCreateDto) {
        try {
            friendRepository.save(DtoConvertor.convert(friendCreateDto));
            return ResponseEntity.status(HttpStatus.valueOf(201)).build();

        } catch (Exception e) {

            HashMap<String, Object> message = new HashMap<>();
            message.put(MESSAGE, "insert Failed");

            return ResponseEntity
                .status(HttpStatus.valueOf(500))
                .body(message);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Friend> getFriend(@PathVariable Integer id) {
        try {
            return ResponseEntity
                .status(HttpStatus.valueOf(200))
                .body(friendRepository.findById(id).get());

        } catch (Exception e) {

            HttpHeaders headers = new HttpHeaders();
            headers.add(BAD_ID, id.toString());

            return ResponseEntity
                .status(HttpStatus.valueOf(404))
                .headers(headers)
                .build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Friend>> getFriends() {
        return ResponseEntity
            .status(HttpStatus.valueOf(200))
            .body(friendRepository.findAll());
    }

    @GetMapping("/name/{fname}")
    public ResponseEntity<Friend> getFriendByFname(@PathVariable String fname) {
        try {
            return ResponseEntity
                .status(HttpStatus.valueOf(200))
                .body(friendRepository.findByFname(fname).get());

        } catch (Exception e) {

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(BAD_NAME, fname);

            return ResponseEntity
                .status(HttpStatus.valueOf(404))
                .headers(httpHeaders)
                .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFriend(FriendModifyDto friendModifyDto) {
        try {
            friendRepository.save(DtoConvertor.convert(friendModifyDto));

            return ResponseEntity
                .status(HttpStatus.valueOf(205))
                .build();

        } catch (Exception e) {
            HashMap<String, Object> body = new HashMap<>();
            body.put(MESSAGE, "업데이트에 실패했습니다.");

            return ResponseEntity
                .status(HttpStatus.valueOf(500))
                .body(body);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFriend(@PathVariable Integer id) {
        try {
            friendRepository.deleteById(id);

            return ResponseEntity
                .status(HttpStatus.valueOf(205))
                .build();

        } catch (Exception e) {
            HashMap<String, Object> body = new HashMap<>();
            body.put(MESSAGE, "삭제에 실패했습니다.");

            return ResponseEntity
                .status(HttpStatus.valueOf(500))
                .body(body);
        }
    }

}
