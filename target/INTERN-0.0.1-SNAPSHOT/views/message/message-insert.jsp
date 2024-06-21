<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ include file="../_inc/header-1.jsp"%>
<%@ include file="../_inc/header-2.jsp"%>
<%@ include file="../_inc/lnb.jsp"%>

<script>
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";

	function addMessage() {
		const url = "<%=request.getContextPath()%>/addMessage.do";

		const title = document.getElementById('title').value;
		const content = document.getElementById('content').value;
		const receiver = document.getElementById('receiver').value;
		const creationUser = document.getElementById('creationUser').textContent;

		fetch(url , {
			method: 'POST',
			headers: {
				'header': csrfHeaderName,
				'Content-Type': 'application/json',
				'X-CSRF-Token': csrfTokenValue
			},
			body: JSON.stringify({
				title: title,
				content: content,
				receiver: receiver,
				creationUser: creationUser
			})
		})
				.then(response => {
					if (response.ok) {
						alert('메세지가 정상적으로 추가되었습니다.');
						window.location.href = "<%=request.getContextPath()%>/messageList.do";
					}
				})
				.catch(error => {
					console.error('에러 발생:', error);
					alert('메세지 저장 중 에러가 추가했습니다.');
				})
	}
</script>

<form id="theForm" name="theForm">
	
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
					<h4>메세지 작성</h4>
					
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
										<input type="text" class="form-control w-100" id="title" name="title" value="" placeholder="">
									</td>
								</tr>
								<tr>
								</tr>
								<tr>
									<td colspan="4">
										<textarea id="content" name="content" cols="" rows="15" class="form-control h-150 w-100"></textarea>
									</td>
								</tr>
								<tr>
									<th scope="col">From</th>
									<td colspan="3">
										<div class="input-group display-inline align-middle">
											<p id="creationUser"><sec:authentication property="principal.member.userName"/></p>
										</div>
									</td>
								</tr>
								<tr>
									<th scope="col">To</th>
									<td colspan="3">
										<div class="input-group display-inline align-middle">
											<input type="email" class="form-control" name="receiver" id="receiver">
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					
					<div class="btn-area text-right">
						<button type="button" class="btn btn-next btn-negative px-20">취소</button>
						<button type="button" class="btn btn-blue px-20" onclick="addMessage()">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>

<%@ include file="../_inc/footer.jsp"%>