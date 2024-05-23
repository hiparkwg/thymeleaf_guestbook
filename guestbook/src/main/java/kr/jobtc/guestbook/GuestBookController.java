package kr.jobtc.guestbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@RestController
public class GuestBookController {
    
    @Autowired
    GuestBookDao dao;

    @RequestMapping(path="/")
    public ModelAndView index(HttpSession httpSession){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(path="/login")
    public boolean login(GuestBookVo vo, HttpSession session){
        boolean b=false;
        String name = dao.login(vo);
        if(name !=null){
            b=true;
            session.setAttribute("id", vo.getId());
            session.setAttribute("name", name);
        }
        return b;
    }

    @RequestMapping(path="/logout")
    public void logout(HttpSession session){
        session.setAttribute("id", null);
        session.setAttribute("name", null);
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
        mv.addObject("findStr", findStr);
        mv.setViewName("guestbook/list");
        return mv;
    }

    @RequestMapping(path="/select")
    public String select(int sno){
        String data = "";
        GuestBookVo vo = dao.select(sno);
        data = vo.toJSON();
        return data;
    }

    @RequestMapping(path="/update")
    public boolean update(GuestBookVo vo){
        boolean b=false;
        b = dao.update(vo);
        return b;
    }

    @RequestMapping(path="/delete")
    public boolean delete(GuestBookVo vo){
        boolean b=false;
        b = dao.delete(vo);
        return b;
    }

}
