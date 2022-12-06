package id.co.geetoor.demoauth.controller;

import id.co.geetoor.demoauth.dto.ApiResponse;
import id.co.geetoor.demoauth.dto.SignInRequest;
import id.co.geetoor.demoauth.dto.SignUpRequest;
import id.co.geetoor.demoauth.service.LoginService;
import id.co.geetoor.demoauth.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignUpRequest signUpRequest){
        ApiResponse apiResponse = loginService.signUp(signUpRequest);

        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse> signIn(@RequestBody SignInRequest signInRequest){
        ApiResponse apiResponse = loginService.signIn(signInRequest);

        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

    @GetMapping("/private")
    public ResponseEntity<ApiResponse> privateApi() throws Exception {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setData("Private api");

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

}
