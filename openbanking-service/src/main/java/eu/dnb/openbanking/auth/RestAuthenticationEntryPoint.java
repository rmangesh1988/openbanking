package eu.dnb.openbanking.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.dnb.openbanking.domain.vo.ErrorDetail;
import eu.dnb.openbanking.domain.vo.ExceptionResponse;
import eu.dnb.openbanking.exception.DNBError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by rmang on 17-03-2018.
 */
public class RestAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
        ExceptionResponse exceptionResponse = buildUnauthorizedExceptionResponse();
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getOutputStream().print(objectMapper.writeValueAsString(exceptionResponse));
    }

    private ExceptionResponse buildUnauthorizedExceptionResponse() {
        return new ExceptionResponse(DNBError.UNAUTHORIZED.getErrorCode(),
                DNBError.UNAUTHORIZED.getErrorMessage(), DNBError.UNAUTHORIZED.getUriString(),
                Arrays.asList(new ErrorDetail(DNBError.UNAUTHORIZED.getErrorCode(), null, "You are unauthorized!")));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("openbanking");
        super.afterPropertiesSet();
    }
}
