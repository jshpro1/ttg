<%-- 
    Document   : index
    Created on : 2024. 3. 28., 오후 3:14:35
    Author     : guym3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>TTG <보드게임 온라인></title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/my_style.css">
    </head>
    <body>
        <%@ include file ="/WEB-INF/jspf/header.jspf"%>
        <div class="center"> 
                사용자: <input type="text" name="userid" size="20" autofocus> <br />
                암&nbsp;&nbsp;&nbsp;호: <input type="password" name="passwd" size="20"> <br /> <br />
                <input type="submit" value="로그인" name="B1">&nbsp;&nbsp;&nbsp;
                <input type="reset" value="다시 입력" name="B2">
        </div>
                
        <ul>
            <li><a href="#">홈</a></li>
            <li><a href="choice">보드게임 선택</a></li>
            <li><a href="#">마이 페이지</a></li>
        </ul>
        <%@ include file ="/WEB-INF/jspf/footer.jspf"%> 
    </body>
</html>
