<%-- 
    Document   : login
    Created on : 01-jun-2016, 17:34:48
    Author     : dasamo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>AdminLTE 2 | Log in</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" type="text/css" href="${contextPath}/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" type="text/css" href="${contextPath}/bootstrap/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" type="text/css" href="${contextPath}/bootstrap/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet"type="text/css" href="${contextPath}/dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/iCheck/square/blue.css">
    </head>
 <body class="hold-transition login-page">
    <div class="login-box">
      <div class="login-logo">
          <a><b>CTAC - Inventory</b></a><br/>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">Inicia sesión para ingresar</p>
        <form action="validatelogin" method="post">
          <div class="form-group has-feedback">
              <input type="text" class="form-control" placeholder="Usuario" name="usuario">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
              <input type="password" class="form-control" placeholder="Contraseña" name="clave">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">
              <div class="checkbox icheck">
                <label>
                    <%=(request.getParameter("rpta")==null)?"": request.getParameter("rpta")%>
                 <!-- <input type="checkbox"> Remember Me -->
                </label>
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
                 ${rpta}
              <button type="submit" class="btn btn-primary btn-block btn-flat">Ingresar</button>
            </div><!-- /.col -->
          </div>
        </form>
        <!--
        <div class="social-auth-links text-center">
          <p>- OR -</p>
          <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using Facebook</a>
          <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using Google+</a>
        </div>
        -->
        <!-- /.social-auth-links -->
        <!--
        <a href="#">I forgot my password</a><br>
        <a href="register.html" class="text-center">Register a new membership</a>-->

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <!-- jQuery 2.1.4 -->
    <script src="${contextPath}/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="${contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="${contextPath}/plugins/iCheck/icheck.min.js"></script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>
  </body>
</html>
