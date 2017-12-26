package yo.antihype.team.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;
import yo.antihype.team.model.JwtBlacklist;
import yo.antihype.team.repository.JwtBlacklistRepository;

@Service
public class JwtBlacklistService {

    private final JwtBlacklistRepository jwtBlacklistRepository;

    @Autowired
    public JwtBlacklistService(JwtBlacklistRepository jwtBlacklistRepository) {
        this.jwtBlacklistRepository = jwtBlacklistRepository;
    }

    @Retryable(
            value = CannotCreateTransactionException.class,
            backoff = @Backoff(delay = 5000,
                    maxDelay = 100000,
                    multiplier = 2))
    public void add(String token) {
        JwtBlacklist jwt = jwtBlacklistRepository.findByToken(token);

        if (jwt == null) {
            jwt = new JwtBlacklist();
            jwt.setToken(token);
            jwtBlacklistRepository.save(jwt);
        }
    }

    @Retryable(
            value = CannotCreateTransactionException.class,
            backoff = @Backoff(delay = 5000,
                    maxDelay = 100000,
                    multiplier = 2))
    public boolean isLogout(String token) {
        JwtBlacklist jwt = jwtBlacklistRepository.findByToken(token);
        return jwt != null;
    }

}

