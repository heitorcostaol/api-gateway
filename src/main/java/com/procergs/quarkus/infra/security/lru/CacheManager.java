package com.procergs.quarkus.infra.security.lru;

import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Getter
@Setter
@Slf4j
public class CacheManager {
    private Cache<String, Set<String>> cache = new LRUCache<>(50);

    public Set<String> getPermissions(String tokenID) {
        return cache.get(tokenID).orElse(null);
    }

    public void putPermissions(String tokenID, Set<String> permissions) {
        cache.put(tokenID, permissions);
    }
}
