package id.co.geetoor.demoauth.config;

import id.co.geetoor.demoauth.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("authorization");

        if (!(request.getRequestURI().contains("api/user/signin") || request.getRequestURI().contains("api/user/signup"))){
            jwtUtils.verify(auth);
        }
//        System.out.println(request.getRequestURI());
//        System.out.println("--- PRE HANDLER ----");
        return super.preHandle(request, response, handler);
    }
}
