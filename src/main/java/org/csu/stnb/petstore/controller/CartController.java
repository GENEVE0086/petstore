package org.csu.stnb.petstore.controller;

import org.csu.stnb.petstore.domain.Cart;
import org.csu.stnb.petstore.domain.CartItem;
import org.csu.stnb.petstore.domain.Item;
import org.csu.stnb.petstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

@Controller
@SessionAttributes("cart")
public class CartController {

    @Autowired
    private CatalogService catalogService;

    Cart cart = new Cart();

    @GetMapping("/viewCart")
    public String viewCart(Model model){
        if(cart == null){
            cart = new Cart();
        }
        model.addAttribute("cart",cart);
        return "cart/cart";
    }

    @GetMapping("/addItemToCart")
    public String addItemToCart(@RequestParam("workingItemId")String workingItemId, Model model){
//        if(cart==null){
//            cart = new Cart();
//        }
        if(cart.containsItemId(workingItemId)){
            cart.incrementQuantityByItemId(workingItemId);
        }else {
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item,isInStock);
        }
        model.addAttribute("cart",cart);
        return "cart/cart";
    }

    @PostMapping("/updateCart")
    public String updateCart(HttpServletRequest request, Model model){
        Iterator<CartItem> cartItems = cart.getAllCartItems();
        while (cartItems.hasNext()) {
            CartItem cartItem = (CartItem) cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                int quantity = Integer.parseInt((String)request.getParameter(itemId));
                cart.setQuantityByItemId(itemId, quantity);
                if (quantity < 1) {
                    cartItems.remove();
                }
            } catch (Exception e) {
                System.out.println("error");
            }
        }
        model.addAttribute("cart",cart);
        return "cart/cart";
    }

    @GetMapping("/removeItemFromCart")
    public String removeItemFromCart(@RequestParam("cartItemId")String cartItemId,Model model){

        Item item=cart.removeItemById(cartItemId);
        if (item==null){
            model.addAttribute("message","Attempted to remove null CartItem from Cart.");
            model.addAttribute("cart",cart);
            return "common/error";
        }else {
            model.addAttribute("cart",cart);
            return "cart/Cart";
        }
    }
}
