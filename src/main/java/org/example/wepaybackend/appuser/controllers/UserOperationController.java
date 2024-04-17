package org.example.wepaybackend.appuser.controllers;

import lombok.AllArgsConstructor;
import org.example.wepaybackend.appuser.models.AppUser;
import org.example.wepaybackend.appuser.reqests.BalanceUpdateRequest;
import org.example.wepaybackend.appuser.reqests.GetRequest;
import org.example.wepaybackend.appuser.reqests.ParticularUserUpdateRequest;
import org.example.wepaybackend.appuser.services.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class UserOperationController {

    private final AppUserService appUserService;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> loadAllUsers() {
        return ResponseEntity.ok(appUserService.loadAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<AppUser> loadUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(appUserService.loadUserById(id));
    }

    @GetMapping("/users/get")
    public ResponseEntity<Integer> getUserId(@RequestBody GetRequest request) {
        return ResponseEntity.ok(appUserService.getUserId(request));
    }
    @PutMapping("/users/particular/{id}")
    public ResponseEntity<AppUser> updateUserById(@RequestBody ParticularUserUpdateRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(appUserService.updateUserById(request, id));
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<AppUser> updateUserBalanceById(@RequestBody BalanceUpdateRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(appUserService.updateUserBalanceById(request, id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(appUserService.deleteUserById(id));
    }

    @GetMapping("/users/balance/{id}")
    public ResponseEntity<Double> getUserBalance(@PathVariable Integer id) {
        return ResponseEntity.ok(appUserService.getUserBalance(id));
    }

    @GetMapping("/users/identity/{id}")
    public ResponseEntity<String> getUserIdentity(@PathVariable Integer id) {
        return ResponseEntity.ok(appUserService.getUserIdentity(id));
    }


}
