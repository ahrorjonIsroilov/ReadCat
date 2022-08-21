package ent.readon.service.user;

import ent.readon.config.jwt.JwtProvider;
import ent.readon.dto.ApiResponse;
import ent.readon.dto.user.LoginDto;
import ent.readon.dto.user.PasswordResetDto;
import ent.readon.dto.user.UserCreateDto;
import ent.readon.dto.user.UserUpdateDto;
import ent.readon.entity.user.AuthUser;
import ent.readon.enums.RoleName;
import ent.readon.mapper.AuthMapper;
import ent.readon.repo.AuthRepo;
import ent.readon.repo.RoleRepo;
import ent.readon.utils.Utils;
import ent.readon.utils.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService extends AbstractService<AuthRepo, AuthMapper> implements UserDetailsService, BaseService {
    private final Utils utils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RoleRepo roleRepo;

    @Lazy
    public AuthService(AuthRepo repo, @Qualifier("authMapperImpl") AuthMapper mapper, Utils utils, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, RoleRepo roleRepo) {
        super(repo, mapper);
        this.utils = utils;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.roleRepo = roleRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> user = repo.findByUsernameOrEmail(username, username);
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public ApiResponse login(LoginDto dto, HttpServletRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
            return new ApiResponse(jwtProvider.generateToken(dto.getUsername(), request), true);
        } catch (BadCredentialsException e) {
            return new ApiResponse(e.getLocalizedMessage(), false);
        }
    }

    public ApiResponse register(UserCreateDto dto) {
        Validator<?> validator = utils.validForRegister(dto, repo);
        if (validator.success) {
            AuthUser user = mapper.fromCreateDto(dto, passwordEncoder);
            user.setRole(roleRepo.findByName(RoleName.USER));
            repo.save(user);
            utils.sendEmailVerification(user.getEmail(), user.getEmailCode());
            return new ApiResponse("Successfully registered! Please verify your email.");
        }
        return new ApiResponse(validator.returnType, false);
    }


    public ApiResponse verifyAccount(String email, String code) {
        if (!repo.existsByEmailAndEmailCode(email, code))
            return new ApiResponse("Something went wrong! Please back to app and try again.", false);
        AuthUser user = repo.findByEmail(email);
        user.setEnabled(true);
        user.setEmailCode(null);
        repo.save(user);
        return new ApiResponse("Your email is verified!", true);
    }

    public ApiResponse forgotPassword(String email, String returnMessage) {
        if (Objects.isNull(email))
            return new ApiResponse("Enter your email", false);
        if (!repo.existsByEmail(email)) return new ApiResponse("We don't have a user with this email", false);
        AuthUser user = repo.findByEmail(email);
        String password = utils.getSixDigitPassword();
        user.setEmailCode(password);
        user.setEmailCodeExpiry(LocalDateTime.now().plusMinutes(3));
        repo.save(user);
        utils.sendResetPasswordConfirmation(email, password);
        return new ApiResponse(returnMessage, true);
    }

    public ApiResponse resetPassword(PasswordResetDto dto) {
        Validator<String> validator = utils.validForResetPassword(dto, repo);
        if (validator.success) {
            AuthUser au = repo.findByEmail(dto.getEmail());
            au.setPassword(passwordEncoder.encode(dto.getPassword()));
            au.setEmailCode(null);
            au.setEmailCodeExpiry(null);
            repo.save(au);
            return new ApiResponse("Password reset!",true);
        } else return new ApiResponse(validator.returnType, false);
    }

    public ApiResponse editUser(UserUpdateDto dto, Long id) {
        if (repo.existsByEmailAndIdIsNotLike(dto.getEmail(), id))
            return new ApiResponse("Email already taken", false);
        if (repo.existsByUsernameAndIdIsNotLike(dto.getUsername(), id))
            return new ApiResponse("Username already taken", false);
        AuthUser user = mapper.fromUpdateDto(dto, repo.getReferenceById(id));
        repo.save(user);
        return new ApiResponse("Profile updated");

    }
}
