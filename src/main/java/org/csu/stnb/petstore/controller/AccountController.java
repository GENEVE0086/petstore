package org.csu.stnb.petstore.controller;

import org.csu.stnb.petstore.domain.Account;
import org.csu.stnb.petstore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("account")
public class AccountController {
    @Autowired
    AccountService accountService;
    Account account=null;

    @GetMapping("/account/signonForm")
    public String signonForm(){
        return "account/signonForm";
    }

    @PostMapping("/account/signon")
    public String signon(@RequestParam("username") String username, @RequestParam("password") String password, Model model){
        account=accountService.getAccount(username,password);
        if(account==null){
            String msg="Invalid username or password.  Signon failed.";
            model.addAttribute("msg",msg);
            return "account/signonForm";
        }else {
            account.setPassword(null);
            model.addAttribute("account",account);
            return "catalog/main";
        }
    }

    @GetMapping("/account/signOut")
    public String signout(Model model){
        account=null;
        model.addAttribute("account",account);
        return "catalog/main";
    }
    @GetMapping("/account/register")
    public String register(Model model){
        if(account!=null){
            model.addAttribute("account",account);
        }
        return "account/NewAccountForm";
    }
    @PostMapping("/account/addNewAccount")
    public String addNewAccount(@ModelAttribute(value="account") Account account, Model model){
        if(account!=null){
            accountService.insertAccount(account);
            model.addAttribute("account",account);
        }
        return "catalog/main";
    }

    @GetMapping("/account/viewAccount")
    public String viewAccount(Model model){
        if(account!=null){
            model.addAttribute("account",account);
        }
        return "account/EditAccountForm";
    }

    @PostMapping("/account/editAccount")
    public String editAccount(@ModelAttribute(value="account") Account account, Model model){
        if(account!=null){
            accountService.updateAccount(account);
        }
        account=accountService.getAccount(account.getUsername());
        model.addAttribute("account",account);
        return "account/EditAccountForm";
    }
}
