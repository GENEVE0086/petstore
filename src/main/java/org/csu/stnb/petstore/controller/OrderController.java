package org.csu.stnb.petstore.controller;

import org.csu.stnb.petstore.domain.Account;
import org.csu.stnb.petstore.domain.Cart;
import org.csu.stnb.petstore.domain.Order;
import org.csu.stnb.petstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes(value = {"account", "cart", "order"})
public class OrderController {

  @Autowired
  OrderService orderService;

  @PostMapping("/order/confirmOrder")
  public String confirmOrder(@ModelAttribute("cardType") String cardType,
                             @ModelAttribute("creditCard") String creditCard,
                             @ModelAttribute("expiryDate") String expiryDate,
                             @ModelAttribute("firstName") String firstName,
                             @ModelAttribute("lastName") String lastName,
                             @ModelAttribute("address1") String address1,
                             @ModelAttribute("address2") String address2,
                             @ModelAttribute("city") String city,
                             @ModelAttribute("state") String state,
                             @ModelAttribute("zip") String zip,
                             @ModelAttribute("country") String country,
                             @ModelAttribute("shippingAddressRequired") String shippingAddressRequired,
                             @ModelAttribute("order") Order order,
                             Model model) {
    order.setCardType(cardType);
    order.setCreditCard(creditCard);
    order.setExpiryDate(expiryDate);
    order.setBillToFirstName(firstName);
    order.setShipToFirstName(firstName);
    order.setBillToLastName(lastName);
    order.setShipToLastName(lastName);
    order.setBillAddress1(address1);
    order.setShipAddress1(address1);
    order.setBillAddress2(address2);
    order.setShipAddress2(address2);
    order.setBillCity(city);
    order.setShipCity(city);
    order.setBillState(state);
    order.setShipState(state);
    order.setBillZip(zip);
    order.setShipZip(zip);
    order.setBillCountry(country);
    order.setShipCountry(country);

    System.out.println(address1);
    System.out.println(address2);
    System.out.println(firstName);
    System.out.println(lastName);
    System.out.println(cardType);
    System.out.println(state);
    System.out.println(zip);
    System.out.println(country);

    model.addAttribute("order", order);

    if (shippingAddressRequired.equals("")) {
      return "order/ConfirmOrder";
    } else {
      return "order/ShippingForm";
    }
  }

  @GetMapping("/order/check")
  public String checkOrder(@RequestParam("orderId") int orderId, Model model) {
    Order order = orderService.getOrder(orderId);
    model.addAttribute("order", order);
    return "order/ViewOrder";
  }

  @GetMapping("/order/list")
  public String orderList(@ModelAttribute("account") Account account, Model model) {
    List<Order> orderList = orderService.getOrdersByUsername(account.getUsername());
    model.addAttribute("orderList",orderList);
    return "order/ListOrders";
  }

  @PostMapping("/order/confirmShip")
  public String confirmShip(@ModelAttribute("shipToFirstName") String shipToFirstName,
                            @ModelAttribute("shipToLastName") String shipToLastName,
                            @ModelAttribute("shipAddress1") String shipAddress1,
                            @ModelAttribute("shipAddress2") String shipAddress2,
                            @ModelAttribute("shipCity") String shipCity,
                            @ModelAttribute("shipState") String shipState,
                            @ModelAttribute("shipZip") String shipZip,
                            @ModelAttribute("shipCountry") String shipCountry,
                            @ModelAttribute("order") Order order,
                            Model model) {
    order.setShipToFirstName(shipToFirstName);
    order.setShipToLastName(shipToLastName);
    order.setShipAddress1(shipAddress1);
    order.setShipAddress2(shipAddress2);
    order.setShipCity(shipCity);
    order.setShipState(shipState);
    order.setShipZip(shipZip);
    order.setShipCountry(shipCountry);

    model.addAttribute("order", order);
    return "order/ConfirmOrder";
  }

  @GetMapping("/order/order")
  public String order(@ModelAttribute("order") Order order, Model model) {
    model.addAttribute("lineItems", order.getLineItems());
    orderService.insertOrder(order);
    Cart cart = new Cart();
    model.addAttribute("cart", cart);
    return "order/ViewOrder";
  }

  @GetMapping("/order/viewOrder")
  public String viewOrder(@ModelAttribute("cart") Cart cart,
                          @ModelAttribute("account") Account account,
                          Model model) {
    if (cart == null) {
      cart = new Cart();
      model.addAttribute("cart", cart);
    }
    if (account == null) {
      return "account/signonForm";
    } else {
      Order order = new Order();
      order.initOrder(account, cart);
      model.addAttribute("creditCardTypes",order.getCardType());
      model.addAttribute("order", order);
      return "order/NewOrderForm";
    }
  }
}
