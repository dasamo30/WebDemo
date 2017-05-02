<%-- 
    Document   : view_new_supplier
    Created on : 01/05/2017, 05:57:26 PM
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
                <form  data-toggle="validator" id="${formSupplier}" role="form" >
                  <div class="box-body">
                    <div class="form-group">
                      <label for="code_suppliers">Code</label>
                      <input type="text" class="form-control" id="code_suppliers" name="code_suppliers" value="${SupplierBean.code_suppliers}" placeholder="Ingrese el codigo" required>
                      <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                      <label for="name_suppliers">Name</label>
                      <input type="text" class="form-control" id="name_suppliers" name="name_suppliers" value="${SupplierBean.name_suppliers}" placeholder="Ingrese el nombre" required>
                      <div class="help-block with-errors"></div>
                    </div><div class="form-group">
                      <label for="phone_number">Phone number</label>
                      <input type="text" class="form-control" id="phone_number" name="phone_number" value="${SupplierBean.phone_number}" placeholder="Ingrese el telefono" required>
                      <div class="help-block with-errors"></div>
                    </div><div class="form-group">
                      <label for="web">Web</label>
                      <input type="text" class="form-control" id="web" name="web" value="${SupplierBean.web}" placeholder="Ingrese el sitio web" required>
                      <div class="help-block with-errors"></div>
                    </div>  
                  </div><!-- /.box-body -->
                  <input type="hidden" name="id_supplier"  value="${SupplierBean.id_supplier}" readonly /> 
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Guardar cambios</button>
                  </div>
                </form>
                <div id="msjmntsupplier" ></div>
                <!-- /.box -->
            </div><!--/.col (left) -->
            <!-- right column -->
          </div>   <!-- /.row -->
     <!--   </section><!-- /.content -->
