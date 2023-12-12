package com.gt.gestion_taches.Security;

import com.gt.gestion_taches.services.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class SecurityController {

    private UserAccountService userAccountService;

    @PostMapping(path = "/login")
    public Map<String, String> login(@RequestBody Map<String, String> requestMap) {
        return userAccountService.login(requestMap);
    }

}