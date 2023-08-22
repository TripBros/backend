package com.example.tripbros.controller;

import com.example.tripbros.config.jwt.TokenProvider;
import com.example.tripbros.config.oauth.OauthProperties;
import com.example.tripbros.domain.RefreshToken;
import com.example.tripbros.domain.User;
import com.example.tripbros.domain.enumerate.Gender;
import com.example.tripbros.domain.enumerate.Role;
import com.example.tripbros.dto.CreateOauthUserRequest;
import com.example.tripbros.dto.CreateUserRequest;
import com.example.tripbros.repository.RefreshTokenRepository;
import com.example.tripbros.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private OauthProperties oauthProperties;
    @Autowired
    private UserService userService;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    private final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private final String KAKAO_INFO_URL = "https://kapi.kakao.com/v2/user/me";

    public static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);
    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);


    @GetMapping("/login/kakao")
    @CrossOrigin(origins = "http://172.20.10.2:19006")
    public ResponseEntity<Object> kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        String kakaoAccessToken = getKakaoAccessToken(code); //인가코드로 카카오 엑세스 토큰 받아오기
        CreateOauthUserRequest request = getKakaoInfo(kakaoAccessToken); //엑세스 토큰으로 카카오 사용자 정보 받아오기
        if(userExists(request.getEmail())) { //이미 가입된 회원
            Optional<User> userOptional = userService.findByEmail(request.getEmail());
            User user = userOptional.get();
            HttpHeaders headers = getLoginHeader(user);

            return ResponseEntity.ok().headers(headers).body("login");
            //로그인 처리하기
        } else { //신규 회원
            return ResponseEntity.ok(request);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody CreateUserRequest request) { //이미 있는 회원인지 확인해야됨
        User user = User.builder()
                .email(request.getEmail())
                .gender(Gender.valueOf(request.getGender().toUpperCase()))
                .age(request.getAge())
                .role(Role.USER)
                .nickname(request.getNickname())
                .travelStyle(request.getTravelStyle())
                .build();
        Long userId = userService.save(user);
        HttpHeaders headers = getLoginHeader(userService.findById(userId));
        return ResponseEntity.ok().headers(headers).body("OK");
    }

    private HttpHeaders getLoginHeader(User user) {
        //리프레시 토큰 생성 -> 저장 -> 쿠키에 저장
        String refreshToken = tokenProvider.generateToken(user, REFRESH_TOKEN_DURATION);
        saveRefreshToken(user.getId(), refreshToken);

        //액세스 토큰 생성 -> 패스에 액세스 토큰을 추가
        String accessToken = tokenProvider.generateToken(user, ACCESS_TOKEN_DURATION);

        HttpHeaders headers = new HttpHeaders();
        headers.set("RefreshToken", "Bearer " + refreshToken);
        headers.set("Authorization", "Bearer " + accessToken);
        return headers;
    }

    //성생된 리프레시 토큰을 전달받아 데이터베이스에 저장
    private void saveRefreshToken(Long userId, String newRefreshToken) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId)
                .map(entity -> entity.update(newRefreshToken))
                .orElse(new RefreshToken(userId, newRefreshToken));

        refreshTokenRepository.save(refreshToken);
    }

    private boolean userExists(String email) {
        Optional<User> userOptional = userService.findByEmail(email);
        if(userOptional.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    //카카오 엑세스 토큰으로 사용자 정보 받아오기
    private CreateOauthUserRequest getKakaoInfo(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(KAKAO_INFO_URL, HttpMethod.GET, entity, String.class);
        CreateOauthUserRequest userRequest = new CreateOauthUserRequest();

        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            String email = jsonNode.path("kakao_account").path("email").asText();
            String[] ageRange = jsonNode.path("kakao_account").path("age_range").asText().split("~");
            String gender = jsonNode.path("kakao_account").path("gender").asText();
            Integer ageStart = Integer.parseInt(ageRange[0]);
            Integer ageEnd = Integer.parseInt(ageRange[1]);

            System.out.println("= = = " + email + " " + gender + " " + ageStart + " " + ageEnd);
            userRequest.setEmail(email);
            userRequest.setGender(gender);
            userRequest.setAgeStart(ageStart);
            userRequest.setAgeEnd(ageEnd);
            return userRequest;
        }
        return null;
    }

    private String getKakaoAccessToken(String code) throws JsonProcessingException { //인가코드로 카카오 엑세스 토큰 받아오기
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("grant_type", "authorization_code");
        data.add("client_id", oauthProperties.getKakaoClientId() );
        data.add("client_secret", oauthProperties.getKakaoClientSecret());
        data.add("code", code); // 카카오로부터 받은 인가 코드
        data.add("redirect_uri", oauthProperties.getKakaoRedirectUri()); // 카카오로부터 등록한 리다이렉트 URI

        // 요청 객체 생성
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(data, headers);

        // RestTemplate를 이용하여 POST 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                KAKAO_TOKEN_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseBody = responseEntity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            String accessToken = jsonNode.get("access_token").asText();

            return accessToken;
        } else {
            return null; //나중에 제대로 햐
        }
    }
}




