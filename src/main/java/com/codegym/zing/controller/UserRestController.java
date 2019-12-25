package com.codegym.zing.controller;

import com.codegym.zing.model.Playlist;
import com.codegym.zing.model.Role;
import com.codegym.zing.model.User;
import com.codegym.zing.service.PlaylistService;
import com.codegym.zing.service.RoleService;
import com.codegym.zing.service.UserService;
import com.codegym.zing.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private Environment env;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/users")
    public ResponseEntity<Void> save(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = userService.findById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/{id}/playlists")
    public ResponseEntity<List<Playlist>> findAllPlaylist(@PathVariable Long id){
        User user = userService.findById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Playlist> playlists = playlistService.findAllByUser(user);
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> showAllUser() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Iterable<User> users = userService.findAll();
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user.getUsername())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        List<Role> roleList = (List<Role>) roleService.findAll();
        if (roleList.isEmpty()) {
            Role role = new Role();
            role.setId(1L);
            role.setName(ROLE_USER);
            roleService.save(role);
        }
        Role role = roleService.findRoleByName(ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
        userService.save(user);
        VerificationToken token = new VerificationToken(user);
        token.setExpiryDate(10);
        verificationTokenService.save(token);
        emailService.sendEmail(user.getEmail(), SUBJECT_REGISTER, TEXT_REGISTER + env.getProperty("confirmAccountLink") + token.getToken());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Void> confirmUserAccount(@RequestParam("token") String verificationToken) {
        VerificationToken token = verificationTokenService.findByToken(verificationToken);
        if (token != null) {
            boolean isExpired = token.isExpired();
            if (!isExpired) {
                User user = userService.findByEmail(token.getUser().getEmail());
                user.setEnabled(true);
                userService.save(user);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @RequestMapping(value = "/new-password/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<User> updatePassword(@RequestParam("token") String token, @PathVariable Long id, @RequestBody User user) {
        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        boolean isExpired = verificationToken.isExpired();
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (isExpired) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!userService.isCorrectConfirmPassword(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String newPassword = passwordEncoder.encode(user.getPassword());
        String confirmPassword = passwordEncoder.encode(user.getConfirmPassword());
        user.setPassword(newPassword);
        user.setConfirmPassword(confirmPassword);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
