jQuery(document).ready( function () {
    //$idmnus=$("#opmnu").val().split(":");
   
    //console.log();
    
    //class="active"
    $('#example1').DataTable( {
        //responsive: true,
       // "paging":false,
                   // "scrollY": 400,
                   // "scrollX": true,
                   // "info": false,
                    //"ordering": false,
                    //"searching": false
    } );
     /*$('#example').DataTable();
      $("#example1").DataTable();*/
        /*$('#example2').DataTable({
          "paging": true,
          "lengthChange": true,
          "searching": true,
          "ordering": true,
          "info": true,
          "autoWidth": true
        });*/
        
    //$("#example").DataTable();
    /*
    $('#example').DataTable( {
        "ajax": "data/arrays.txt"
    } );*/
    
    //alert(baseurl);
    var dataTable = $('#tbusuario').DataTable({
            processing: true,
            serverSide: true,
            responsive: true,
            autoWidth: false,
            ajax:{
                url :baseurl+"/usuarios/ActlistaUsuarios", // json datasource
                type: "post",  // method  , by default get
                complete: function(){
                   table=$('#tbusuario');
                  //alert(dataTable);
                  //console.log(table.parent());
                  table.parent().addClass("table-responsive");
                 // table.parent().addClass('table-responsive');//.removeClass('col-sm-12');
                },
                error: function(){  // error handling
                    $(".tbusuario-grid-error").html("");
                    $("#tbusuario-grid").append('<tbody class="tbusuario-grid-error"><tr><th colspan="3">No data found in the server</th></tr></tbody>');
                    $("#tbusuario-grid_processing").css("display","none");
 
                }
            },
            "aoColumns": [
            { "mData": "login" },
            { "mData": "nombres"},
            { "mData": "apellidos"},
            { "mData": "perfil"},
            { "mData": "ico_estado","sClass": "text-center"},
            { "mData": "ico_edit","sClass": "text-center"},
            { "mData": "ico_delete","sClass": "text-center"}
            
        ] 
    });
    
   /* $("#frmrRegistraUsuario").submit(function(event){
    // cancels the form submission
        var msj=$("#msjregusu");
        msj.removeAttr('class');
        msj.html("");
        
        event.preventDefault();
        alert("sssss");
        msj.addClass("callout callout-success");
        msj.html("<p>This is a green callout.</p>");
        //callout callout-success
        //submitForm();
    });*/
    
   /* $("#myModal12").on("show.bs.modal", function(e) {
        //alert("ssssss");
        var link = $(e.relatedTarget);
        $(this).find(".modal-body").load(link.attr("href"));
        alert("aaaa");
        $('#frmrRegistraUsuario').validator();
        //$('#frmrRegistraUsuario').formValidation('resetForm', true);
        
    });*/
   /*
    $('#myModal').on('show.bs.modal', function (e) { 
        var link = $(e.relatedTarget);
       // alert(link.attr("href"));
        $.get(link.attr("href"), function( data ) { 
            $('.modal-body').html(data); 
            $('#frmrRegistraUsuario').validator();
            //do somethings that i want 
            
        }); 
    
    });*/
    
    $('#myModalViewUsuario').on('show.bs.modal', function (e) { 
        
        console.log("myModalViewUsuario");
        
        var btn = $(e.relatedTarget);
        var idUsuario=btn.data('id');
       var data=null;
       var title="Registrar usuario";
       var frm='#frmrRegistraUsuario';
       
       if(btn.attr("id")==="btnViewEditUsuario"){ 
           console.log("btnViewEditPerfil");
            data={"idUsuario":idUsuario};
            title="Modificar usuario";
            frm='#frmrModificaUsuario';
       }
        $.post(btn.attr("href"),data, function( data ) { 
            $('#myModalLabel').html(title);
            $('#modal-body').html(data); 
            $(frm).validator();
        }); 
    });
    
    
   /* $('#frmrRegistraUsuario').validator().on('submit', function (e) {
        
        if (e.isDefaultPrevented()) {
            alert("ssssss");
            // handle the invalid form...
        } else {
            alert("dddd");
            // everything looks good!
        }
})*/
    

    //$('#form').validator().on('submit', function (e) {
   $(document).on("submit","#frmrRegistraUsuario",function(e){    
      
        if (e.isDefaultPrevented()) {
            return false;
        }
       
       frm=$("#frmrRegistraUsuario");
       e.preventDefault();
       var msj=$("#msjregusu");
           msj.removeAttr('class');
           msj.html("");
           
       //$("#popup").modal("show"); 
       
       $.ajax({
            type: "POST",
            url: baseurl+"/usuarios/ActRegistraUsuario",
            contentType: 'application/json',
            data:JSON.stringify(frm.serializeJSON()),
            success: function(result){
              //  alert(result);
            //  chatWith('9','name');
                if(result==0){
                    alerts(0,msj,"El usuario se grabo con exito");   
                    loadDataTable("#tbusuario");
                    frm.trigger('reset');
                }else{
                    alerts(2,msj,"El usuario ya existe");
                }
                
                
            },
            error: function() {
                //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
                
                alerts(3,msj,"A ocurrido un error interno !!!");
                msj.addClass("alert alert-danger alert-dismissable");
                msj.html("<p>A ocurrido un error interno !!!</p>");
            } 
        });
      //  alert("ajax");
    });
    

    
    /*
    $('#frmrRegistraUsuario').on('submit', function (e) {
        //e.preventDefault();
        
        if (e.isDefaultPrevented()) {
          // handle the invalid form...
          alert("aaaa");
        } else {
          // everything looks good!
          alert("bbb");
        }
        });*/
     /* $("#frmrRegistraUsuario").submit(function(e){
    // cancels the form submission
        e.preventDefault();
        alert("aaaa");
        //submitForm();
    
    });*/
    
    ///////////////////////////////perfiles/////////////////////////////////////
    //$.fn.DataTable.ext.errMode = 'throw';
   //$.fn.dataTable.ext.errMode = 'none';
/*
$('#tbperfil')
    .on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } )
    .DataTable();*/
    
    var dataTablep = $('#tbperfil').DataTable({
           processing: true,
            //"serverSide": true,
            responsive: true,
            autoWidth: false,
            ajax:{
                url :baseurl+"/perfiles/ActlistaPerfiles", // json datasource
                type: "post",  // method  , by default get
                complete: function(){
                   table=$('#tbperfil');
                  //alert(dataTable);
                  //console.log(table.parent());
                  table.parent().addClass("table-responsive");
                 // table.parent().addClass('table-responsive');//.removeClass('col-sm-12');
                },
                error: function(xhr, textStatus, errorThrown){  // error handling
                   
                    $("#tbperfil_wrapper").html("");
                    $("#tbperfil_wrapper").append('<div class="alert alert-danger alert-dismissable"><tr><th colspan="3">No data found in the server</th></tr></div>'); 
                }
            },
            "aoColumns": [
            { "mData": "nombre" },
            { "mData": "fecha"},
            //{ "mData": "tiempo_sesion"},
            { "mData": "ico_estado","sClass": "text-center"},
            { "mData": "ico_editar","sClass": "text-center"}
            //{ "mData": "ico_permiso","sClass": "text-center"}
            //{ "sClass": "a-right", "aTargets": [ 4 ] }
            
            ]/*,
            "columnDefs": [
            { className: "text-center", "targets": [4,5] }
            //{ className: "text-nowrap", "targets": [0,1] }
          ]*/
    });
    
    
    $('#myModalViewPerfil').on('show.bs.modal', function (e) { 
        
        console.log("myModalViewPerfil");
        
        var btn = $(e.relatedTarget);
        var idPerfil=btn.data('id');
       var data=null;
       var title="Registra Perfil";
       var frm='#frmrRegisterPerfil';
       
       if(btn.attr("id")==="btnViewEditPerfil"){ 
           console.log("btnViewEditPerfil");
            data={"idPerfil":idPerfil};
            title="Modifica perfil";
            frm='#frmModifPerfil';
       }
        $.post(btn.attr("href"),data, function( data ) { 
            $('#myModalLabel').html(title);
            $('#modal-body').html(data); 
            $(frm).validator();
        }); 
    });
    
    
    
    $(document).on("submit","#frmrRegisterPerfil",function(e){    
      
        if (e.isDefaultPrevented()) {
            return false;
        }
       e.preventDefault();
       
       frm=$("#frmrRegisterPerfil");
       
       var msj=$("#msjmntPerfil");
           msj.removeAttr('class');
           msj.html("");
           
        
        console.log(":::frmrRegisterPerfil");
        //return;
       $.ajax({
            type: "POST",
            url: baseurl+"/perfiles/ActRegistraPerfil",
            contentType: 'application/json',
            data:JSON.stringify(frm.serializeJSON()),
            success: function(result){
              //  alert(result);
            //  chatWith('9','name');
                if(result==0){
                    alerts(0,msj,"El perfil se grabo con exito");   
                    loadDataTable("#tbperfil");
                    frm.trigger('reset');
                }else{
                    alerts(2,msj,"El perfil ya existe");
                }
                
                
            },
            error: function() {
                //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
                
                alerts(3,msj,"A ocurrido un error interno !!!");
            } 
        });
      //  alert("ajax");
    });
    
    $(document).on("submit","#frmModifPerfil",function(e){    
      
        if (e.isDefaultPrevented()) {
            return false;
        }
       e.preventDefault();
       
       frm=$("#frmModifPerfil");
       
       var msj=$("#msjmntPerfil");
           msj.removeAttr('class');
           msj.html("");
           
        
        console.log(":::frmModifPerfil");
        //return;
       $.ajax({
            type: "POST",
            url: baseurl+"/perfiles/ActModificaPerfil",
            contentType: 'application/json',
            data:JSON.stringify(frm.serializeJSON()),
            success: function(result){
              //  alert(result);
            //  chatWith('9','name');
                if(result==0){
                    alerts(0,msj,"El perfil se modifico con exito");   
                    loadDataTable("#tbperfil");
                    frm.trigger('reset');
                }else{
                    alerts(2,msj,"No se completo el proceso.. !!!");
                }
                
                
            },
            error: function() {
                //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
                
                alerts(3,msj,"A ocurrido un error interno !!!");
            } 
        });
      //  alert("ajax");
    });
    
    
    //btnEliminaUsuario
    $(document).on("click","#btnEliminaUsuario",function(e){    

        var obj = this;
        ezBSAlert({
        type: "confirm",
        headerText:"Confirm",
        messageText: "Are you sure about this ?",
        alertType: "warning"
        }).done(function (e) {
          var idUsuario = $(obj).data('id');
          //console.log("confirma::"+idProduct);
          //var url =baseurl+"/usuarios/ActEliminarUsuario";
          if(e){
              $.ajax({
                url: baseurl+"/usuarios/ActEliminaUsuario",
                type: 'POST',
                data: { idUsuario:idUsuario} ,
                //contentType: 'application/json; charset=utf-8',
                success: function (result) {
                    if(result==0){
                        //alerts(0,msj,"");   
                        loadDataTable("#tbusuario");
                        ezBSAlert({ headerText:"success", messageText: "El usuario se elmino con exito", alertType: "success"});
                    }else{
                       // alerts(2,msj,"No se completo el proceso.. !!!");
                        ezBSAlert({ headerText:"Error",messageText: "No se completo el proceso.. !!!", alertType: "danger"});
                    }


                },
                error: function () {
                    //alerts(3,msj,"A ocurrido un error interno !!!");
                    ezBSAlert({ headerText:"Error",messageText: "A ocurrido un error interno !!!", alertType: "danger"});
                }
              });
            
          }
        });
   });
    
    //btnViewEditUsuario
    $(document).on("submit","#frmrModificaUsuario",function(e){    
      
        if (e.isDefaultPrevented()) {
            return false;
        }
       e.preventDefault();
       
       frm=$("#frmrModificaUsuario");
       
       var msj=$("#msjregusu");
           msj.removeAttr('class');
           msj.html("");
           
        
        console.log(":::frmrModificaUsuario");
        //return;
       $.ajax({
            type: "POST",
            url: baseurl+"/usuarios/ActModificaUsuario",
            contentType: 'application/json',
            data:JSON.stringify(frm.serializeJSON()),
            success: function(result){
              //  alert(result);
            //  chatWith('9','name');
                if(result==0){
                    alerts(0,msj,"El usuario se modifico con exito");   
                    loadDataTable("#tbusuario");
                    //frm.trigger('reset');
                }else{
                    alerts(2,msj,"No se completo el proceso.. !!!");
                }
                
                
            },
            error: function() {
                //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
                
                alerts(3,msj,"A ocurrido un error interno !!!");
            } 
        });
      //  alert("ajax");
    });
});

  
    