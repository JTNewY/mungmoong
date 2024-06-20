// ################################# 달력 ######################################## 
  

$(document).ready(function() {
    $("div.input-daterange input").datepicker({
      language: 'ko', // 한국어 설정
      format: 'yyyy-mm-dd', // 날짜 형식 설정
      autoclose: true // 날짜 선택 후 자동 닫힘
    });
    
    $("div.input-daterange").each(function() {
      var $inputs = $(this).find('input');
      if ($inputs.length >= 2) {
        var $from = $inputs.eq(0);
        var $to = $inputs.eq(1);
        $from.on('changeDate', function(e) {
          $to.datepicker('setStartDate', new Date(e.date.valueOf()));
        });
        $to.on('changeDate', function(e) {
          $from.datepicker('setEndDate', new Date(e.date.valueOf()));
        });
      }
    });
  });
  // ################################# 회원가입 선택 ################################
  function showContent(content, clickedButton) {
    var contentA = document.getElementById("contentA");
    var contentB = document.getElementById("contentB");
    var buttons = document.querySelectorAll('.button-container button');

    // 내용 숨김
    contentA.style.display = "none";
    contentB.style.display = "none";

    // 모든 버튼의 활성화 상태 해제
    buttons.forEach(function(button) {
      button.classList.remove('active');
    });

    // 선택한 내용 보이기
    if (content === "A") {
      contentA.style.display = "block";
    } else if (content === "B") {
      contentB.style.display = "block";
    }

    // 클릭된 버튼 활성화 상태로 설정
    clickedButton.classList.add('active');
  }

  // 페이지 로드 시 초기화
  document.addEventListener('DOMContentLoaded', function() {
    showContent('A', document.getElementById('buttonA'));
  });

  // ################################# 아이디 중복여부 확인 ################################

  // 💍 CSRF TOKEN
  const csrfToken = /*[[${_csrf.token}]]*/ 'CSRF_TOKEN_PLACEHOLDER';

  /*
      아이디 중복 확인
  */
  async function checkId() {
      const userId = document.getElementById("userId").value;

      // null 또는 undefined
      if (!userId) {
          alert("아이디를 입력해주세요");
          return;
      }

      try {
          // 아이디 중복 확인
          const response = await fetch(`/users/register/check/${userId}`, {
              method: 'GET',
              headers: {
                  'X-CSRF-TOKEN': csrfToken
              }
          });

          if (response.ok) {
              const result = await response.json();
              let boxId = document.getElementById('box-id');
              if (result) {
                  alert('사용 가능한 아이디입니다.');
                  boxId.classList.remove('needs-validation');
                  boxId.classList.add('was-validated');
                  return true;
              } else {
                  alert('중복된 아이디입니다.');
                  boxId.classList.remove('was-validated');
                  boxId.classList.add('needs-validation');
              }
              return false;
          } else {
              alert('아이디 중복 확인 중 오류가 발생했습니다.');
              return false;
          }
      } catch (error) {
          console.error('Error:', error);
          alert('아이디 중복 확인 중 오류가 발생했습니다.');
          return false;
      }
  }

  // ################################# 비밀번호 확인 ######################################
  async function checkPasswordMatch() {
    var password = document.getElementById('password').value;
    var passwordchk = document.getElementById('passwordchk').value;
    var invalidFeedback = document.querySelector('#box-pw .invalid-feedback');
    let boxPw = document.getElementById('box-pw');
    var saveBtn = $('#save-btn');
    
    // 비밀번호 유효성 검사
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

    if (!passwordRegex.test(password)) {
        alert("비밀번호는 최소 8자 이상이어야 하며, 대문자, 소문자, 숫자 및 특수문자를 포함해야 합니다.");
        $("#password-invalid-success").hide();
        $("#password-invalid-feedback").show();
        invalidFeedback.style.display = 'block';
        boxPw.classList.remove('was-validated');
        boxPw.classList.add('needs-validation');
        saveBtn.prop('disabled', true);
        return false;
    }

    // null 또는 undefined 체크
    if (!password || !passwordchk) {
        alert("비밀번호를 입력해주세요.");
        return false;
    }

    // 비밀번호 일치 여부 확인
    if (password === passwordchk) {
        $("#password-invalid-success").show();
        $("#password-invalid-feedback").hide();
        invalidFeedback.style.display = 'none';
        boxPw.classList.remove('needs-validation');
        boxPw.classList.add('was-validated');
        saveBtn.prop('disabled', false);
        return true;    
    } else {
        $("#password-invalid-success").hide();
        $("#password-invalid-feedback").show();
        invalidFeedback.style.display = 'block';
        boxPw.classList.remove('was-validated');
        boxPw.classList.add('needs-validation');
        saveBtn.prop('disabled', true);
        return false;
    }
}

  // ################################# 주소 합치기 ########################################
  function combineAddress() {
    var address2 = document.getElementById('address2').value.trim();
    var address3 = document.getElementById('address3').value.trim();

    // address2와 address3를 공백으로 구분하여 결합
    var combinedAddress = address2 + (address3 ? ' ' + address3 : '');

    // 결합된 주소를 숨겨진 input 상자에 저장
    document.getElementById('address').value = combinedAddress;
}


//======================================이메일 인증 =====================================
$(document).ready(function() {
    $.ajaxSetup({
        beforeSend: function(xhr) {
            var csrfToken = $("meta[name='csrf-token']").attr("content");
            var csrfHeader = $("meta[name='csrf-header-name']").attr("content");
            xhr.setRequestHeader(csrfHeader, csrfToken);
        }
    });

    $("#sendCode").click(function() {
        $.ajax({
            url: '/users/register/sendOtp',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify({ email: $('#mail').val() }),
            success: function(response) {
                $(".invalid-send").show();
                $(".invalid-send-error").hide();
            },
            error: function() {
                $(".invalid-send").hide();
                $(".invalid-send-error").show();
            }
        });
    });

    $("#verifyCode").click(function() {
        $.ajax({
            url: '/users/register/verifyOtp',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                email: $('#mail').val(),
                otp: $('#mail-verification').val()
            }),
            success: function(response) {
                if (response === "이메일인증 성공하였습니다.") {
                    $("#save-btn").prop('disabled', false);
                    $(".valid-feedback").show();
                    $(".invalid-feedback").hide();
                } else {
                    $(".valid-feedback").hide();
                    $(".invalid-feedback").show();
                }
            },
            error: function(xhr) {
                $(".valid-feedback").hide();
                $(".invalid-feedback").show();
            }
        });
    });




});
