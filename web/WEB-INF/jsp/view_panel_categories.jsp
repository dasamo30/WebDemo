<%-- 
    Document   : view_panel_categories
    Created on : 28/04/2017, 11:56:55 PM
    Author     : developer
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
                    <a href="${contextPath}/categoryController/ActViewNewCategory" data-toggle="modal" data-target="#myModalNewCategory" data-remote="false" class="btn btn-primary">
                        New category
                    </a>
                        
                      <!--<button type="button" class="btn btn-danger" id="click_to_load_modal_popup">Open Popup Modal</button>-->  
                </div><!-- /.box-header -->
                <!-- form start -->
                
                <!--<div id="msjregusu" > </div>-->
              </div><!-- /.box -->
            </div><!--/.col (left) -->
            <!-- right column -->
          </div>   <!-- /.row -->
          
          <!-- Default bootstrap modal example -->
            <div class="modal fade" id="myModalNewCategory" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel"></h4>
                  </div>
                    <div class="modal-body" id="modal-body">
                    
                  </div>
                  <!--<div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                  </div>-->
                </div>
              </div>
            </div>
          
            <!-- Modal Dialog
            <div class="modal fade" id="confirmDelete" role="dialog" aria-labelledby="confirmDeleteLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Delete Parmanently</h4>
                  </div>
                  <div class="modal-body">
                    <p>Are you sure about this ?</p>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-danger" id="confirm">Delete</button>
                  </div>
                </div>
              </div>
            </div> -->
          
           <div class="row">
            <div class="col-xs-12">
          <div class="box">
                <div class="box-header">
                  <h3 class="box-title">Category List</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table id="tbCategories" class="table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th>Name category</th>
                        <th>Date creation</th>
                        <th>Date modification</th>
                        <th>Edit</th>
                        <th>Delete</th>
                      </tr>
                    </thead>
                    <tfoot>
                      <tr>
                        <th>Name category</th>
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
