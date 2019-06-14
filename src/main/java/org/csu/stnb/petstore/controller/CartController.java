package org.csu.stnb.petstore.controller;

import org.csu.stnb.petstore.domain.Account;
import org.csu.stnb.petstore.domain.Cart;
import org.csu.stnb.petstore.domain.CartItem;
import org.csu.stnb.petstore.domain.Item;
import org.csu.stnb.petstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Iterator;

@Controller
@SessionAttributes(value = {"account", "cart", "order"})
public class CartController {

    @Autowired
    private CatalogService catalogService;

    @Autowired
    HttpSession session;

//    private Cart cart = new Cart();

    @GetMapping("/cart/view")
    public String viewCart( Model model){
        Account account= (Account) session.getAttribute("account");
        Cart cart = (Cart) session.getAttribute("cart");
        System.out.println(cart);
        if(cart == null){
            cart = new Cart();
        }
        model.addAttribute("account", account);
        model.addAttribute("cart", cart);
        return "cart/cart";
    }

    @GetMapping("/cart/addItem")
    public String addItemToCart(@RequestParam("workingItemId")String workingItemId,
                                Model model){
        Cart cart = (Cart) session.getAttribute("cart");
//        Cart cart = new Cart();
        if(cart==null){
            cart = new Cart();
        }
        if(cart.containsItemId(workingItemId)){
            cart.incrementQuantityByItemId(workingItemId);
        }else {
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item,isInStock);
        }
        model.addAttribute("cart",cart);
        session.setAttribute("cart",cart);
        return "cart/cart";
    }

    @PostMapping("/cart/update")
    public String updateCart(HttpServletRequest request,
                             Model model){
        Cart cart = (Cart) session.getAttribute("cart");
//        Cart cart = new Cart();
        Iterator<CartItem> cartItems = cart.getAllCartItems();
        while (cartItems.hasNext()) {
            CartItem cartItem = cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                int quantity = Integer.parseInt((String)request.getParameter(itemId));
                cart.setQuantityByItemId(itemId, quantity);
                if (quantity < 1) {
                    cartItems.remove();
                }
            } catch (Exception e) {
                //ignore parse exceptions on purpose
            }
        }
//        model.addAttribute("account", account);
        model.addAttribute("cart",cart);
        session.setAttribute("cart",cart);
        return "cart/cart";
    }

    @GetMapping("/cart/removeItem")
    public String removeItemFromCart(@RequestParam("cartItemId") String cartItemId,
                                     @ModelAttribute("account") Account account,
                                     Model model){
//        Cart cart = new Cart();
        Cart cart = (Cart) session.getAttribute("cart");
        model.addAttribute("account", account);
        Item item=cart.removeItemById(cartItemId);
        if (item==null){
            model.addAttribute("message","Attempted to remove null CartItem from Cart.");
            model.addAttribute("cart",cart);
            session.setAttribute("cart",cart);
            return "common/error";
        }else {
            model.addAttribute("cart",cart);
            session.setAttribute("cart",cart);
            return "cart/Cart";
        }

    }
}
