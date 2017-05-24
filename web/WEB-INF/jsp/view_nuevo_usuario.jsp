<%-- 
    Document   : view_panel_usuarios
    Created on : 17-feb-2017, 11:46:17
    Author     : dasamo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
        <!-- Main content -->
      <!--  <section class="content"> -->
            <div class="row"> 
            <!-- left column  centrar  col-md-offset-3-->
            <div class="col-md-12">
              <!-- general form elements -->
              <div class="box box-primary">
                <div class="box-header with-border">
                  <!--<h3 class="box-title">Registrar Usuario</h3>-->
                </div><!-- /.box-header -->
                <!-- form start -->
                <form  data-toggle="validator" id="${formUsuario}" role="form">
                  <div class="box-body">
                   <div class="form-group">
                      <label for="txtusuario">Usuario</label>
                      <input type="text" class="form-control" id="txtusuario" name="login" value="${usuarioBean.login}" placeholder="Ingrese nombre de usuario" required  >
                      <div class="help-block with-errors"></div>
                    </div>
                    <c:if test = "${empty usuarioBean}">
                    <div class="form-group">
                      <label for="txtpassword">Password</label>
                      <input type="password" class="form-control" id="txtpassword" name="clave" placeholder="Password" required>
                      <div class="help-block with-errors"></div>
                    </div>
                    </c:if>
                   <div class="form-group">
                            <label for="cboPerfiles">Perfil</label>    
                            <select id="cboPerfiles" class="form-control" name="id_perfil"  required > 
                                <option value="" >Seleccione</option>
                            <c:if test="${!empty listPerfil}">
                                <c:forEach var="perfil" items="${listPerfil}">
                                    <option value="${perfil.id_perfil}"  ${ perfil.id_perfil==usuarioBean.id_perfil ? 'selected' : ''}>${perfil.nombre}</option>											
                                </c:forEach>
                            </c:if>
                            </select>
                            <div class="help-block with-errors"></div>
                   </div>
                   <c:if test = "${!empty usuarioBean}">   
                    <div class="form-group">
                        <label>Estado</label>
                        <select name="estado" class="form-control">
                            <option value="1" ${ usuarioBean.estado==1 ? 'selected' : ''} >Activo</option>
                            <option value="0" ${ usuarioBean.estado==0 ? 'selected' : ''}>Incativo</option>
                        </select>
                      </div>
                    </c:if>    
                   <div class="form-group">
                      <label for="txtnombres">Nombres</label>
                      <input type="text" class="form-control" id="txtnombres" name="nombres" value="${usuarioBean.nombres}" placeholder="Ingrese su nombre" required>
                      <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                      <label for="txtapellidos">Apellidos</label>
                      <input type="text" class="form-control" id="txtapellidos" name="apellidos" value="${usuarioBean.apellidos}" placeholder="Ingrese sus apellidos" required>
                      <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                        <label for="txtgenero">Genero</label>
                        <select id="txtgenero" class="form-control" name="genero" required>
                          <option value="" >seleccione</option>
                          <option value="M" ${ usuarioBean.genero=="M" ? 'selected' : ''}>M</option>
                          <option value="F" ${ usuarioBean.genero=="F" ? 'selected' : ''}>F</option>
                      </select>
                      <div class="help-block with-errors"></div>  
                    </div>
                    <div class="form-group">
                      <label for="txtdni">Dni</label>
                      <input type="text" class="form-control" id="txtdni" name="dni" value="${usuarioBean.dni}" placeholder="Ingrese Dni" required>
                      <div class="help-block with-errors"></div>
                    </div>  
                    <div class="form-group">
                      <label for="txtmail">Email address</label>
                      <input type="email" class="form-control" id="txtmail" name="correo" value="${usuarioBean.correo}" placeholder="Ingrese email" required >
                      <div class="help-block with-errors"></div>
                    </div>
                  </div><!-- /.box-body -->
                  <input type="hidden" name="id_usuario"  value="${usuarioBean.id_usuario}" readonly /> 
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Guardar cambios</button>
                  </div>
                </form>
                <div id="msjregusu" ></div>
                <!-- /.box -->
            </div><!--/.col (left) -->
            <!-- right column -->
          </div>   <!-- /.row -->
     <!--   </section><!-- /.content -->  