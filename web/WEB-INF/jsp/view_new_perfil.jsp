<%-- 
    Document   : view_new_perfil
    Created on : 22-may-2017, 15:20:19
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
                <form  data-toggle="validator" id="${formPerfil}" role="form" >
                  <div class="box-body">
                    <div class="form-group">
                      <label for="txtNamePerfil">Name Perfil</label>
                      <input type="text" class="form-control" id="txtNamePerfil" name="nombre" value="${PerfilBean.nombre}" placeholder="Ingrese un nombre" required>
                      <div class="help-block with-errors"></div>
                    </div>
                      <c:if test = "${!empty PerfilBean}">   
                    <div class="form-group">
                        <label>Estado</label>
                        <select name="estado" class="form-control">
                            <option value="1" ${ PerfilBean.estado==1 ? 'selected' : ''} >Activo</option>
                            <option value="0" ${ PerfilBean.estado==0 ? 'selected' : ''}>Incativo</option>
                        </select>
                      </div>
                    </c:if>
                  </div><!-- /.box-body -->
                  <input type="hidden" name="id_perfil"  value="${PerfilBean.id_perfil}" readonly /> 
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Guardar cambios</button>
                  </div>
                </form>
                <div id="msjmntPerfil" ></div>
                
                <!-- /.box -->
            </div><!--/.col (left) -->
            <!-- right column -->
          </div>   <!-- /.row -->
     <!--   </section><!-- /.content -->  