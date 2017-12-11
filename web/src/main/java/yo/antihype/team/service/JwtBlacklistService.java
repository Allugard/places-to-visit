package yo.antihype.team.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yo.antihype.team.model.JwtBlacklist;
import yo.antihype.team.repository.JwtBlacklistRepository;

@Service
public class JwtBlacklistService {

    private final JwtBlacklistRepository jwtBlacklistRepository;

    @Autowired
    public JwtBlacklistService(JwtBlacklistRepository jwtBlacklistRepository) {
        this.jwtBlacklistRepository = jwtBlacklistRepository;
    }


    public void add(String token) {
        JwtBlacklist jwt = jwtBlacklistRepository.findByToken(token);

        if (jwt == null) {
            jwt = new JwtBlacklist();
            jwt.setToken(token);
            jwtBlacklistRepository.save(jwt);
        }
    }

    public boolean isLogout(String token) {
        JwtBlacklist jwt = jwtBlacklistRepository.findByToken(token);
        return jwt != null;
    }

}

