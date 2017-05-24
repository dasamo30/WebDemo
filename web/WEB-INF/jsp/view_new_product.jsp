<%-- 
    Document   : view_new_product
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
                <form  data-toggle="validator" id="${formHardware}" role="form" enctype="multipart/form-data">
                    
                  <div class="box-body">
                  <div class="col-md-6">    
                   <div class="form-group">
                      <input type="hidden" name="id"  value="${productBean.id}" readonly /> 
                      <label for="txtCodeProduct">Code Product</label>
                      <input type="text" class="form-control" id="txtcodeProduct" name="code" value="${productBean.code}"  placeholder="Ingrese el codigo de producto" required  >
                      <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                      <label for="txtNameProduct">Name Product</label>
                      <input type="text" class="form-control" id="txtNameProduct" name="name" value="${productBean.name}" placeholder="Ingrese el nombre del producto" required>
                      <div class="help-block with-errors"></div>
                    </div>
                   <div class="form-group">
                            <label for="cboCategories">Categories</label>    
                            <select id="cboCategories" class="form-control" name="id_category" style="width: 100%;" required > 
                                <option value="" ></option>
                            <c:if test="${!empty listCategories}">
                                <c:forEach var="category" items="${listCategories}">
                                    <option value="${category.id_category}"  ${ category.id_category==productBean.id_category ? 'selected' : ''}>${category.name_category}</option>											
                                </c:forEach>
                            </c:if>
                            </select>			                           	                       	                                
                   </div>     
                   <div class="form-group">
                      <label for="txtnombres">Description</label>
                      <textarea class="form-control" rows="3" id="txtnombres" name="description" placeholder="Ingrese la descripcion del producto" required >${productBean.description}</textarea>
                      <div class="help-block with-errors"></div>
                    </div>
                  </div> 
                      <!--
                    <div class="form-group">
                      <label for="txtunitCost">Unit Cost</label>
                          <input type="text" class="form-control" id="txtunitCost" name="unit_cost" value="${productBean.unit_cost}" placeholder="Ingrese costo unitario" required>
                    </div>-->
                    <div class="col-md-6">  
                    <div class="form-group has-feedback">
                        <label for="txtunitCost" class="control-label">Unit Cost</label> 
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-dollar"></i></span>
                            <input type="text" class="form-control" pattern="^[1-9]\d*(\.\d+)?$" id="txtunitCost" name="unit_cost" value="${productBean.unit_cost}" placeholder="Ingrese costo unitario" required>
                        </div>
                        
                        <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group has-feedback">
                        <label for="txtStock" class="control-label">Stock</label> 
                            <input type="text" class="form-control"  id="txtStock" readonly="" value="${ productBean.stock!=null ? productBean.stock : '0'}"  required>    
                        <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group has-feedback">
                        <label for="txtAlertStock" class="control-label">Alert stock</label> 
                        <input type="text" class="form-control" pattern="^[1-9]\d*?$" id="txtAlertStock" name="alert_stock" value="${productBean.alert_stock}"  required>    
                        <div class="help-block with-errors"></div>
                    </div>     
                    <div class="form-group">
                      <label for="exampleInputFile">File input</label>
                      <div id="image_preview" class="box-body" style="display: block;">
                          
                          <img id="previewing"  ${imagebase64}  class="img-responsive center-block" /></div>  
                      <!--<input type="file" id="fileToUpload" name="images" required> src="${imagebase64}"-->
                      <input type="file" name="imageProd"  id="file" />
                      <div class="help-block with-errors"></div>
                      <!--<h4 id='loading' >loading..</h4>-->
                      <div id="message"></div>
                    </div>
                      
                    </div>   
                  </div><!-- /.box-body -->
                  
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Guardar cambios</button>
                  </div>
                </form>
                <div id="msjregprod" ></div>
                <!-- /.box -->
            </div><!--/.col (left) -->
            <!-- right column -->
          </div>   <!-- /.row -->
     <!--   </section><!-- /.content -->  