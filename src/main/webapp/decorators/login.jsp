<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title><dec:title default="Login" /></title>
    
    <link href="<c:url value='/template/login/bootstrap/bootstrap.min.css' />" rel="stylesheet" id="bootstrap-css">
	<script type="text/javascript" src="<c:url value='/template/login/jquery/jquery.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/template/login/bootstrap/bootstrap.min.js' />"></script>
	<link href="<c:url value='/template/login/style.css' />" rel="stylesheet" type="text/css" media="all"/>
</head>
<body id="LoginForm">
    
    <div class="container">
    	<dec:body/>
    </div>
	
	
</body>
</html>