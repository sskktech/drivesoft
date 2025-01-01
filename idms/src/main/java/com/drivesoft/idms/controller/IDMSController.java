package com.drivesoft.idms.controller;

import com.drivesoft.idms.repository.account.AccountEntity;
import com.drivesoft.idms.service.IDMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/idms/api")
public class IDMSController {

    @Autowired
    IDMSService idmsService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${idms.baseurl}")
    String baseurl;

    @Value("${idms.username}")
    String username;

    @Value("${idms.password}")
    String password;

    @Value("${idms.institutionID}")
    String institutionID;

    //need to modify get authorization token and pass it backend and fetch retrieval
    @GetMapping("/Account/GetAccountList")
    public List<AccountEntity> getAccount(){
        return idmsService.getAccountList();
    }


        @GetMapping("/authenticate/GetUserAuthorizationToken")
        public String getToken(){
        String userTokenUri = "/api/authenticate/GetUserAuthorizationToken";

        // Define the URL with query parameters
        StringBuilder url = new StringBuilder(baseurl)
                .append(userTokenUri)
                .append("?username=").append(username)
                .append("&password=").append(password)
                .append("&InstitutionId=").append(institutionID);

        log.debug(" Url call for Token -->{} ",url);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url.toString(), String.class);

        return responseEntity.getBody();
    }







}
