/* register -------------- */
$(function(){
    load();
})


let btnRegister = document.querySelector(".btnRegister");
let btnModify = document.querySelector(".btnModify");
let btnDelete = document.querySelector(".btnDelete");

let btnLogin = document.querySelector(".btnLogin");
let btnLogout = document.querySelector(".btnLogout");

if(btnLogin !=null){
    btnLogin.onclick = ()=>{
        let temp = document.frmLogin;
        let frm = $(temp).serialize();
        $.ajax({
            url   : "/login",
            type  : "POST",
            data  : frm,
            success : (resp)=>{
                location.href="/";
            }
        })
    }
}
if(btnLogout !=null){
    btnLogout.onclick = ()=>{
        $.ajax({
            url   : "/logout",
            type  : "GET",
            success : (resp)=>{
                location.href="/";
            }
        })
    }
}




btnRegister.onclick = ()=>{
    let frm = document.frmRegister;
    let frmData = $(frm).serialize();
    alert(frmData)
    $.ajax({
        url    : "/register",
        type   : "POST",
        data   : frmData,
        success : (resp)=>{
            if(resp){
                load();
            }
        }
    })
}


/* list 에 방명록 리스트 출력하기 */
let btnSearch;
let load = (findStr)=>{
    $.ajax({
        url : "/search",
        type : "GET",
        data : {"findStr" :  findStr},
        success : (resp)=>{
            let temp = $(resp).find('.list')
            $('.right').html(temp);
            btnSearch = document.querySelector(".btnSearch");
            btnSearch.onclick = ()=>{
                let findStr = document.querySelector(".findStr").value;
                load(findStr);
            }
            
        }
    })
}


let view = (frm)=>{
    $.ajax({
        url   : "/select",
        type  : "GET",
        data  : {"sno" : frm.sno.value },
        success : (resp)=>{
            let frmRegister = document.frmRegister;
            let json = JSON.parse(resp);
            frmRegister.sno.value = json.sno;
            frmRegister.id.value = json.id;
            frmRegister.pwd.value = '';
            let temp = json.doc.replace("\\n", "\n");
            temp = temp.replace("\\r", "\r")
            frmRegister.doc.value = temp;
        }
    })
}


btnModify.onclick = ()=>{
    let frm = document.frmRegister;
    let frmData = $(frm).serialize();

    $.ajax({
        url    : "/update",
        type   : "POST",
        data   : frmData,
        success : (resp)=>{
            if(resp){
                load();
                clearForm();
            }else{
                alert("수정중 오류 발생")
            }
        }
    })
}

btnDelete.onclick = ()=>{
    let frm = document.frmRegister;
    let frmData = $(frm).serialize();

    $.ajax({
        url    : "/delete",
        type   : "POST",
        data   : frmData,
        success : (resp)=>{
            if(resp){
                load();
                clearForm();
            }else{
                alert("삭제중 오류 발생")
            }
        }
    })
}

let clearForm=()=>{
    let frm = document.frmRegister;
    frm.sno.value='';
    frm.id.value='';
    frm.doc.value='';
    frm.pwd.value='';
}