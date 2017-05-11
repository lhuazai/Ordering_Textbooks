package com.booksys.example.controller;

import com.booksys.example.bean.ServiceRes;
import com.booksys.example.model.*;
import com.booksys.example.repository.BookOrderRepository;
import com.booksys.example.repository.BookRepository;
import com.booksys.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    BookOrderRepository bookOrderRepository;
    @Autowired
    BookRepository bookRepository;

    // 订单管理
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap){
        // 找到里面的所有记录
        List<OrderEntity> orderEntities=orderRepository.findAll();
//        for (int i = 0; i <bookEntities.size() ; i++) {
//            UserEntity userEntity=userRepository.findOne(bookEntities.get(i).getAddUserId());
//            bookEntities.get(i).setUser(userEntity);
//        }
        // 将所有的记录传递给返回的jsp页面
        modelMap.addAttribute("orderList", orderEntities);

        // 返回pages目录下的jsp
        return "orderList";
    }


    // 新增班级
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServiceRes addBook(@RequestParam(name = "name",required = true) String name, HttpSession session){
        OrderEntity orderEntity=new OrderEntity();
        String time=dateFormat.format(new Date());
        orderEntity.setBuildTime(time);
        UserEntity userEntity= (UserEntity) session.getAttribute("user");
        orderEntity.setBuildUserId(userEntity.getId());
        orderEntity.setInUse(1);
        orderEntity.setName(name);
        orderRepository.saveAndFlush(orderEntity);
        return new ServiceRes("添加成功",true);
    }


    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String delClazz(@PathVariable("id") Integer id){
        orderRepository.delete(id);
        return "redirect:/order/list";
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public String clazzUsers(@PathVariable("id") Integer id,ModelMap modelMap){
        OrderEntity orderEntity=orderRepository.findOne(id);
        List<BookOrderEntity> bookOrderEntities=bookOrderRepository.findAllByOrderId(id);
        orderEntity.setCount(bookOrderEntities.size());
        for (int i = 0; i <bookOrderEntities.size() ; i++) {
            BookEntity bookEntity=bookRepository.findOne(bookOrderEntities.get(i).getBookId());
            bookOrderEntities.get(i).setBookEntity(bookEntity);
        }
        modelMap.addAttribute("order",orderEntity);
        modelMap.addAttribute("bookOrderList",bookOrderEntities);
        return "bookOrder";
    }


    @ResponseBody
    @RequestMapping(value = "/ajax/addBook",method = RequestMethod.POST)
    public ServiceRes addUser(@RequestParam(name = "bookIds",required = true) int[] bookIds,@RequestParam(name = "orderId",required = true) int orderId,
                              HttpSession session){
        List<BookOrderEntity> bookOrderEntities=bookOrderRepository.findAllByOrderId(orderId);
        for (int i = 0; i <bookIds.length ; i++) {
            if(!isTheBookInList(bookOrderEntities,bookIds[i])){
                BookOrderEntity bookOrderEntity=new BookOrderEntity();
                bookOrderEntity.setBookId(bookIds[i]);
                bookOrderEntity.setOrderId(orderId);
                bookOrderEntity.setBuildTime(dateFormat.format(new Date()));
                UserEntity userEntity= (UserEntity) session.getAttribute("user");
                bookOrderEntity.setBuildUserId(userEntity.getId());
                bookOrderRepository.save(bookOrderEntity);
            }
        }
        bookOrderRepository.flush();

        return new ServiceRes("添加成功",true);
    }

    private boolean isTheBookInList(List<BookOrderEntity> bookOrderEntities,int bookId){
        for (int i = 0; i <bookOrderEntities.size() ; i++) {
            if(bookId==bookOrderEntities.get(i).getBookId()){
                return true;
            }
        }
        return false;
    }
}
