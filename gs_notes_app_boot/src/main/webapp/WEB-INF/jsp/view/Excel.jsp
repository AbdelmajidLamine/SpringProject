<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 09/06/2022
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<style>

    #file {
        border: 2px dashed #92b0b3 !important;

    }



    .files:before {
        position: absolute;
        bottom: 60px;
        left: 0;
        width: 100%;
        content: attr(data-before);
        color: #000;
        font-size: 12px;
        font-weight: 600;
        text-align: center;
    }
    button:focus {
        -moz-box-shadow: none !important;
        -webkit-box-shadow: none !important;
        box-shadow: none !important;
        outline-width: 0
    }

    .btn {
        letter-spacing: 1px;
        text-align: center;
        position: center;
        margin-left: 570px;
    }


    #file {
        display: inline-block;
        width: 100%;
        padding: 95px 0 0 100%;
        background: url('https://i.imgur.com/VXWKoBD.png') top center no-repeat #fff;
        background-size: 55px 55px;
    }

</style>
<body>

<%--<form method="post" enctype="multipart/form-data" action="upload">
    <input type="file" name="file" accept=".xls,.xlsx" /> <input
        type="submit" value="Upload file" />
</form>--%>
<form method="post" enctype="multipart/form-data" action="upload">
<div class="row justify-content-center">
    <div class="col-md-12 col-lg-10 col-12"> <div class="form-group files"><label class="my-auto"><strong>Upload Your File</strong> </label> <input id="file" name="file" type="file" accept=".xls,.xlsx" /> <div class="col-lg-12 m-4 col-auto "><button type="submit" class="btn btn-primary btn-block"><small class="font-weight-bold">Upload</small></button> </div></div></div>
</div>
</form>

<script>
    $(document).ready(function(){
        $(".files").attr('data-before',"Drag file here or click the above button");
        $('input[type="file"]').change(function(e){
            var fileName = e.target.files[0].name;
            $(".files").attr('data-before',fileName);

        });
    });
</script>
</body>
</html>
