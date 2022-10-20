package ent.readcat.controller.auth;

import ent.readcat.controller.AbstractController;
import ent.readcat.dto.ApiResponse;
import ent.readcat.dto.user.LoginDto;
import ent.readcat.dto.user.PasswordResetDto;
import ent.readcat.dto.user.UserCreateDto;
import ent.readcat.service.user.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController extends AbstractController<AuthService> {
    public AuthController(AuthService service) {
        super(service);
    }


    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody UserCreateDto dto) {
        ApiResponse response = service.register(dto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PostMapping(value = "/login")
    public HttpEntity<?> login(HttpServletRequest request, @RequestBody LoginDto dto) {
        ApiResponse response = service.login(dto, request);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping(value = "/confirmAccount")
    public HttpEntity<?> verifyEmail(@RequestParam String email, @RequestParam String code) {
        ApiResponse response = service.verifyAccount(email, code);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping(value = "/forgotPassword")
    public HttpEntity<?> forgotPassword(@RequestParam String email) {
        ApiResponse response = service.forgotPassword(email, "Reset password code sent! Check your email.");
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PostMapping(value = "/resetPassword")
    public HttpEntity<?> resetPassword(@RequestBody PasswordResetDto dto) {
        ApiResponse response = service.resetPassword(dto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping(value = "/resendResetPassword")
    public HttpEntity<ApiResponse> resendPassword(@RequestParam(name = "email") String email) {
        ApiResponse response = service.forgotPassword(email, "Email resent");
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }
}
