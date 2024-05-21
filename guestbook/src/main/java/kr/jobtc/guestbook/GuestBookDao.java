package kr.jobtc.guestbook;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.jobtc.guestbook.mybatis.MyFactory;

@Component
public class GuestBookDao {
    
    SqlSession session;
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
}
