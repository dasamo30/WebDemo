<%-- 
    Document   : view_new_category
    Created on : 01/05/2017, 03:49:51 PM
    Author     : developer
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
                <form  data-toggle="validator" id="${formCategory}" role="form" enctype="multipart/form-data">
                  <div class="box-body">
                    <div class="form-group">
                      <label for="txtNameCategory">Name Category</label>
                      <input type="text" class="form-control" id="txtNameCategory" name="name_category" value="${CategoryBean.name_category}" placeholder="Ingrese la categoria" required>
                      <div class="help-block with-errors"></div>
                    </div>  
                  </div><!-- /.box-body -->
                  <input type="hidden" name="id_category"  value="${CategoryBean.id_category}" readonly /> 
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Guardar cambios</button>
                  </div>
                </form>
                <div id="msjmntcategory" ></div>
                <!-- /.box -->
            </div><!--/.col (left) -->
            <!-- right column -->
          </div>   <!-- /.row -->
     <!--   </section><!-- /.content -->