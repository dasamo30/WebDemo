<%-- 
    Document   : view_panel_purchase_orders
    Created on : 02-may-2017, 11:45:10
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
                        <th>Nro OC</th>    
                        <th>Code Supplier</th>
                        <th>Supplier</th>
                        <th>Username</th>
                        <th>Amount</th>
                        <th>Date</th>
                        <th>Detail</th>
                        <th>Delete</th>
                        <th>Print</th>
                      </tr>
                    </thead>
                    <tfoot>
                      <tr>
                        <th>Nro OC</th>      
                        <th>Supplier</th>
                        <th>Code Supplier</th>
                        <th>Username</th>
                        <th>Amount</th>
                        <th>Date</th>
                        <th>Detail</th>
                        <th>Delete</th>
                        <th>Print</th>
                      </tr>
                    </tfoot>
                  </table>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
            </div>
          </div>
    
        </section><!-- /.content -->
        
        
 <%@include file="view_panel_footer.jsp" %>