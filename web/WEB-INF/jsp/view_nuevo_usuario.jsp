<%-- 
    Document   : view_panel_usuarios
    Created on : 17-feb-2017, 11:46:17
    Author     : dasamo
--%>

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
                <form  data-toggle="validator" id="frmrRegistraUsuario" role="form">
                  <div class="box-body">
                   <div class="form-group">
                      <label for="txtusuario">Usuario</label>
                      <input type="text" class="form-control" id="txtusuario" name="login"  placeholder="Ingrese nombre de usuario" required  >
                    </div>
                    <div class="form-group">
                      <label for="txtpassword">Password</label>
                      <input type="password" class="form-control" id="txtpassword" name="clave" placeholder="Password" required>
                    </div>
                   <div class="form-group">
                      <label for="txtnombres">Nombres</label>
                      <input type="text" class="form-control" id="txtnombres" name="nombres" placeholder="Ingrese su nombre" required>
                    </div>
                    <div class="form-group">
                      <label for="txtapellidos">Apellidos</label>
                      <input type="text" class="form-control" id="txtapellidos" name="apellidos" placeholder="Ingrese sus apellidos" required>
                    </div>
                    <div class="form-group">
                        <label for="txtgenero">Genero</label>
                        <select id="txtgenero" class="form-control" name="genero" required>
                          <option value="" >seleccione</option>
                          <option value="M">M</option>
                          <option value="F">F</option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="txtdni">Dni</label>
                      <input type="text" class="form-control" id="txtdni" name="dni" placeholder="Ingrese Dni" required>
                    </div>  
                    <div class="form-group">
                      <label for="txtmail">Email address</label>
                      <input type="email" class="form-control" id="txtmail" name="correo" placeholder="Ingrese email" required >
                    </div>
                    <!--
                    <div class="form-group">
                      <label for="exampleInputFile">File input</label>
                      <input type="file" id="exampleInputFile">
                      <p class="help-block">Example block-level help text here.</p>
                    </div>
                    <div class="checkbox">
                      <label>
                        <input type="checkbox"> Check me out
                      </label>
                    </div>-->
                  </div><!-- /.box-body -->
                  
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