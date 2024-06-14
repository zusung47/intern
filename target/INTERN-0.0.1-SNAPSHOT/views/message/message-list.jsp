<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../_inc/header-1.jsp"%>
<%@ include file="../_inc/header-2.jsp"%>
<%@ include file="../_inc/lnb.jsp"%>


<script>
	function messageInsertForm() {
		$("#frm").attr("action", "<%=request.getContextPath()%>/messageInsertForm.do")
		$("#frm").attr("method", "POST");
		$("#frm").submit();
	}

	function messageDetailOpen(messageId) {
		$("#frm").attr("action", "<%=request.getContextPath()%>/messageDetailOpen/" + messageId + ".do")
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
					<h4>메세지 목록</h4>
					
					<!-- 검색영역 -->
					<div class="card-header-element-long search-select">
						<div class="input-group">
							<select id="searchChoice" class="input-group-prepend display-flex">
								<option id="ALL" value="">전체</option>
								<option id="searchTitle" value="searchTitle">제목</option>
								<option id="searchContent" value="searchContent">내용</option>
							</select>
							
							<input type="text" class="form-control input-group-between display-flex" id="searchText" placeholder="검색어를 입력하세요.">
							
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
							<button type="button" class="btn btn-blue" onclick="">수정</button>
							<button type="button" class="btn btn-negative" onclick="">삭제</button>
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
									<th scope="row">No</th>
									<th scope="row">제목</th>
									<th scope="row">작성일</th>
									<th scope="row">작성자</th>
									<th scope="row">받는 사람</th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach var="message" items="${messageDTOlIST}">
										<tr>
											<td><input type="checkbox" name="check" id="check"></td>
											<td>${message.messageId}</td>
											<td><a href="<%=request.getContextPath()%>/messageDetailOpen/${message.messageId}.do">${message.title}</a></td>
											<td><fmt:formatDate pattern="yyyy-MM-dd" value="${message.creationDate}"/></td>
											<td>${message.creationUser}</td>
											<td>${message.receiver}</td>
										</tr>
								</c:forEach>
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