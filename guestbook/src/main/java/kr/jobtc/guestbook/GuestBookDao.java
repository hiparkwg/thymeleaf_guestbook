package kr.jobtc.guestbook;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.jobtc.guestbook.mybatis.MyFactory;

@Component
public class GuestBookDao {
    
    SqlSession session;


    public String login(GuestBookVo vo){
        session = new MyFactory().getSession();
        String name = session.selectOne("guestbook.login", vo);

        session.close();
        return name;
    }

    public boolean register(GuestBookVo vo){
        boolean b = false;
        session = new MyFactory().getSession();
    
        int cnt = session.insert("guestbook.register", vo);
        if(cnt>0){
            b=true;
            session.commit();
        }else{
            b=false;
            session.rollback();
        }

        session.close();
        return b;
    }

    public List<GuestBookVo> search(String findStr){
        session = new MyFactory().getSession();
        List<GuestBookVo> list = session.selectList("guestbook.search", findStr);


        session.close();
        return list;
    }

    public GuestBookVo select(Integer sno){
        GuestBookVo vo = null;
        session = new MyFactory().getSession();
        vo = session.selectOne("guestbook.select", sno);
        String temp = vo.getDoc();
        vo.setDoc(temp);
        session.close();
        return vo;
    }
    public boolean update(GuestBookVo vo){
        boolean b=false;
        session = new MyFactory().getSession();
        int cnt = session.update("guestbook.update", vo);
        if(cnt>0){
            b=true;
            session.commit();
        }else{
            session.rollback();
        }
        session.close();
        return b;
    }

    public boolean delete(GuestBookVo vo){
        boolean b=false;
        session = new MyFactory().getSession();
        int cnt = session.delete("guestbook.delete", vo);
        if(cnt>0){
            b=true;
            session.commit();
        }else{
            session.rollback();
        }
        session.close();
        return b;
    }

}
