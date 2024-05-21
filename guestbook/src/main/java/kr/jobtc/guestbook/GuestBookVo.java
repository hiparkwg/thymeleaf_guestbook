package kr.jobtc.guestbook;

import lombok.Data;

@Data
public class GuestBookVo {
    int sno;
    String id;
    String doc;
    String nal;
    String pwd;

    public String toJSON(){
        doc = doc.replace("\n", "\\n");
        doc = doc.replace("\r", "\\r");
        String str = String.format(
            "{'sno' : '%s' , 'id' : '%s', 'doc' : '%s', 'nal' : '%s'}",
            sno, id, doc, nal
        );
        return str.replaceAll("'", "\"");
    }
}
