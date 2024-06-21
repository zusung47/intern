<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../_inc/header-1.jsp"%>
<%@ include file="../_inc/header-2.jsp"%>
<%@ include file="../_inc/lnb.jsp"%>


<script>
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";

	function messageInsertForm() {
		$("#frm").attr("action", "<%=request.getContextPath()%>/messageInsertForm.do")
		$("#frm").attr("method", "GET");
		$("#frm").submit();
	}

	function messageDetailOpen(messageId) {
		$("#frm").attr("action", "<%=request.getContextPath()%>/messageDetailOpen/" + messageId + ".do")
		$("#frm").attr("method", "GET");
		$("#frm").submit();
	}

	function isCheck(checkBoxtest, id) {
		const isChecked = checkBoxtest.checked;
		const messageId = id;

		//체크상태 true넘기기
		if(isChecked) {
			console.log("isChecked : ", isChecked);
			const url = "<%=request.getContextPath()%>/updateCheckTypeTrue.do";
			fetch(url, {
				method: 'PUT',
				headers: {
					'header': csrfHeaderName,
					'Content-Type': 'application/json',
					'X-CSRF-Token': csrfTokenValue
				},
				body: JSON.stringify({
					messageId: messageId,
				})
			})
					.then(response => {
						if (response.ok) {
							alert('check true 성공.');
						}
					})
					.catch(error => {
						console.error('에러 발생:', error);
						alert('에러가 발생했습니다.');
					});
			//체크상태 false넘기기
		} else {
			console.log("isChecked : ", isChecked);
			const url = "<%=request.getContextPath()%>/updateCheckTypeFalse.do";
			fetch(url, {
				method: 'PUT',
				headers: {
					'header': csrfHeaderName,
					'Content-Type': 'application/json',
					'X-CSRF-Token': csrfTokenValue
				},
				body: JSON.stringify({
					messageId: messageId,
				})
			})
					.then(response => {
						if (response.ok) {
							alert('check false 성공.');
						}
					})
					.catch(error => {
						console.error('에러 발생:', error);
						alert('에러가 발생했습니다.');
					});
		}
	}

	function deleteCheckTrue() {
		const userConfirmed = confirm("정말로 메세지를 삭제하시겠습니까?");

		if (userConfirmed) {
			const url = "<%=request.getContextPath()%>/deleteMessageByCheckTrue.do";

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
							location.reload();
						}
					})
					.catch(error => {
						console.error('에러 발생:', error);
						alert('메세지 삭제 중 에러가 발생했습니다.');
					});
		}
	}

	function goSearch() {
		$("#frm").attr("action", "<%=request.getContextPath()%>/searchMessage.do")
		$("#frm").attr("method", "GET");
		$("#frm").submit();
	}
</script>

<form id="frm" name="frm">
	<div class="container">
		<div class="container-header">
			<h3>메세지</h3>
			<div class="navigation">
				<ul class="">
					<li>Home</li>
					<li>메세지</li>
				</ul>
			</div>
		</div>

		<div class="container-body">
			<div class="card">
				<div class="card-header">

					<!-- 검색영역 -->
					<div class="card-header-element-long search-select">
						<div class="input-group">
							<select id="searchCondition" name="searchCondition" class="input-group-prepend display-flex">
								<option value="A"
									<c:out value='${cri.searchCondition eq "A" ? "selected" : ""}'/>>전체</option>
								<option value="T"
									<c:out value='${cri.searchCondition eq "T" ? "selected" : ""}'/>>제목</option>
								<option value="C"
									<c:out value='${cri.searchCondition eq "C" ? "selected" : ""}'/>>내용</option>
							</select>

							<input type="text" class="form-control input-group-between display-flex" name="searchText"
								   value="<c:out value='${cri.searchText}'/>" placeholder="검색어를 입력하세요.">

							<div class="input-group-append display-flex">
								<button class="btn btn-blue btn-append form-control" onclick="goSearch()"><i class="fa fa-search"></i></button>
							</div>
						</div>
					</div>

					<!-- 화면 축소시 검색영역 -->
					<div class="board-control board-control-mobile mt-20">
						<div class="input-group display-flex">
							<select id="searchChoice" class="input-group-prepend input-md">
								<option id="ALL" value="">전체</option>
								<option id="searchTitle" value="searchTitle">제목</option>
								<option id="searchContent" value="searchContent">내용</option>
							</select>

							<input type="text" class="form-control input-group-between w-100" id="searchText" placeholder="검색어를 입력하세요.">

							<div class="input-group-append input-sm">
								<button class="btn btn-blue btn-append form-control" onclick="goSearch()"><i class="fa fa-search"></i></button>
							</div>
						</div>
					</div>
				</div>

				<div class="card-body">
					<div class="board-control mt-20">
						<div class="left-box">
<%--							<button type="button" class="btn btn-blue" onclick="">수정</button>--%>
							<button type="button" class="btn btn-negative" onclick="deleteCheckTrue()">삭제</button>
						</div>

						<div class="right-box">
							<button type="button" class="btn btn-blue" onclick="messageInsertForm()"><i class="ft-edit"></i>메세지 작성하기</button>
						</div>
					</div>

					<div class="board-table mb-20">
						<table cellpadding="0" cellspacing="0" class="w-100">
							<colgroup>
								<col width=""/>
							</colgroup>

							<thead>
							<tr>
								<th scope="row"></th>
								<th scope="row">No</th>
								<th scope="row">제목</th>
								<th scope="row">작성일</th>
								<th scope="row">작성자</th>
								<th scope="row">받는 사람</th>
							</tr>
							</thead>

							<tbody>
							<c:choose>
								<c:when test="${listEmpty eq 'listEmpty'}">
									<tr>
										<td colspan="6" class="text-center">등록 된 게시글이 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="message" items="${messageDTOlIST}">
										<tr>
											<td><input type="checkbox" name="check" id="check" onclick="isCheck(this, ${message.messageId})" ${message.checkType ? 'checked' : ''}></td>
											<td>${message.messageId}</td>
											<td><a href="<%=request.getContextPath()%>/messageDetailOpen/${message.messageId}.do">${message.title}</a></td>
											<td><fmt:formatDate pattern="yyyy-MM-dd" value="${message.creationDate}"/></td>
											<td>${message.creationUser}</td>
											<td>${message.receiver}</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
							</tbody>
						</table>
					</div>

					<!-- 페이징 -->
					<div class="text-center">
					</div>
				</div>
			</div>
		</div>
	</div>
</form>

<%@ include file="../_inc/footer.jsp"%>