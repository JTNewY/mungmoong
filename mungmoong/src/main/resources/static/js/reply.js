

  // 👩‍💻 모델 객체를 자바스크립트로 가져오는 방법
  let no = "[[${board.no}]]"      // 글번호

  // 댓글 목록 요청
  replyList()         
  

  // 수정 화면 이동
  function moveUpdate() {
      location.href = '/board/update?no=' + no
  }

  // 목록 화면 이동
  function moveList() {
      location.href = '/board/list'
  }

  // 파일 삭제
  function deleteFile(element, no) {
      // alert(no)

      // AJAX 비동기 요청
      let request = new XMLHttpRequest()

      // 요청 세팅
      // request.open(요청메소드, URL)
      request.open('DELETE', '/file/' + no)
      request.send()

      request.onreadystatechange = function() {

          // 요청 성공 시,
          if( request.readyState == request.DONE && request.status == 200 ) {
              console.log('파일 삭제 성공!');
              // 파일 항목 제거
              element.parentNode.remove()
          }
      }
  }

  // 댓글 목록
  function replyList() {
       // AJAX 비동기 요청
       let request = new XMLHttpRequest()

      // 요청 세팅
      request.open('GET', '/reply/' + no)
      request.send()

      request.onreadystatechange = function() {

          // 요청 성공 시,
          if( request.readyState == request.DONE && request.status == 200 ) {
              // 댓글 목록
              let response = request.responseText

              // ⭐ 서버에서 렌더링한 HTML로 갱신(SSR)
              let data = response     // (html)

              // ⭐ JSON 데이터를 전달받아 HTML 로 변환 (CSR)
              // let replyListData = JSON.parse(response) // JSON -> array
              // let data = ``
              // for(let i = 0 ; i < replyListData.length ; i++ ) {
              //     data += `<div class="reply-box">`
              //     data += `<div class="inner">`
              //     let writer = replyListData[i].writer        // 작성자
              //     let content = replyListData[i].content      // 댓글내용
              //     let date = replyListData[i].regDate
                  
              //     data += `<div class="top">`
              //     data += `<span class="reply-writer">${writer}</span>`
              //     data += `<span class="reply-date">${date}</span>`
              //     data += `<div class="item">`
              //     data += `<button class="btn-reply-update">수정</button>`
              //     data += `<button class="btn-reply-delete">삭제</button>`
              //     data += `</div>`
              //     data += `</div>`

              //     data += `<p class="reply-content">${content}</p>`
              //     data += `<button class="btn-reply-answer">답글</button>`
              //     data += `</div>`
              //     data += `</div>`
              // }

              let replyList = document.getElementById('reply-list')
              replyList.innerHTML = data

              // 이벤트 등록    
              event()
          }
      }

  }

  // 댓글 등록
  function insertReply() {
      let $writer = document.getElementById('reply-writer')
      let $content = document.getElementById('reply-content')

      let writer = $writer.value
      let content = $content.value

      let data = {
          'boardNo'   : no,
          'writer'    : writer,
          'content'   : content 
      }

      let request = new XMLHttpRequest()
      
      request.open('POST', '/reply')
      request.setRequestHeader('Content-Type', 'application/json')
      request.send( JSON.stringify(data) )

      request.onreadystatechange = function() {
          // 요청 성공 시,
          if( request.readyState == request.DONE && request.status == 201 ) {
              console.log('댓글 등록 성공!');
              // alert(request.responseText)     // 응답 메시지 확인
              let response = request.responseText
              if( response == 'SUCCESS' ) {
                  replyList()     // 댓글 목록 갱신
                  // 댓글 입력 창 비우기
                  $writer.value = ''
                  $content.value = ''
              }
          }
      }
  }

  // 이벤트 등록
  function event() {

      // 댓글 수정 버튼
      let $btnUpdate = document.getElementsByClassName('btn-reply-update')
      // 댓글 수정 클릭 이벤트
      for( let i = 0 ; i < $btnUpdate.length ; i++ ) {
          
          $btnUpdate[i].addEventListener('click', function() {
              // $btnUpdate[i] : 수정 버튼
              let $top = $btnUpdate[i].parentNode.parentNode
              let writer = $top.childNodes[1].textContent
              let $inner = $top.parentNode
              let content = $inner.childNodes[3].textContent
              let $replyBox = $inner.parentNode

              let no = $btnUpdate[i].getAttribute('data')         // 댓글 번호

              // 댓글 수정 모드
              // 1. 기존 댓글 내용창 숨기기
              $inner.style.display = 'none'
              // 2. 수정 UI 출력
              let $editor = document.createElement('div')
              $editor.classList.add('inner')
              $editor.id = 'editor'
              let editor = ``
              // editor += `<div class="inner" id="editor">`
              editor += `<input type="text" value="${writer}" id="update-writer" />`
              editor += `<br>`
              editor += `<textarea cols="60" rows="5" id="update-content">${content}</textarea> `
              editor += `<br>`
              editor += `<button onclick="updateReply(${no})">수정</button>`
              editor += `<button onclick="cancelReply()">취소</button>`
              // editor += `</div>`
              $editor.innerHTML = editor
              $replyBox.appendChild($editor)
          })
      }
  }

  // 댓글 삭제 요청
  function deleteReply(no) {

      const check = confirm("정말로 삭제하시겠습니까?")
      if( !check )
          return

      // 삭제 요청
      let request = new XMLHttpRequest()
      request.open('DELETE', '/reply/' + no)
      // request.open('DELETE', `/reply/${no}`)       // ⭐템플릿 문자열
      request.send()

      request.onreadystatechange = function() {
          // 요청 성공 시,
          if( request.readyState == request.DONE && request.status == 200 ) {
              console.log('댓글 삭제 성공!');
              let response = request.responseText
              // alert(response)
              if( response == 'SUCCESS' ) {
                  replyList()     // 댓글 목록 갱신
              }
          }
      }
  }

  // 댓글 수정 취소
  function cancelReply() {
      let $editor = document.getElementById('editor')
      $editor.remove()            // 요소 삭제

      let $inner = document.getElementsByClassName('inner')
      for (let i = 0; i < $inner.length; i++) {
          $inner[i].style.display = 'block'                
      }
  }

  // 댓글 수정 요청
  function updateReply(no) {
      let writer = document.getElementById('update-writer').value
      let content = document.getElementById('update-content').value

      console.log(`수정한 작성자 : ${writer}`);
      console.log(`수정한 내용 : ${content}`);

      let data = {
          'no'        : no,
          'writer'    : writer,
          'content'   : content,
      }

      console.dir(data)

      // 수정 요청
      let request = new XMLHttpRequest()
      request.open('PUT', '/reply')
      request.setRequestHeader("Content-Type", "application/json")
      request.send( JSON.stringify(data) )

      request.onreadystatechange = function() {
          // 요청 성공 시,
          if( request.readyState == request.DONE && request.status == 200 ) {
              console.log('댓글 수정 성공!');
              let response = request.responseText
              // alert(response)
              if( response == 'SUCCESS' ) {
                  replyList()     // 댓글 목록 갱신
              }
          }
      }
  }

  // 답글 등록
  function insertAnswer(element, no) {
      let $replyBox = element.parentNode.parentNode
      console.log(`부모 번호 : ${no}`);
      console.log($replyBox);

      // 답글 작성 UI
      let $editor = document.createElement('div')     
      $editor.classList.add('reply-box')
      $editor.id = 'answer'
      // <div id="answer" class="reply-box" >
      let editor = ``
      editor += `<div class="inner">`
      editor += `<input type="text" id="answer-writer" />`     
      editor += `<br>`     
      editor += `<textarea cols="60" rows="5" id="answer-content"></textarea>`     
      editor += `<br>`     
      editor += `<button onclick="answer(${no})">등록</button>`     
      editor += `<button onclick="cancelAnswer()">취소</button>`     
      editor += `</div>`     
      $editor.innerHTML = editor

      // 기존 답글 UI 가 있으면 초기화
      let $answer = document.getElementById("answer");
      if ($answer) {
          $answer.parentNode.removeChild($answer);
      }

      // 요소.after(새 요소)
      // - 요소 바로 뒤에 새 요소를 추가합니다.
      $replyBox.after($editor)
  }

  // 답글 등록 요청
  function answer(parentNo) {
      // TODO
      // 1. answer-writer, answer-content  값을 가져오고
      // 2. data = { 부모번호, 작성자, 내용 }
      // 3. POST - /reply  요청
      let $writer = document.getElementById('answer-writer')
      let $content = document.getElementById('answer-content')

      let writer = $writer.value            
      let content = $content.value            

      let data = {
          'boardNo'    : no,
          'parentNo'   : parentNo,
          'writer'     : writer,
          'content'    : content
      }

      let request = new XMLHttpRequest()

      request.open('POST', '/reply')
      request.setRequestHeader('Content-Type', 'application/json')
      request.send( JSON.stringify(data) )

      request.onreadystatechange = function() {
          if( request.readyState == request.DONE && request.status == 201 ) {
              let response = request.responseText 
              if( response == 'SUCCESS' ) {
                  replyList()         // 댓글 목록 갱신
              }

          }
      }
  }

  // 답글 등록 취소
  function cancelAnswer() {

      let $answer = document.getElementById("answer");
      if ($answer) {
          $answer.parentNode.removeChild($answer);
      }

  }
