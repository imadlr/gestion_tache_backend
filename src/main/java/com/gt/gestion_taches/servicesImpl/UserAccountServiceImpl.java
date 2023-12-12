package com.gt.gestion_taches.servicesImpl;

import com.gt.gestion_taches.Security.JwtUtils;
import com.gt.gestion_taches.entities.UserAccount;
import com.gt.gestion_taches.repositories.UserAccountRepository;
import com.gt.gestion_taches.services.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private UserAccountRepository userAccountRepository;

    @Override
    public Map<String, String> login(Map<String, String> requestMap) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("username"),
                    requestMap.get("password")));

            UserAccount userAccount = userAccountRepository.findByUsername(requestMap.get("username"));
            Map<String, String> token = new HashMap<>();
            token.put("token", jwtUtils.generateToken(userAccount.getUsername(), userAccount.getRole()));
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

}
