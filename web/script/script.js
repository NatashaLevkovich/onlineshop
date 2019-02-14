$(document).ready(function () {

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
