package com.booksys.example.controller;

import com.booksys.example.model.BookEntity;
import com.booksys.example.model.UserEntity;
import com.booksys.example.repository.BookRepository;
import com.booksys.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
@Controller
@RequestMapping("/book")
public class BookController {
    DateFormat dateFormat=new SimpleDateFormat("yy-MM-dd HH:mm:ss");

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    // 教材管理
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String users(ModelMap modelMap){
        // 找到里面的所有记录
        List<BookEntity> bookEntities=bookRepository.findAll();
        for (int i = 0; i <bookEntities.size() ; i++) {
            UserEntity userEntity=userRepository.findOne(bookEntities.get(i).getAddUserId());
            bookEntities.get(i).setUser(userEntity);
        }
        // 将所有的记录传递给返回的jsp页面
        modelMap.addAttribute("bookList", bookEntities);

        // 返回pages目录下的jsp
        return "bookList";
    }

    // 新增教材
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBook(ModelMap modelMap){
        // 返回pages目录下的userManage.jsp
        return "addBook";
    }

    // 新增教材
    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addBookPost(BookEntity bookEntity, HttpSession session){
        String time=dateFormat.format(new Date());
        UserEntity userEntity= (UserEntity) session.getAttribute("user");
        int userId=userEntity.getId();
        bookEntity.setAddUserId(userId);
        bookEntity.setAddTime(time);
        bookRepository.saveAndFlush(bookEntity);
        // 返回pages目录下的userManage.jsp
        return "redirect:/book/list";
    }

    @RequestMapping(value = "/del/{bookId}", method = RequestMethod.GET)
    public String delBook(@PathVariable("bookId") Integer bookId){
        bookRepository.delete(bookId);
        return "redirect:/book/list";
    }
}
