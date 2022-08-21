package ent.readon.entity.user;

import ent.readon.controller.AbstractController;
import ent.readon.dto.ApiResponse;
import ent.readon.dto.user.UserUpdateDto;
import ent.readon.service.user.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends AbstractController<AuthService> {

    public UserController(AuthService service) {
        super(service);
    }

    @PostMapping("/edit")
    public HttpEntity<ApiResponse> editUser(@RequestBody UserUpdateDto dto, @RequestParam Long id) {
        ApiResponse response = service.editUser(dto, id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }
}
