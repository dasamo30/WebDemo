jQuery(document).ready( function () {
   
    console.log("maintenance.js");
    /*
        $.validator.addMethod('decimal', function(value, element) {
        return this.optional(element) || /^\d+(\.\d{2,2})?$/.test(value); 
        }, "Wrong number format, please enter xx.xx value");
    */
    
    $('#myModalNewProduct').on('show.bs.modal', function (e) { 
        
        //console.log(e);
        var btn = $(e.relatedTarget);
        //console.log(btn.attr("id"));
        var idProduct=btn.data('id');
        
        //console.log("confirma::"+idProduct);
        
       // alert(link.attr("href"));
       var data=null;
       var title="Register Product";
       var frm='#frmrRegisterProduct';
       
       if(btn.attr("id")==="btnViewEditProduc"){ 
            data={"product_id":idProduct};
            title="Modify Product";
            frm='#frmModifProduct';
       }
       
        $.post(btn.attr("href"),data, function( data ) { 
            $('#myModalLabel').html(title);
            $('#modal-body').html(data); 
            
            $(frm).validator();
            /*$('#frmrRegisterProduct').validator({
                a:{
                    validators:{
                    notEmpty:{
                    message: 'This field is required and cannot be empty'
                    }}
                }});*/
            //do somethings that i want 
            
        }); 
        
    });
    
    /***********************************/

    //$("#file").change(function() {
    $(document).on("change","#file",function(e){    
        console.log("change file");
        $("#message").empty(); // To remove the previous error message
        var file = this.files[0];
        var imagefile = file.type;
        var match = ["image/jpeg", "image/png", "image/jpg"];
        if (!((imagefile == match[0]) || (imagefile == match[1]) || (imagefile == match[2])))
        {
            $('#previewing').attr('src', 'noimage.png');
            $("#message").html("<p id='error'>Please Select A valid Image File</p>" + "<h4>Note</h4>" + "<span id='error_message'>Only jpeg, jpg and png Images type allowed</span>");
            return false;
        }
        else
        {
            var reader = new FileReader();
            reader.onload = imageIsLoaded;
            reader.readAsDataURL(this.files[0]);
        }
    });
    
    function imageIsLoaded(e) {
        $("#file").css("color","green");
        $('#image_preview').css("display", "block");
        $('#previewing').attr('src', e.target.result);
        //console.log(e.target.result);
        /*$('#previewing').attr('width', '250px');
        $('#previewing').attr('height', '230px');*/
    };

    /**********************************/
   $(document).on("submit","#frmrRegisterProduct",function(e){    
      
        if (e.isDefaultPrevented()) {
            return false;
        }
        e.preventDefault();
        
        var frm=$("#frmrRegisterProduct");
        var msj=$("#msjregprod");
           msj.removeAttr('class');
           msj.html("");
           
        console.log(this);
        
        //var post_url = $(this).attr("action"); //get form action url
        //var request_method = $(this).attr("method"); //get form GET/POST method
	var form_data = new FormData(this); //Creates new FormData object
        
        $.ajax({
            url: baseurl+"/ProductController/ActRegisterProduct",
            type:"POST",
            data : form_data,
            dataType : "json",
            contentType: false,
            cache: false,
            processData:false,
            success: function(result){
              //  alert(result);
            //  chatWith('9','name');
                if(result==0){
                    alerts(0,msj,"El producto se registro con exito");   
                    loadDataTable("#tbProducts");
                    frm.trigger('reset');
                    $("#file").css("color","black");
                    $('#image_preview').css("display", "none");
                    $('#previewing').removeAttr("src")       //attr('src', e.target.result);
                }else if(result==1){
                    alerts(2,msj,"El producto ya existe");
                }else{
                    alerts(3,msj,"A ocurrido un error interno !!!");
                }
                
                
            },
            error: function() {
                //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
                
                alerts(3,msj,"A ocurrido un error interno !!!");
            } 
        });
    });
    //frmModifProduct
    $(document).on("submit","#frmModifProduct",function(e){    
      
        if (e.isDefaultPrevented()) {
            return false;
        }
       
       
       
       frm=$("#frmModifProduct");
       e.preventDefault();
       var msj=$("#msjregprod");
           msj.removeAttr('class');
           msj.html("");
       var form_data = new FormData(this);    
           
       $.ajax({
            type: "POST",
            url: baseurl+"/ProductController/ActModifyProduct",
            data : form_data,
            dataType : "json",
            contentType: false,
            cache: false,
            processData:false,
            success: function(result){
              //  alert(result);
            //  chatWith('9','name');
                if(result==0){
                    alerts(0,msj,"El producto se modifico con exito");   
                    loadDataTable("#tbProducts");
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
      
    });
    
    
    var dataTablep = $('#tbProducts').DataTable({
           processing: true,
            //"serverSide": true,
            responsive: true,
            autoWidth: false,
            order: [],
            ajax:{
                url :baseurl+"/ProductController/ActListProduct", // json datasource
                type: "post",  // method  , by default get
                complete: function(){
                   table=$('#tbProducts');
                  //alert(dataTable);
                  //console.log(table.parent());
                  table.parent().addClass("table-responsive");
                 // table.parent().addClass('table-responsive');//.removeClass('col-sm-12');
                },
                error: function(xhr, textStatus, errorThrown){  // error handling
                   
                    $("#tbProducts_wrapper").html("");
                    $("#tbProducts_wrapper").append('<div class="alert alert-danger alert-dismissable"><tr><th colspan="3">No data found in the server</th></tr></div>'); 
                }
            },
            "aoColumns": [
            { "mData": "code" },
            { "mData": "name"},
            { "mData": "description"},
            { "mData": "unit_cost","sClass": "text-center"},
            { "mData": "registration_date","sClass": "text-center"},
            { "mData": "ico_img","sClass": "text-center"},
            { "mData": "ico_edit","sClass": "text-center"},
            { "mData": "ico_delete","sClass": "text-center"}
            //{ "sClass": "a-right", "aTargets": [ 4 ] }
            
            ]/*,
            "columnDefs": [
            { className: "text-center", "targets": [4,5] }
            //{ className: "text-nowrap", "targets": [0,1] }
          ]*/
    });
    
   /* $(document).on( "click", ".btndelProduct", function(e) {     
        e.preventDefault();
        alert("eliminar");
    });*/
    
    //$('#btnDeletetProduct').click(function(e){
    
      $("#btnAlert").on("click", function(){  	
   
  });
    
    $(document).on("click","#btnDeletetProduct",function(e){    

        var obj = this;
        ezBSAlert({
        type: "confirm",
        headerText:"Confirm",
        messageText: "Are you sure about this ?",
        alertType: "info"
        }).done(function (e) {
          var idProduct = $(obj).data('id');
          //console.log("confirma::"+idProduct);
          //var url =baseurl+"/usuarios/ActEliminarUsuario";
          if(e){
              $.ajax({
                url: baseurl+"/ProductController/ActDeleteProduct",
                type: 'POST',
                data: { idProduct:idProduct} ,
                //contentType: 'application/json; charset=utf-8',
                success: function (result) {
                    if(result==0){
                        //alerts(0,msj,"");   
                        loadDataTable("#tbProducts");
                        ezBSAlert({ headerText:"success", messageText: "El producto se elmino con exito", alertType: "success"});
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
        
        
      /*  
      ezBSAlert({
        messageText: "You entered: " + e,
        alertType: "success"
      });*/
        
        
        return false;
        var anchor = this;
        var ID = $(this).data('id');
        var del_link = '/admin/edit/user/delete/' + $(anchor).attr('user');
       // $('#confirmDelete').modal('show');
        $('button#ezok-btn').click(function(e){
            console.log("confirma::"+ID);
            $('#confirmDelete').modal('hide');
            /*
            $.ajax({
                url: del_link,
                success:function(result){
                    $(anchor).closest('tr').addClass("error");
                    $(anchor).closest('tr').delay(2000).fadeOut();
              }});*/
        });
        return false;
    });
    
    $(document).on("click","#btnViewEditProducsssss",function(e){
        
                var md=$('#myModalNewProduct');
                //$('#modal-body').html("editarmeos");
                /*md.load(baseurl+"/ProductController/ActViewModifProduct",{'id1': '1', 'id2': '2'},
                function(){
                   // md.modal('show');
                });*/
                //md.remove();
                //md.preventDefault();
                /*
                var $this = $(this)
                  , $remote = $this.data('remote') || $this.attr('href')
                  , $modal = $('<div class="modal" id="ajaxModal"><div class="modal-body"></div></div>');
                $('body').append($modal);
                $modal.modal({backdrop: 'static', keyboard: false});
                $modal.load($remote);*/
        
        
        
    });
    /*
    $('[data-toggle="ajaxModal"]').on('click',
              function(e) {
                $('#myModalNewProduct').remove();
                e.preventDefault();
                var $this = $(this)
                  , $remote = $this.data('remote') || $this.attr('href')
                  , $modal = $('<div class="modal" id="ajaxModal"><div class="modal-body"></div></div>');
                $('body').append($modal);
                $modal.modal({backdrop: 'static', keyboard: false});
                $modal.load($remote);
              }
            );*/
    /*
    $('#myModalNewProduct').on('show.bs.modal', function (e) { 
        var link = $(e.relatedTarget);
       // alert(link.attr("href"));
        $.get(link.attr("href"), function( data ) { 
            $('#modal-body').html(data); 
            $('#frmrRegisterProduct').validator();
            //do somethings that i want 
            
        });*/
    /*
     $('#confirmDelete').on('show.bs.modal', function(e) {
            
         $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
            
            $('.debug-url').html('Delete URL: <strong>' + $(this).find('.btn-ok').attr('href') + '</strong>');
        });
    
    */
    /*
    $('#confirmDelete').on('show.bs.modal', function (e) {
        
         $message = $(e.relatedTarget).attr('data-message');
         $(this).find('.modal-body p').text($message);
         $title = $(e.relatedTarget).attr('data-title');
         $(this).find('.modal-title').text($title);

         // Pass form reference to modal for submission on yes/ok
         var form = $(e.relatedTarget).closest('form');
         $(this).find('.modal-footer #confirm').data('form', form);
     });*/
/*
     $('#confirmDelete').find('.modal-footer #confirm').on('click', function(){
         alert("sssss");
         //$(this).data('form').submit();
     });*/

    //btndelProduct
    
    var $modal = $('#load_popup_modal_show_id');
    $(document).on("click","#click_to_load_modal_popup",function(e){
        
       // $('#click_to_load_modal_popup').on('click', function(){
            console.log("sdsdsdsd");
            $modal.load(baseurl+"/ProductController/ActViewModifProduct",{'id1': '1', 'id2': '2'},
            function(){
                $modal.modal('show');
            });

    });
    
//--------------------------location-----------------------------------------//

    $('#myModalNewLocation').on('show.bs.modal', function (e) { 
        
        //console.log(e);
        var btn = $(e.relatedTarget);
        var idLocation=btn.data('id');
       var data=null;
       var title="Register Location";
       var frm='#frmrRegisterLocation';
       
       if(btn.attr("id")==="btnViewEditLocation"){ 
           console.log("btnViewEditLocation");
            data={"location_id":idLocation};
            title="Modify Location";
            frm='#frmModifLocation';
       }
        $.post(btn.attr("href"),data, function( data ) { 
            $('#myModalLabel').html(title);
            $('#modal-body').html(data); 
            $(frm).validator();
        }); 
    });
    
    $(document).on("submit","#frmrRegisterLocation",function(e){    
      
        if (e.isDefaultPrevented()) {
            return false;
        }
       e.preventDefault();
       
       frm=$("#frmrRegisterLocation");
       
       var msj=$("#msjmntlocation");
           msj.removeAttr('class');
           msj.html("");
           
        
        console.log(":::frmrRegisterLocation");
        //return;
       $.ajax({
            type: "POST",
            url: baseurl+"/locationController/ActRegisterLocation",
            contentType: 'application/json',
            data:JSON.stringify(frm.serializeJSON()),
            success: function(result){
              //  alert(result);
            //  chatWith('9','name');
                if(result==0){
                    alerts(0,msj,"la ubicacion se grabo con exito");   
                    loadDataTable("#tbLocation");
                    frm.trigger('reset');
                }else{
                    alerts(2,msj,"El ubicacion ya existe");
                }
                
                
            },
            error: function() {
                //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
                
                alerts(3,msj,"A ocurrido un error interno !!!");
            } 
        });
      //  alert("ajax");
    });
    //$.fn.dataTable.ext.errMode = 'console';
    //tbLocation
    var dataTablep = $('#tbLocation').DataTable({
           processing: true,
            //"serverSide": true,
            responsive: true,
            autoWidth: false,
            order: [],
            ajax:{
                url :baseurl+"/locationController/ActListLocation", // json datasource
                type: "post",  // method  , by default get
                complete: function(){
                   table=$('#tbLocation');
                  //alert(dataTable);
                  //console.log(table.parent());
                  table.parent().addClass("table-responsive");
                 // table.parent().addClass('table-responsive');//.removeClass('col-sm-12');
                },
                error: function(xhr, textStatus, errorThrown){  // error handling
                   
                    $("#tbLocation_wrapper").html("");
                    $("#tbLocation_wrapper").append('<div class="alert alert-danger alert-dismissable"><tr><th colspan="3">No data found in the server</th></tr></div>'); 
                }
            },
            "aoColumns": [
            { "mData": "name_location" },
            { "mData": "date_creation","sClass": "text-center"},
            { "mData": "date_modification","sClass": "text-center"},
            { "mData": "ico_edit","sClass": "text-center"},
            { "mData": "ico_delete","sClass": "text-center"}
            //{ "sClass": "a-right", "aTargets": [ 4 ] }
            
            ]/*,
            "columnDefs": [
            { className: "text-center", "targets": [4,5] }
            //{ className: "text-nowrap", "targets": [0,1] }
          ]*/
    });
    
    $(document).on("submit","#frmModifLocation",function(e){    
      
        if (e.isDefaultPrevented()) {
            return false;
        }
       e.preventDefault();
       
       frm=$("#frmModifLocation");
       
       var msj=$("#msjmntlocation");
           msj.removeAttr('class');
           msj.html("");
           
        
        console.log(":::frmModifLocation");
        //return;
       $.ajax({
            type: "POST",
            url: baseurl+"/locationController/ActModifyLocation",
            contentType: 'application/json',
            data:JSON.stringify(frm.serializeJSON()),
            success: function(result){
              //  alert(result);
            //  chatWith('9','name');
                if(result==0){
                    alerts(0,msj,"la ubicacion se modifico con exito");   
                    loadDataTable("#tbLocation");
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
    //$.fn.dataTable.ext.errMode = 'console';
    //tbLocation
    var dataTablep = $('#tbLocation').DataTable({
           processing: true,
            //"serverSide": true,
            responsive: true,
            autoWidth: false,
            order: [],
            ajax:{
                url :baseurl+"/locationController/ActListLocation", // json datasource
                type: "post",  // method  , by default get
                complete: function(){
                   table=$('#tbLocation');
                  //alert(dataTable);
                  //console.log(table.parent());
                  table.parent().addClass("table-responsive");
                 // table.parent().addClass('table-responsive');//.removeClass('col-sm-12');
                },
                error: function(xhr, textStatus, errorThrown){  // error handling
                   
                    $("#tbLocation_wrapper").html("");
                    $("#tbLocation_wrapper").append('<div class="alert alert-danger alert-dismissable"><tr><th colspan="3">No data found in the server</th></tr></div>'); 
                }
            },
            "aoColumns": [
            { "mData": "name_location" },
            { "mData": "date_creation","sClass": "text-center"},
            { "mData": "date_modification","sClass": "text-center"},
            { "mData": "ico_edit","sClass": "text-center"},
            { "mData": "ico_delete","sClass": "text-center"}
            //{ "sClass": "a-right", "aTargets": [ 4 ] }
            
            ]/*,
            "columnDefs": [
            { className: "text-center", "targets": [4,5] }
            //{ className: "text-nowrap", "targets": [0,1] }
          ]*/
    });
    $(document).on("click","#btnDeletetLocation",function(e){    

        var obj = this;
        ezBSAlert({
        type: "confirm",
        headerText:"Confirm",
        messageText: "Are you sure about this ?",
        alertType: "warning"
        }).done(function (e) {
          var idLocation = $(obj).data('id');
          //console.log("confirma::"+idProduct);
          //var url =baseurl+"/usuarios/ActEliminarUsuario";
          if(e){
              $.ajax({
                url: baseurl+"/locationController/ActDeleteLocation",
                type: 'POST',
                data: { idLocation:idLocation} ,
                //contentType: 'application/json; charset=utf-8',
                success: function (result) {
                    if(result==0){
                        //alerts(0,msj,"");   
                        loadDataTable("#tbLocation");
                        ezBSAlert({ headerText:"success", messageText: "La ubicacion se elmino con exito", alertType: "success"});
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
//--------------------------category-----------------------------------------//
  $('#myModalNewCategory').on('show.bs.modal', function (e) { 
        
        console.log("myModalNewCategory");
        
        var btn = $(e.relatedTarget);
        var idCategory=btn.data('id');
       var data=null;
       var title="Register Category";
       var frm='#frmrRegisterCategory';
       
       if(btn.attr("id")==="btnViewEditCategory"){ 
           console.log("btnViewEditCategory");
            data={"idCategory":idCategory};
            title="Modify Category";
            frm='#frmModifCategory';
       }
        $.post(btn.attr("href"),data, function( data ) { 
            $('#myModalLabel').html(title);
            $('#modal-body').html(data); 
            $(frm).validator();
        }); 
    });
    
    
    $(document).on("submit","#frmrRegisterCategory",function(e){    
      
        if (e.isDefaultPrevented()) {
            return false;
        }
       e.preventDefault();
       
       frm=$("#frmrRegisterCategory");
       
       var msj=$("#msjmntcategory");
           msj.removeAttr('class');
           msj.html("");
           
        
        console.log(":::frmrRegisterCategory");
        //return;
       $.ajax({
            type: "POST",
            url: baseurl+"/categoryController/ActRegisterCategory",
            contentType: 'application/json',
            data:JSON.stringify(frm.serializeJSON()),
            success: function(result){
              //  alert(result);
            //  chatWith('9','name');
                if(result==0){
                    alerts(0,msj,"la categoria se grabo con exito");   
                    loadDataTable("#tbCategories");
                    frm.trigger('reset');
                }else{
                    alerts(2,msj,"El categoria ya existe");
                }
                
                
            },
            error: function() {
                //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
                
                alerts(3,msj,"A ocurrido un error interno !!!");
            } 
        });
    });
    
    var dataTablep = $('#tbCategories').DataTable({
           processing: true,
            //"serverSide": true,
            responsive: true,
            autoWidth: false,
            order: [],
            ajax:{
                url :baseurl+"/categoryController/ActListCategories", // json datasource
                type: "post",  // method  , by default get
                complete: function(){
                   table=$('#tbCategories');
                  //alert(dataTable);
                  //console.log(table.parent());
                  table.parent().addClass("table-responsive");
                 // table.parent().addClass('table-responsive');//.removeClass('col-sm-12');
                },
                error: function(xhr, textStatus, errorThrown){  // error handling
                   
                    $("#tbCategories_wrapper").html("");
                    $("#tbCategories_wrapper").append('<div class="alert alert-danger alert-dismissable"><tr><th colspan="3">No data found in the server</th></tr></div>'); 
                }
            },
            "aoColumns": [
            { "mData": "name_category" },
            { "mData": "date_creation","sClass": "text-center"},
            { "mData": "date_modification","sClass": "text-center"},
            { "mData": "ico_edit","sClass": "text-center"},
            { "mData": "ico_delete","sClass": "text-center"}
            //{ "sClass": "a-right", "aTargets": [ 4 ] }
            
            ]
    });
    
    $(document).on("click","#btnDeleteCategory",function(e){    

        var obj = this;
        ezBSAlert({
        type: "confirm",
        headerText:"Confirm",
        messageText: "Are you sure about this ?",
        alertType: "warning"
        }).done(function (e) {
          var idCategory = $(obj).data('id');
          //console.log("confirma::"+idProduct);
          //var url =baseurl+"/usuarios/ActEliminarUsuario";
          if(e){
              $.ajax({
                url: baseurl+"/categoryController/ActDeleteCategory",
                type: 'POST',
                data: { idCategory:idCategory} ,
                //contentType: 'application/json; charset=utf-8',
                success: function (result) {
                    if(result==0){
                        //alerts(0,msj,"");   
                        loadDataTable("#tbCategories");
                        ezBSAlert({ headerText:"success", messageText: "La categoria se elmino con exito", alertType: "success"});
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
    
    
    $(document).on("submit","#frmModifCategory",function(e){    
      
        if (e.isDefaultPrevented()) {
            return false;
        }
       e.preventDefault();
       
       frm=$("#frmModifCategory");
       
       var msj=$("#msjmntcategory");
           msj.removeAttr('class');
           msj.html("");
           
        
        console.log(":::frmModifCategory");
        //return;
       $.ajax({
            type: "POST",
            url: baseurl+"/categoryController/ActModifyCategory",
            contentType: 'application/json',
            data:JSON.stringify(frm.serializeJSON()),
            success: function(result){
              //  alert(result);
            //  chatWith('9','name');
                if(result==0){
                    alerts(0,msj,"la categoria se modifico con exito");   
                    loadDataTable("#tbCategories");
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

//--------------------------category-----------------------------------------//

    $('#myModalNewSupplier').on('show.bs.modal', function (e) { 
        
        console.log("myModalNewSupplier");
        
        var btn = $(e.relatedTarget);
        var idSupplier=btn.data('id');
       var data=null;
       var title="Register supplier";
       var frm='#frmrRegisterSupplier';
       
       if(btn.attr("id")==="btnViewEditSupplier"){ 
           console.log("btnViewEditCategory");
            data={"idSupplier":idSupplier};
            title="Modify supplier";
            frm='#frmModifSupplier';
       }
        $.post(btn.attr("href"),data, function( data ) { 
            $('#myModalLabel').html(title);
            $('#modal-body').html(data); 
            $(frm).validator();
        }); 
    });
    
    
    $(document).on("submit","#frmrRegisterSupplier",function(e){    
      
        if (e.isDefaultPrevented()) {
            return false;
        }
       e.preventDefault();
       
       frm=$("#frmrRegisterSupplier");
       
       var msj=$("#msjmntsupplier");
           msj.removeAttr('class');
           msj.html("");
           
        
        console.log(":::frmrRegisterSupplier");
        //return;
       $.ajax({
            type: "POST",
            url: baseurl+"/supplierController/ActRegisterSupplier",
            contentType: 'application/json',
            data:JSON.stringify(frm.serializeJSON()),
            success: function(result){
              //  alert(result);
            //  chatWith('9','name');
                if(result==0){
                    alerts(0,msj,"la proveedor se grabo con exito");   
                    loadDataTable("#tbSuppliers");
                    frm.trigger('reset');
                }else{
                    alerts(2,msj,"El proveedor ya existe");
                }
                
                
            },
            error: function() {
                //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
                
                alerts(3,msj,"A ocurrido un error interno !!!");
            } 
        });
    });
    
    var dataTablep = $('#tbSuppliers').DataTable({
           processing: true,
            //"serverSide": true,
            responsive: true,
            autoWidth: false,
            order: [],
            ajax:{
                url :baseurl+"/supplierController/ActListSuppliers", // json datasource
                type: "post",  // method  , by default get
                complete: function(){
                   table=$('#tbSuppliers');
                  //alert(dataTable);
                  //console.log(table.parent());
                  table.parent().addClass("table-responsive");
                 // table.parent().addClass('table-responsive');//.removeClass('col-sm-12');
                },
                error: function(xhr, textStatus, errorThrown){  // error handling
                   
                    $("#tbSuppliers_wrapper").html("");
                    $("#tbSuppliers_wrapper").append('<div class="alert alert-danger alert-dismissable"><tr><th colspan="3">No data found in the server</th></tr></div>'); 
                }
            },
            "aoColumns": [
            { "mData": "code_suppliers" },
            { "mData": "name_suppliers" },
            { "mData": "phone_number" },
            { "mData": "web" },
            { "mData": "date_creation","sClass": "text-center"},
            { "mData": "date_modification","sClass": "text-center"},
            { "mData": "ico_edit","sClass": "text-center"},
            { "mData": "ico_delete","sClass": "text-center"}
            //{ "sClass": "a-right", "aTargets": [ 4 ] }
            
            ]
    });
    
    
    $(document).on("click","#btnDeleteSupplier",function(e){    

        var obj = this;
        ezBSAlert({
        type: "confirm",
        headerText:"Confirm",
        messageText: "Are you sure about this ?",
        alertType: "warning"
        }).done(function (e) {
          var idSupplier = $(obj).data('id');
          //console.log("confirma::"+idProduct);
          //var url =baseurl+"/usuarios/ActEliminarUsuario";
          if(e){
              $.ajax({
                url: baseurl+"/supplierController/ActDeleteSupplier",
                type: 'POST',
                data: { idSupplier:idSupplier} ,
                //contentType: 'application/json; charset=utf-8',
                success: function (result) {
                    if(result==0){
                        //alerts(0,msj,"");   
                        loadDataTable("#tbSuppliers");
                        ezBSAlert({ headerText:"success", messageText: "El proveedor se elmino con exito", alertType: "success"});
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
    
    $(document).on("submit","#frmModifSupplier",function(e){    
      
        if (e.isDefaultPrevented()) {
            return false;
        }
       e.preventDefault();
       
       frm=$("#frmModifSupplier");
       
       var msj=$("#msjmntsupplier");
           msj.removeAttr('class');
           msj.html("");
           
        
        console.log(":::frmModifSupplier");
        //return;
       $.ajax({
            type: "POST",
            url: baseurl+"/supplierController/ActModifySupplier",
            contentType: 'application/json',
            data:JSON.stringify(frm.serializeJSON()),
            success: function(result){
              //  alert(result);
            //  chatWith('9','name');
                if(result==0){
                    alerts(0,msj,"El proveedor se modifico con exito");   
                    loadDataTable("#tbSuppliers");
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
    
    $(".select2").select2();
    $("#txtDate").datepicker({
        autoclose:true,
        todayHighlight: true
    });
    
//-------------------------final del documento--------------------------------------------------//    
});