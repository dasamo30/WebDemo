<%-- 
    Document   : view_new_purchase_orders
    Created on : 02-may-2017, 12:03:15
    Author     : dasamo
--%>

<%@include file="view_panel_header.jsp" %>
        <!-- Main content -->
        <section class="content">
          <!--<div class="row">  
          <div class="col-md-12">
              <div class="box-body">
                <button class="btn btn-primary">Nuevo usuario</button>
            </div>
          </div>
          </div>-->    
            <div class="row"> 
            <!-- left column  centrar  col-md-offset-3-->
            <div class="col-md-12">
              <!-- general form elements -->
              <div class="box box-primary">
                <div class="box-header with-border">
                    	            <div class="row mb-lg">
	                <section class="col-md-4 col-print-4">                          
                            <div class="form-group">
                            <label for="cboSupplier">Supplier</label>    
                            <select id="cboSupplier" class="form-control select2" style="width: 100%;">
                           <option value=""></option>
                            <c:if test="${!empty listSupplier}">
                                <c:forEach var="supplier" items="${listSupplier}">
                                    <option value="${supplier.id_supplier}">${supplier.name_suppliers}</option>											
                                </c:forEach>
                            </c:if>
                            </select>			                           	                       	                                
                            </div>  
	                </section>
	                <section class="col-md-3 col-print-3">	                    	                   
	                    <fieldset>                            
                            <div class="form-group">
                                <label for="txtDate">Date</label>
                                <div class="input-group">
                      <div class="input-group-addon">
                        <i class="fa fa-calendar"></i>
                      </div>
                      <input class="form-control pull-right" id="txtDate" type="text">
                    </div>
	                        
                            </div>                            
                        </fieldset>
	                    	
	                </section>
	                
	            </div>
                    <div class="row mb-lg">
	            	            	
	            	<section class="col-md-4 col-print-4">
                        <fieldset>                            
                            <div class="form-group">
                                <input id="txtProductSearch"  type="text" size="16" class="form-control" placeholder="<s:message code="purchaseorder.lbl.product.placeholder" />">                               
                            </div>                           
                        </fieldset>
	                    	                    	                    	
	                </section>
	                
	                <section class="col-md-3 col-print-3">
                        
                        <fieldset>                            
                            <div class="form-group">
	                            <input id="txtProductSellPrice" class="form-control numbersOnly" size="10" type="text" placeholder="<s:message code="purchaseorder.lbl.price.placeholder" />">
                            </div>                            
                        </fieldset>
                                        	                    	
	                </section>
	                
	                <section class="col-md-3 col-print-3">
                       	<fieldset>                            
                            <div class="form-group">
	                            <input id="txtProductAmount" class="form-control numbersOnly" size="10" type="text" placeholder="<s:message code="purchaseorder.lbl.amount.placeholder" />">                                
                                <input id="txtProductId" type="hidden">
                                <input id="txtProductStock" type="hidden">                                   
                            </div>                            
                        </fieldset>                    	
	                </section>
	                
	                 <section class="col-md-2 col-print-2">	
	                 
	                 	<fieldset>       
	                 		<button id="btnProductAdd" type="button" class="btn btn-primary btn-block">
                                            aaaa
	                 		</button>	                 		     
	                    </fieldset>         	                    
	                                      	
               	 	</section>	                
	                
	            </div>
                    <!-- Link trigger modal -->
                    <a href="${contextPath}/purchaseOrdersController/ActViewNewPurchaseOrder"  class="btn btn-primary" >
                        Add Purchase Order
                    </a>
                        
                      <!--<button type="button" class="btn btn-danger" id="click_to_load_modal_popup">Open Popup Modal</button>-->  
                </div><!-- /.box-header -->
                <!-- form start -->
                
                <!--<div id="msjregusu" > </div>-->
              </div><!-- /.box -->
            </div><!--/.col (left) -->
            <!-- right column -->
          </div>   <!-- /.row -->
          
          <!-- Default bootstrap modal example 
            <div class="modal fade" id="myModalNewSupplier" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel"></h4>
                  </div>
                    <div class="modal-body" id="modal-body">
                    
                  </div>
                </div>
              </div>
            </div>-->
          
           <div class="row">
            <div class="col-xs-12">
          <div class="box">
                <div class="box-header">
                  <h3 class="box-title">Purchase Order List</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table id="tbPurchaseOrder" class="table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th>code suppliers</th>    
                        <th>Name suppliers</th>
                        <th>Phone number</th>
                        <th>Web</th>
                        <th>Date creation</th>
                        <th>Date modification</th>
                        <th>Edit</th>
                        <th>Delete</th>
                      </tr>
                    </thead>
                    <tfoot>
                      <tr>
                        <th>code suppliers</th>    
                        <th>Name suppliers</th>
                        <th>Phone number</th>
                        <th>Web</th>
                        <th>Date creation</th>
                        <th>Date modification</th>
                        <th>Edit</th>
                        <th>Delete</th>
                      </tr>
                    </tfoot>
                  </table>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
            </div>
          </div>
    
        </section><!-- /.content -->
        
        
 <%@include file="view_panel_footer.jsp" %>