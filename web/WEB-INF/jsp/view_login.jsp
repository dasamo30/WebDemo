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
        <link href="${contextPath}/images/ctasitelogo.png" rel="shortcut icon">
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
    <link rel="stylesheet"type="text/css" href="${contextPath}/dist/css/AdminLTE.css">
    <!-- iCheck -->
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/iCheck/square/blue.css">
    <!-- animate -->
    <link rel="stylesheet"type="text/css" href="${contextPath}/dist/css/animate.css">
    <!-- login -->
    <link rel="stylesheet"type="text/css" href="${contextPath}/css/login.css">
    </head>
 <body class="hold-transition login-page">
    <div class="login-box animated fadeInUp">
      <div>
          <h3 class="widget-login-logo">
                    <i class="fa fa-circle text-black"></i>
                    Inventory CTAC
                    <i class="fa fa-circle text-warning"></i>
                </h3>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">Inicia sesión para ingresar</p>
        <form action="validatelogin" method="post" data-toggle="validator">
          <div class="form-group has-feedback">
              <input type="text" class="form-control" placeholder="Usuario" name="usuario" required >
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
            <div class="help-block with-errors"></div>
          </div>
          <div class="form-group has-feedback">
              <input type="password" class="form-control" placeholder="Contraseña" name="clave" required >
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            <div class="help-block with-errors"></div>
          </div>
          <div class="row">
            <div class="col-xs-8">
              <div class="checkbox icheck">
                  <label style="color:#a94442;">
                    ${rpta}
                 <!-- <input type="checkbox"> Remember Me -->
                </label>
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat btn-sm">Ingresar</button>
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
      <div class="login-footer">
                   ${datefooter} &copy; Inventory CTAC.
      </div>
    </div><!-- /.login-box -->

    <!-- jQuery 2.1.4 -->
    <script src="${contextPath}/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="${contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="${contextPath}/plugins/iCheck/icheck.min.js"></script>
     <!-- validator -->
    <script src="${contextPath}/plugins/validator/validator.js"></script>
    <script>
      $(function () {
        /*$('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
        */
         $("#validatelogin").validator();
         //$("#validatelogin")[0].reset();
      });
    </script>
  </body>
</html>
