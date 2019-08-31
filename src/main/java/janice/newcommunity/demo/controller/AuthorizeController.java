package janice.newcommunity.demo.controller;

import janice.newcommunity.demo.datatransferobject.AccessTokenDTO;
import janice.newcommunity.demo.datatransferobject.GithubUser;
import janice.newcommunity.demo.supporter.GithubSupporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Janice on 8/31/19 4:28 PM
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubSupporter githubSupporter;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);

        String accessToken = githubSupporter.getAccessToken(accessTokenDTO);
        GithubUser user = githubSupporter.getUser(accessToken);
        System.out.println(user.getName());

        return "index";
    }
}
