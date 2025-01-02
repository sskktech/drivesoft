package com.drivesoft.idms.controller;

import com.drivesoft.idms.repository.account.AccountModel;
import com.drivesoft.idms.repository.user.User;
import com.drivesoft.idms.service.IDMSService;
import com.drivesoft.idms.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/idms/api")
public class IDMSController {

    @Autowired
    IDMSService idmsService;

    @Autowired
    SecurityService securityService;

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

    @GetMapping("/Account/{AccountID}")
    public AccountModel getAccountDetails(@RequestHeader("Authorization") String token, @PathVariable Integer AccountID) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            throw new SecurityException(" Unauthorized access ");
        }
        // Extract the token (Remove 'Bearer ' prefix if present)
        if (authentication != null && authentication.isAuthenticated()) {
            String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;

            //user details validated against logged in user deatils
            User user;
            if (!securityService.validateToken(jwtToken, user)) {
                throw new RuntimeException(" Unauthorized access ");
            }

            // Fetch the account details from the database
            AccountModel account = new AccountModel();
            account.setAccountID(AccountID);
            AccountModel fetchedAccount = idmsService.getAccount(account);
            if (Optional.of(fetchedAccount).isEmpty()) {
                log.error(" Account not found. ");
                throw new RuntimeException(" Account not found. ");
            }
            return fetchedAccount;
        }
    }

    @GetMapping("/Account/GetAccountList")
    public List<AccountModel> getAccount() {
        return idmsService.getAccountList();
    }

    @PostMapping("/Create/Account")
    public void saveOrUpdate(@RequestBody AccountModel account) {
        idmsService.saveOrUpdate(account);
    }

    @GetMapping("/authenticate/GetUserAuthorizationToken")
    public String getToken(@RequestBody User user) {
        String userTokenUri = "/api/authenticate/GetUserAuthorizationToken";

        // Define the URL with query parameters
        StringBuilder url = new StringBuilder(baseurl)
                .append(userTokenUri)
                .append("?username=").append(username)
                .append("&password=").append(password)
                .append("&InstitutionId=").append(institutionID);

        log.debug(" Url call for Token -->{} ", url);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url.toString(), String.class);

        return responseEntity.getBody();
    }
}
