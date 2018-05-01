<%--
  Created by IntelliJ IDEA.
  User: tatja
  Date: 27.04.2018
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!--html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%
    //String redirectURL = "./authorization";
    //response.sendRedirect(redirectURL);
  %>
  </body>
</html-->
<html>
  <head>
    <!--base href="/authorization" /-->

    <base href="./">
      <!--base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/"-->
    <title>Title</title>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
  </head>
  <body>
    <app>Loading...</app>
    <script src="public/polyfills.js"></script>
    <script src="public/app.js"></script>
  </body>
</html>
