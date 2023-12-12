package com.gt.gestion_taches.Security;

import com.gt.gestion_taches.entities.UserAccount;
import com.gt.gestion_taches.repositories.UserAccountRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        if (userAccount != null) {
            return new UserDetailsImpl(userAccount);
        }
        throw new UsernameNotFoundException("Utilisateur Non Trouvé");
    }
}
