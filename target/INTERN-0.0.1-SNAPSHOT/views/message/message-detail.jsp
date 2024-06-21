<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../_inc/header-1.jsp"%>
<%@ include file="../_inc/header-2.jsp"%>
<%@ include file="../_inc/lnb.jsp"%>

<script>
    var csrfHeaderName = "${_csrf.headerName}";
    var csrfTokenValue = "${_csrf.token}";

    //메세지 삭제
    function deleteMessage(messageId) {
        const userConfirmed = confirm("정말로 이 메세지를 삭제하시겠습니까?");

        if (userConfirmed) {
            const url = "<%=request.getContextPath()%>/deleteMessage/" + messageId + ".do";

            fetch(url, {
                method: 'DELETE',
                headers: {
                    'header': csrfHeaderName,
                    'X-CSRF-Token': csrfTokenValue
                }
            })
                .then(response => {
                    if (response.ok) {
                        alert('메세지가 성공적으로 삭제되었습니다.');
                        window.location.href = "<%=request.getContextPath()%>/messageList.do";
                    }
                })
                .catch(error => {
                    console.error('에러 발생:', error);
                    alert('메세지 삭제 중 에러가 발생했습니다.');
                });
        }
    }

    //메세지 수정
    function updateMessage() {
        const userConfirmed = confirm("정말로 이 메세지를 수정하시겠습니까?");

        if (userConfirmed) {
            const url = "<%=request.getContextPath()%>/updateMessage.do";

            const messageId = ${message.messageId};
            const title = document.getElementById('title').value;
            const content = document.getElementById('content').value;
            const receiver = document.getElementById('receiver').value;

            fetch(url, {
                method: 'PUT',
                headers: {
                    'header': csrfHeaderName,
                    'Content-Type': 'application/json',
                    'X-CSRF-Token': csrfTokenValue
                },
                body: JSON.stringify({
                    messageId: messageId,
                    title: title,
                    content: content,
                    receiver: receiver
                })
            })
                .then(response => {
                    if (response.ok) {
                        alert('메세지가 성공적으로 수정되었습니다.');
                        window.location.href = "<%=request.getContextPath()%>/messageList.do";
                    }
                })
                .catch(error => {
                    console.error('에러 발생:', error);
                    alert('메세지 수정 중 에러가 발생했습니다.');
                });
        }

    }

    function back() {
        window.history.back();
    }
</script>

<form id="theForm" name="theForm" enctype="multipart/form-data">
    <div class="container">
        <div class="container-header">
            <h3>메세지 관리</h3>
            <div class="navigation">
                <ul class="">
                    <li>Home</li>
                    <li>시스템관리</li>
                    <li>메세지관리</li>
                </ul>
            </div>
        </div>

        <div class="container-body">
            <div class="card">
                <div class="card-header">
                    <h4>메세지 관리</h4>

                    <div class="card-header-element"></div>
                </div>

                <div class="card-body mt-10">
                    <div class="write-table write-table-vertical mb-10">
                        <table cellpadding="0" cellspacing="0" class="w-100">
                            <colgroup>
                                <col width="15%"/><col width="35%"/><col width="15%"/><col width="35%"/>
                            </colgroup>

                            <tbody>
                            <tr>
                                <th scope="col">제목</th>
                                <td colspan="3">
                                    <input type="text" class="form-control w-100" id="title" name="title" value="${message.title}" placeholder="">
                                </td>
                            </tr>
                            <tr>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <textarea id="content" name="content" cols="" rows="15" class="form-control h-150 w-100">${message.content}</textarea>
                                </td>
                            </tr>

                            <tr>
                                <th scope="col">받는 사람</th>
                                <td colspan="3">
                                    <div class="input-group display-inline align-middle">
                                        <input type="email" class="form-control" name="receiver" id="receiver" value="${message.receiver}">
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="btn-area text-right">
                        <button type="button" class="btn btn-next btn-negative px-20" onclick="back()">취소</button>
                        <button type="button" class="btn btn-blue px-20" onclick="updateMessage()">수정</button>
                       <button type="button" class="btn btn-negative px-20" onclick="deleteMessage(${message.messageId})">삭제</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<%@ include file="../_inc/footer.jsp"%>
