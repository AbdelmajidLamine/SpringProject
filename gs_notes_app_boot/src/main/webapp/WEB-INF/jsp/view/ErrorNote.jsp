<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 11/06/2022
  Time: 02:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<style>
    a:link, a:visited {
        background-color: #f44336;
        color: white;
        padding: 14px 25px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
    }

    a:hover, a:active {
        background-color: green;
    }
</style>
<body>
<div class="alert alert-danger">

    <strong>${Message}</strong> !
    <br>
    <br>
    <a href="/note/form" style="text-decoration: none ">Go Back</a>
    <br>
    <br>
</div>



</body>
</html>
