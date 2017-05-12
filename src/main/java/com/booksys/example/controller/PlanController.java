package com.booksys.example.controller;

import com.booksys.example.bean.ServiceRes;
import com.booksys.example.model.*;
import com.booksys.example.repository.*;
import com.booksys.example.util.RoleUtil;
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
 * Created by zhangsc on 2017/5/12.
 */
@Controller
@RequestMapping("/plan")
public class PlanController {
    DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String[] activeArr=new String[]{"已锁定","可编辑"};

    @Autowired
    PlanRepository planRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseBookRepository courseBookRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    CourseBookStudentRepository courseBookStudentRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap,HttpSession session){
        UserEntity userEntity= (UserEntity) session.getAttribute("user");
        List<UserroleEntity> userroleEntities=userRoleRepository.findAllByUserId(userEntity.getId());

        // 找到里面的所有记录
        List<PlanEntity> planEntities=planRepository.findAll();
        for (int i = 0; i <planEntities.size() ; i++) {
            int count=courseRepository.findAllByPlanIdOrderByIdDesc(planEntities.get(i).getId()).size();
            planEntities.get(i).setCount(count);
            planEntities.get(i).setActiveStr(activeArr[planEntities.get(i).getActive()]);
        }
        // 将所有的记录传递给返回的jsp页面

        modelMap.addAttribute("isFacultyAdmin", RoleUtil.isFacultyAdmin(userroleEntities));
        modelMap.addAttribute("isTeachAdmin",RoleUtil.isTeachAdmin(userroleEntities));
        modelMap.addAttribute("planList", planEntities);
        // 返回pages目录下的jsp
        return "planList";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServiceRes addBook(@RequestParam(name = "name",required = true) String name, HttpSession session){
        PlanEntity planEntity=new PlanEntity();
        String time=dateFormat.format(new Date());
        planEntity.setBuildTime(time);
        UserEntity userEntity= (UserEntity) session.getAttribute("user");
        planEntity.setBuildUserId(userEntity.getId());
        planEntity.setName(name);
        planEntity.setActive(1);
        planRepository.saveAndFlush(planEntity);
        return new ServiceRes("添加成功",true);
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String delClazz(@PathVariable("id") Integer id){
        planRepository.delete(id);
        return "redirect:/plan/list";
    }


    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    public String clazzUsers(@PathVariable("id") Integer id,ModelMap modelMap,HttpSession session){
        UserEntity userEntity= (UserEntity) session.getAttribute("user");
        List<UserroleEntity> userroleEntities=userRoleRepository.findAllByUserId(userEntity.getId());


        List<CourseEntity> courseEntities=courseRepository.findAllByPlanIdOrderByIdDesc(id);
        for (int i = 0; i <courseEntities.size() ; i++) {
            int a=courseBookRepository.findAllByCourseId(courseEntities.get(i).getId()).size();
            courseEntities.get(i).setCount(a);

        }
        PlanEntity planEntity=planRepository.findOne(id);
        int a=courseEntities.size();
        planEntity.setCount(a);

        modelMap.addAttribute("isTeachAdmin",RoleUtil.isTeachAdmin(userroleEntities));
        modelMap.addAttribute("plan",planEntity);
        modelMap.addAttribute("courseList",courseEntities);
        return "planCourse";
    }

    @ResponseBody
    @RequestMapping(value = "/ajax/addCourse",method = RequestMethod.POST)
    public ServiceRes addUser(@RequestParam(name = "name",required = true) String name,@RequestParam(name = "planId",required = true) int planId,
                              HttpSession session){
        CourseEntity courseEntity=new CourseEntity();
        courseEntity.setName(name);
        courseEntity.setPlanId(planId);
        courseRepository.saveAndFlush(courseEntity);
        return new ServiceRes("添加成功",true);
    }

    @RequestMapping(value = "/course/del/{id}",method = RequestMethod.GET)
    public String delCourse(@PathVariable("id") Integer id){
        CourseEntity courseEntity=courseRepository.findOne(id);
        int planId=courseEntity.getPlanId();
        courseRepository.delete(id);
        return "redirect:/plan/courses/"+planId;
    }

    @RequestMapping(value = "/course/books/{id}",method = RequestMethod.GET)
    public String bookList(@PathVariable("id") Integer id,ModelMap modelMap,HttpSession session){
        UserEntity userEntity= (UserEntity) session.getAttribute("user");
        List<UserroleEntity> userroleEntities=userRoleRepository.findAllByUserId(userEntity.getId());
        boolean isStudent=RoleUtil.isStudent(userroleEntities);
        CourseEntity courseEntity=courseRepository.findOne(id);
        PlanEntity planEntity=planRepository.findOne(courseEntity.getPlanId());
        List<CourseBookEntity> courseBookEntities=courseBookRepository.findAllByCourseId(id);
        courseEntity.setCount(courseBookEntities.size());


        for (int i = 0; i <courseBookEntities.size() ; i++) {
            BookEntity bookEntity=bookRepository.findOne(courseBookEntities.get(i).getBookId());
            courseBookEntities.get(i).setBook(bookEntity);
            List<CourseBookStudentEntity> courseBookStudentEntities1=courseBookStudentRepository.findAllByCourseBookId(courseBookEntities.get(i).getId());
            courseBookEntities.get(i).setCount(courseBookStudentEntities1.size());
            if(isStudent){
                List<CourseBookStudentEntity> courseBookStudentEntities=courseBookStudentRepository.findAllByCourseBookIdAndUserId(courseBookEntities.get(i).getId(),userEntity.getId());
                if(isTheCourseBookBeUserChson(courseBookEntities,courseBookStudentEntities)){
                    courseBookEntities.get(i).setIsChosen(true);
                }else {
                    courseBookEntities.get(i).setIsChosen(false);
                }
            }
        }

        modelMap.addAttribute("isStudent",isStudent);
        modelMap.addAttribute("isTeachAdmin",RoleUtil.isTeachAdmin(userroleEntities));
        modelMap.addAttribute("isTeacher",RoleUtil.isTeacher(userroleEntities));
        modelMap.addAttribute("course",courseEntity);
        modelMap.addAttribute("courseBookList",courseBookEntities);
        modelMap.addAttribute("plan",planEntity);
        return "courseBook";
    }

    @ResponseBody
    @RequestMapping(value = "/ajax/addBook",method = RequestMethod.POST)
    public ServiceRes addBook(@RequestParam(name = "bookIds",required = true) int[] bookIds,@RequestParam(name = "courseId",required = true) int courseId,
                              HttpSession session){
        List<CourseBookEntity> courseBookEntities=courseBookRepository.findAllByCourseId(courseId);
        for (int i = 0; i <bookIds.length ; i++) {
            if(!isTheBookInList(courseBookEntities,bookIds[i])){
                CourseBookEntity courseBookEntity=new CourseBookEntity();
                courseBookEntity.setBookId(bookIds[i]);
                courseBookEntity.setCourseId(courseId);
                courseBookRepository.save(courseBookEntity);
            }
        }
        courseBookRepository.flush();

        return new ServiceRes("添加成功",true);
    }

    @RequestMapping(value = "/course/chooseBook/{id}",method = RequestMethod.GET)
    public String chooseBook(@PathVariable("id") Integer id,HttpSession session){
        UserEntity userEntity= (UserEntity) session.getAttribute("user");
        CourseBookEntity courseBookEntity=courseBookRepository.findOne(id);
        CourseBookStudentEntity courseBookStudentEntity=new CourseBookStudentEntity();
        courseBookStudentEntity.setCourseBookId(id);
        courseBookStudentEntity.setUserId(userEntity.getId());
        courseBookStudentRepository.saveAndFlush(courseBookStudentEntity);
        return "redirect:/plan/course/books/"+courseBookEntity.getCourseId();
    }

    @RequestMapping(value = "/course/unChooseBook/{id}",method = RequestMethod.GET)
    public String unChooseBook(@PathVariable("id") Integer id,HttpSession session){
        UserEntity userEntity= (UserEntity) session.getAttribute("user");
        CourseBookEntity courseBookEntity=courseBookRepository.findOne(id);
        CourseBookStudentEntity courseBookStudentEntity=new CourseBookStudentEntity();
        courseBookStudentEntity.setCourseBookId(id);
        courseBookStudentEntity.setUserId(userEntity.getId());
        courseBookStudentRepository.deleteAllByCourseBookIdAndUserId(id,userEntity.getId());
        return "redirect:/plan/course/books/"+courseBookEntity.getCourseId();
    }

    @RequestMapping(value = "/lock/{id}",method = RequestMethod.GET)
    public String lockPlan(@PathVariable("id") Integer id,HttpSession session){
        planRepository.updatePlanLock(0,id);
        return "redirect:/plan/list";
    }

    @RequestMapping(value = "/unlock/{id}",method = RequestMethod.GET)
    public String unlockPlan(@PathVariable("id") Integer id,HttpSession session){
        planRepository.updatePlanLock(1,id);
        return "redirect:/plan/list";
    }

    @RequestMapping(value = "/final/{id}",method = RequestMethod.GET)
    public String finalResult(@PathVariable("id") Integer id,HttpSession session,ModelMap modelMap){
        PlanEntity planEntity=planRepository.findOne(id);
        List<CourseEntity> courseEntities=courseRepository.findAllByPlanIdOrderByIdDesc(id);
        float pFinal=0;
        for (int i = 0; i <courseEntities.size() ; i++) {
            List<CourseBookEntity> courseBookEntities=courseBookRepository.findAllByCourseId(courseEntities.get(i).getId());
            float pCourse=0;
            for (int j = 0; j <courseBookEntities.size() ; j++) {
                List<CourseBookStudentEntity> courseBookStudentEntities=courseBookStudentRepository.findAllByCourseBookId(courseBookEntities.get(j).getId());
                BookEntity bookEntity=bookRepository.findOne(courseBookEntities.get(j).getBookId());
                courseBookEntities.get(j).setCount(courseBookStudentEntities.size());
                courseBookEntities.get(j).setBook(bookEntity);
                float p=courseBookStudentEntities.size()*bookEntity.getPrice();
                courseBookEntities.get(j).setTotalPrice(p);
                pCourse+=p;
            }
            courseEntities.get(i).setCourseBookList(courseBookEntities);
            pFinal+=pCourse;
        }
        modelMap.addAttribute("finalPrice",pFinal);
        modelMap.addAttribute("plan",planEntity);
        modelMap.addAttribute("courseList",courseEntities);
        return "finalResult";
    }

    private boolean isTheBookInList(List<CourseBookEntity> bookOrderEntities, int bookId) {
        for (int i = 0; i < bookOrderEntities.size(); i++) {
            if (bookId == bookOrderEntities.get(i).getBookId()) {
                return true;
            }
        }
        return false;
    }

    private boolean isTheCourseBookBeUserChson(List<CourseBookEntity> courseBookEntities,List<CourseBookStudentEntity> courseBookStudentEntities){
        for (int i = 0; i <courseBookEntities.size() ; i++) {
            for (int j = 0; j <courseBookStudentEntities.size() ; j++) {
                if(courseBookEntities.get(i).getId()==courseBookStudentEntities.get(j).getCourseBookId()){
                    return true;
                }
            }
        }
        return false;
    }

}
