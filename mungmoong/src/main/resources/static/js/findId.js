

function openTab(evt, tabName) {
    var i, tabcontent, tabbuttons;
    tabcontent = document.getElementsByClassName("tab-content");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tabbuttons = document.getElementsByClassName("tab-button");
    for (i = 0; i < tabbuttons.length; i++) {
        tabbuttons[i].className = tabbuttons[i].className.replace(" active", "");
    }
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}

function sendOtp() {
    var csrfToken = $("meta[name='csrf-token']").attr("content");
    var csrfHeader = $("meta[name='csrf-header-name']").attr("content");
    $.ajax({
        url: '/users/find/sendOtp',
        type: 'POST',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        contentType: "application/json",
        data: JSON.stringify({ email: $('#mail').val() }),
        success: function(response) {
            $(".feedback").text("인증번호를 보냈습니다.").show();
            $(".feedback-error").hide();
        },
        error: function(xhr) {
            $(".feedback-error").text("인증번호 발송에 실패했습니다: " + xhr.responseText).show();
            $(".feedback").hide();
        }
    });
}

function verifyOtp() {
    var csrfToken = $("meta[name='csrf-token']").attr("content");
    var csrfHeader = $("meta[name='csrf-header-name']").attr("content");
    $.ajax({
        url: '/users/find/verifyOtp',
        type: 'POST',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        contentType: "application/json",
        data: JSON.stringify({
            email: $('#mail').val(),
            otp: $('#mail-verification').val()
        }),
        success: function(response) {
            if (response === "이메일인증 성공하였습니다.") {
                $("#save-btn").prop('disabled', false);
                $(".feedback2").text(response).show();
                $(".feedback-error2").hide();
            } else {
                $(".feedback-error2").text(response).show();
                $(".feedback2").hide();
            }
        },
        error: function(xhr) {
            $(".feedback-error2").text("인증 확인에 실패했습니다: " + xhr.responseText).show();
            $(".feedback2").hide();
        }
    });
}

function sendOtpPw() {
    var csrfToken = $("meta[name='csrf-token']").attr("content");
    var csrfHeader = $("meta[name='csrf-header-name']").attr("content");
    $.ajax({
        url: '/users/find/sendOtp',
        type: 'POST',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        contentType: "application/json",
        data: JSON.stringify({ email: $('#pw-mail').val() }),
        success: function(response) {
            $(".feedback").text("인증번호를 보냈습니다.").show();
            $(".feedback-error").hide();
        },
        error: function(xhr) {
            $(".feedback-error").text("인증번호 발송에 실패했습니다: " + xhr.responseText).show();
            $(".feedback").hide();
        }
    });
}

function verifyOtpPw() {
    var csrfToken = $("meta[name='csrf-token']").attr("content");
    var csrfHeader = $("meta[name='csrf-header-name']").attr("content");
    $.ajax({
        url: '/users/find/verifyOtp',
        type: 'POST',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        contentType: "application/json",
        data: JSON.stringify({
            email: $('#pw-mail').val(),
            otp: $('#pw-mail-verification').val()
        }),
        success: function(response) {
            if (response === "이메일인증 성공하였습니다.") {
                $("#confirm-btn").prop('disabled', false);
                $(".feedback2").text(response).show();
                $(".feedback-error2").hide();
            } else {
                $(".feedback-error2").text(response).show();
                $(".feedback2").hide();
            }
        },
        error: function(xhr) {
            $(".feedback-error2").text("인증 확인에 실패했습니다: " + xhr.responseText).show();
            $(".feedback2").hide();
        }
    });
}