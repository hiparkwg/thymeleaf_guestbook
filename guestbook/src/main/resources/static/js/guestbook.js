/* register -------------- */

let btnRegister = document.querySelector(".btnRegister");
let btnModify = document.querySelector(".btnModify");
let btnDelete = document.querySelector(".btnDelete");

btnRegister.onclick = ()=>{
    console.log("register")
    let frm = document.frmRegister;
    let frmData = $(frm).serialize();

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

btnModify.onclick = ()=>{
    console.log("modify")
}

btnDelete.onclick = ()=>{
    console.log("delete")
}

let view = (frm)=>{
    console.log(frm.sno.value)
}
let load = ()=>{
    $.ajax({
        url : "/search",
        type : "GET",
        success : (resp)=>{
            let temp = $(resp).find('.list')
            console.log(temp);
            $('.right').html(temp);
        }
    })
}