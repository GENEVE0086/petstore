package org.csu.stnb.petstore.controller;

import org.csu.stnb.petstore.domain.Account;
import org.csu.stnb.petstore.domain.Order;
import org.csu.stnb.petstore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes(value = {"account", "cart", "order"})
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/account/signonForm")
    public String signonForm(){
        return "account/signonForm";
    }

    @PostMapping("/account/signon")

    public String signon(@RequestParam("username") String username, @RequestParam("password") String password, Model model){
        Account account=accountService.getAccount(username,password);
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
    public String signout(HttpSession session, Model model){
        Account account=null;
        model.addAttribute("account",account);
        session.removeAttribute("account");
        return "catalog/main";
    }
    @GetMapping("/account/register")
    public String register(@ModelAttribute("account") Account account, Model model){
        model.addAttribute("account",account);
        return "account/NewAccountForm";
    }
    @PostMapping("/account/addNewAccount")
    public String addNewAccount(@ModelAttribute(value="account") Account account, Model model){
        System.out.println(account.getUsername());
        System.out.println(account.getPassword());
        accountService.insertAccount(account);
        model.addAttribute("account",account);
        return "catalog/main";
    }

    @GetMapping("/account/viewAccount")
    public String viewAccount(@ModelAttribute("account") Account account,
                              @ModelAttribute("order") Order order, Model model){
        model.addAttribute("account",account);
        model.addAttribute("order", order);
        return "account/EditAccountForm";
    }

    @PostMapping("/account/editAccount")
    public String editAccount(@ModelAttribute(value="account") Account account, Model model){
        accountService.updateAccount(account);
        account=accountService.getAccount(account.getUsername());
        model.addAttribute("account",account);
        return "account/EditAccountForm";
    }
}
