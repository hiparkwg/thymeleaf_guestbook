package kr.jobtc.guestbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GuestBookController {
    
    @Autowired
    GuestBookDao dao;

    @RequestMapping(path="/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(path="/register")
    public boolean register(GuestBookVo vo){
        boolean b = dao.register(vo);
        return b;
    }

    @RequestMapping(path="/search")
    public ModelAndView search(String findStr){
        ModelAndView mv = new ModelAndView();
        List<GuestBookVo> list = dao.search(findStr);

        mv.addObject("list", list);
        mv.setViewName("guestbook/list");
        return mv;
    }

}
