package yo.antihype.team.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import yo.antihype.team.service.JwtBlacklistService;
import yo.antihype.team.util.SecurityConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtLogoutHandler implements LogoutHandler {

    private final JwtBlacklistService jwtBlacklistService;

    public JwtLogoutHandler(JwtBlacklistService jwtBlacklistService) {
        this.jwtBlacklistService = jwtBlacklistService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        if (token != null) {
            jwtBlacklistService.add(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
        }
    }
}
