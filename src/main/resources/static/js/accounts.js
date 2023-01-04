const alert_background = document.querySelector(".alert_background");
const btn_delete = document.querySelectorAll("#btn_delete");
const btn_delete_cancel = document.querySelector("#btn_delete_cancel");
const btn_delete_confirm=document.querySelector("#btn_delete_confirm");
const selectAccountType=document.querySelector("#selectAccountType");

function confirmDelete(text) {
    console.log(text);
    alert_background.setAttribute('style', 'visibility:visible')
    btn_delete_confirm.href=text;
}

btn_delete_cancel.addEventListener("click", ()=>{
    console.log("close");
    alert_background.setAttribute('style', 'visibility:hidden')
})

btn_delete_confirm.addEventListener('click', ()=> {
})



