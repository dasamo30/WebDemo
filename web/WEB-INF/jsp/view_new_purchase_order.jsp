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
                    <form id="form_pocab">
	                <section class="col-md-4 col-print-4">                          
                            <div class="form-group">
                            <label for="cboSupplier">Supplier</label>    
                            <select id="cboSupplier" class="form-control select2" style="width: 100%;" required>
                           <option value=""></option>
                            <c:if test="${!empty listSupplier}">
                                <c:forEach var="supplier" items="${listSupplier}">
                                    <option value="${supplier.id_supplier}">${supplier.name_suppliers}</option>											
                                </c:forEach>
                            </c:if>
                            </select>
                            <div class="help-block with-errors"></div>
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
                                    <input class="form-control pull-right" id="txtDate" type="text" required >
                        </div>
	                    <div class="help-block with-errors"></div>    
                            </div>                            
                        </fieldset>
	                    	
	                </section>
                        <input type="submit" id="submit" style="display:none;" />
	             </form>   
	            </div>
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
            <!-- Modal HTML -->
            <div id="myModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Add Product</h4>
                        </div>
                        <div class="modal-body">
                            <form  data-toggle="validator" id="addRowProduct" role="form">
                                <div class="box-body">
                                 <div class="form-group">
                                    <label for="txtProductSearch">Product</label>
                                    <select  id="txtProductSearch" class="form-control select2" style="width: 100%;"  required ></select>
                                    <div class="help-block with-errors"></div>
                                  </div>
                                    <div class="form-group">
                                    <label for="txtPriceProduct">Price</label>
                                    <input type="text" class="form-control" id="txtPriceProduct" name="name" placeholder="Ingrese el precio" required>
                                    <div class="help-block with-errors"></div>
                                  </div>
                                    <div class="form-group">
                                    <label for="txtAmountProduct">Amount</label>
                                    <input type="text" class="form-control" id="txtAmountProduct" name="name" placeholder="Ingrese la cantidad" required>
                                    <div class="help-block with-errors"></div>
                                  </div>
                                    <div class="form-group">
                                    <input id="txtProductId" type="hidden">
                                    <div id="msjAddProducts" style="color:#a94442;" class="help-block with-errors">aaaaa</div>
                                    </div>
                                </div><!-- /.box-body -->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="submit"  class="btn btn-primary">Add Product</button>
                                </div>
                              </form>
                        </div>
                        
                    </div>
                </div>
            </div>
          
           <div class="row">
            <div class="col-xs-12">
          <div class="box">
                <div class="box-header">
                  <h3 class="box-title">Purchase Order List</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                    <div class="mailbox-controls">
                    <!-- Check all button -->
                    
                    <!--<button class="btn btn-default btn-sm checkbox-toggle">Add</button>-->
                    <a id="btnmyModal" class="btn btn-default btn-sm checkbox-toggle" data-toggle="modal">Add</a>
                  </div>
                  <table id="tblProducts" class="table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th>Product</th>    
                        <th>Price</th>
                        <th>Amount</th>
                        <th>Sale</th>
                        <!--<th>Edit</th>-->
                        <th>Delete</th>
                      </tr>
                    </thead>
                  </table>
                    <div id="msjtblProducts" style="color:#a94442;" class="help-block with-errors"></div>
                 <div class="row">
	                <div class="col-md-8 col-print-6">
	                    <p></p>
	                </div>
	                <div class="col-md-4 col-print-6">
	                    <div class="row text-xs-right">
	                        <div class="col-xs-6"></div> <!-- instead of offset -->
	                        <div class="col-xs-3">	                            
	                            <p class="no-margin">
									<strong>Total:</strong>
								</p>
	                            </div>
	                            <div class="col-xs-3">	                                
	                                <p class="no-margin">
										<strong><span id="lblTotalSale">0.00</span></strong>
									</p>
	                            </div>
	                        </div>
	                    </div>
	                </div>   
                </div><!-- /.box-body -->
              </div><!-- /.box -->
              <div class="btn-toolbar mt-lg hidden-print print-buttons">
		            
		            	<button id="btnSavePurchaseOrder" type="button" class="btn btn-primary">
							Save Changes
						</button>
	                    <a href="${contextPath}/purchaseOrdersController/initial" class="btn btn-github">
	                    	Return
	                    </a>
		            </div>
            </div>
          </div>
    
        </section><!-- /.content -->
        
        
 <%@include file="view_panel_footer.jsp" %>