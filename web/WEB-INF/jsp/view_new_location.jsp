<%-- 
    Document   : view_new_location
    Created on : 13-abr-2017, 23:27:10
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
                <form  data-toggle="validator" id="${formLocation}" role="form" enctype="multipart/form-data">
                  <div class="box-body">
                    <div class="form-group">
                      <label for="txtNameProduct">Name Location</label>
                      <input type="text" class="form-control" id="txtNameLocation" name="name_location" value="${LocationBean.name_location}" placeholder="Ingrese la ubicacion" required>
                      <div class="help-block with-errors"></div>
                    </div>  
                  </div><!-- /.box-body -->
                  <input type="hidden" name="id_location"  value="${LocationBean.id_location}" readonly /> 
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Guardar cambios</button>
                  </div>
                </form>
                <div id="msjmntlocation" ></div>
                <!-- /.box -->
            </div><!--/.col (left) -->
            <!-- right column -->
          </div>   <!-- /.row -->
     <!--   </section><!-- /.content -->  