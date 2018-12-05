$(document).ready(function () {
 //   LoginHide();
    SubmenuHide();
    RedactorHide();
    RedactorUserHide();
    RedactorOrderHide();

    $('.catalog').mouseover(function () {
        SubmenuShow();

    });
    $('.catalog').mouseout(function () {
        SubmenuHide();
    });

    $('.submenu').mouseover(function () {
        SubmenuShow();

    });
    $('.submenu').mouseout(function () {
        SubmenuHide();
    });

    // $('.button_login').click(function () {
    //     Login();
    // });


});

function RedactorHide() {
    $('.redactor').hide();
}

function RedactorShow() {
    $('.redactor').show();
}

function RedactorUserHide() {
    $('.redactoruser').hide();
}

function RedactorUserShow() {
    $('.redactoruser').show();
}

function RedactorOrderHide() {
    $('.redactororder').hide();
}

function RedactorOrderShow() {
    $('.redactororder').show();
}

function SubmenuShow() {
    $('.submenu').show();
}

function SubmenuHide() {
    $('.submenu').hide();
}
//
// function LoginShow() {
//     $(".login").show();
//     // document.querySelector(".login").style.display = "block";
// }
//
// function LoginHide() {
//     $(".login").hide();
//     // document.querySelector(".login").style.display = "none";
// }
//
// function LogOutHide() {
//     $('.logout').hide();
// }
// //
// function Login() {
//     var url = $('#login_form').serialize();
//     $.ajax({
//         type: 'GET',
//         url: '/shop?page=login&' + url
//     }).done(function (user) {
//      // if (user["email"].length > 1){
//             LoginHide();
//             document.querySelector(".si-in").style.display = "none";
//             document.querySelector(".logout").style.display = "block";
//        //  } else {
//         //     LoginShow();
//         //     document.querySelector("#error").style.display = "block";
//         //
//         // }
//         // LoginHide();
//         // document.querySelector(".si-in").style.display = "none";
//         // document.querySelector(".logout").style.display = "block";
//
//
//
//     }).fail(function () {
//
//     });
//}