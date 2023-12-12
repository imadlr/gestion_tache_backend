package com.gt.gestion_taches.services;

import java.util.Map;

public interface UserAccountService {
    Map<String, String> login(Map<String, String> requestMap);
}
