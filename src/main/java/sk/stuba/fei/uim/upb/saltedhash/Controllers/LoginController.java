package sk.stuba.fei.uim.upb.saltedhash.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import sk.stuba.fei.uim.upb.saltedhash.Entities.Account;
import sk.stuba.fei.uim.upb.saltedhash.Entities.LoggedUsers;
import sk.stuba.fei.uim.upb.saltedhash.SaltCreator;
import sk.stuba.fei.uim.upb.saltedhash.Services.AccountService;
import sk.stuba.fei.uim.upb.saltedhash.Services.LogService;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    AccountService service;
    @Autowired
    LogService logService;

    @GetMapping("/")
    public String test() {
        if (!logService.check()) {
            return "index";
        } else {
            return "redirect:/logged";
        }
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @PostMapping("/register")
    public String register(@RequestParam(value = "newname", required = true) String username,
                           @RequestParam(value = "newpw", required = true) String pw) throws NoSuchAlgorithmException {
        if (service.getAccount(username).isPresent()) {
            //Username already exists
            return "redirect:/";
        } else {
            byte[] salt = SaltCreator.getSalt();
            byte[] hash = SaltCreator.getSaltedHash(pw, salt);
            Account account = new Account(username, salt, hash);
            service.save(account);
            logService.save(new LoggedUsers(username));
            System.out.println(logService.count());
            return "redirect:/logged";
        }
    }

    @PostMapping("/login")
    public RedirectView login(@RequestParam(value = "name", required = true) String username,
                              @RequestParam(value = "pw", required = true) String pw) throws NoSuchAlgorithmException {
        if (service.getAccount(username).isPresent()) {
            //Username exists
            Optional<Account> user = service.getAccount(username);
            byte[] newSaltedHash = SaltCreator.getSaltedHash(pw, user.get().getSalt());

            if (Arrays.equals(newSaltedHash, user.get().getSaltedHash())) {
                Account account = new Account(user.get().getUsername(), user.get().getSalt(), user.get().getSaltedHash());
                logService.save(new LoggedUsers(username));

                return new RedirectView("/logged");
            } else {
                try {
                    Thread.sleep( 1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                return new RedirectView("/");
            }
        } else {
            try {
                Thread.sleep( 1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            return new RedirectView("/");
        }
    }

    @GetMapping("/logged")
    public String logged() {
        if (!logService.check()) {
            return "redirect:/";
        } else {
            return "logged";
        }
    }

    @PostMapping("/dologout")
    public String doLogout() {
        logService.removeUser();
        return "redirect:/";
    }
}