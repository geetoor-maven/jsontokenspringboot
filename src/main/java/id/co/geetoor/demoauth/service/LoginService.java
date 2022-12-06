package id.co.geetoor.demoauth.service;

import id.co.geetoor.demoauth.dto.ApiResponse;
import id.co.geetoor.demoauth.dto.SignInRequest;
import id.co.geetoor.demoauth.dto.SignUpRequest;
import id.co.geetoor.demoauth.entity.User;
import id.co.geetoor.demoauth.repo.UserRepo;
import id.co.geetoor.demoauth.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtils jwtUtils;

    public ApiResponse signUp(SignUpRequest signUpRequest) {
        ApiResponse apiResponse = new ApiResponse();

        // bawa data dto ke entity user
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setGender(signUpRequest.getGender());
        user.setPhone(signUpRequest.getPhone());
        user.setPassword(signUpRequest.getPassword());

        user = userRepo.save(user);

        apiResponse.setData(user);


        return apiResponse;
    }

    public ApiResponse signIn(SignInRequest signInRequest) {
        ApiResponse apiResponse = new ApiResponse();

        User user = userRepo.findOneByEmailIgnoreCaseAndPassword(signInRequest.getEmail(), signInRequest.getPassword());

        // check apabila email dan password salah
        if (user == null){
            apiResponse.setData("User loggin failed...");
            return apiResponse;
        }

        // tampung token ke variabel baru
        String token = jwtUtils.generateJwt(user);

        Map<String, Object> data = new HashMap<>();
        data.put("accessToken", token);
        data.put("user", user);
        apiResponse.setData(data);

        return apiResponse;
    }
}
