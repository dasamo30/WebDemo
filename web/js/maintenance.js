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
            //$("#cboCategories").select2();
            $('#cboCategories').select2({
                  placeholder: "Select a category",
                  //allowClear: true,
                  dropdownParent: $('#myModalNewProduct')
                });
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
            { "mData": "msj_alert","sClass": "text-center"},
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
    
    $("#cboSupplier").select2({
                  placeholder: "Select a supplier"
                });
    $("#txtDate").datepicker({
        autoclose:true,
        todayHighlight: true
    });
    
    
    $('#txtProductSearch').autocomplete({
        source: function( request, response ) {
        	 //alert("wsss");       	        	
        	var csrf = $("#_csrf").val();
        	var requestData = { name: request.term};
                
        	if (request.term.length > 1 ) {
        	//console.log("bbbb");
                $.post( baseurl+"/purchaseOrdersController/searchByName" , requestData, function( data ) {
                  //  console.log("aaaaaaaaaa");
                    //console.log("data: " + JSON.stringify(data));	        			
        	response( $.map( data, function( item ) {
                    	 console.log(item);
                      return {                    	                       
                          value			: item.name,
                          label			: item.name,
                          idProduct		: item.id,
                          sellPrice		: item.unit_cost
                      };
                    }));
        			
        		});
        	}else{
        		//Clean the fields
        		$("#txtProductSellPrice").val("");
        		$("#txtProductAmount").val("");
        		$("#txtProductId").val("");
        		$("#txtProductStock").val("");
        	}
        	
        },
        minLength: 2,
        select: function( event, ui ) {
                event.preventDefault();
                $(this).val(ui.item.value);               
                /*$("#txtProductSellPrice").val(ui.item.sellPrice);
                $("#txtProductAmount").val(1);
                $("#txtProductId").val(ui.item.idProduct);
                $("#txtProductStock").val(ui.item.stock);
                $("#txtProductSellPrice").focus();*/
        }
    });
    
    $('#txtProductSearch').select2({
        placeholder: 'Select an item',
        minimumInputLength: 2,
        allowClear: true,
        ajax: {
          type: "POST",   
          url: baseurl+"/purchaseOrdersController/searchByName",
          dataType: 'json',
          delay: 250,
          data: function (params) {

            var queryParameters = {
                term: params.term
            };
            return queryParameters;
        },
        processResults: function (data) {
            return {
                results: $.map(data, function (item) {
                    return {
                        text: item.name,
                        id: item.code,
                        idProduct: item.id,
                        sellPrice: item.unit_cost
                        
                    };
                })
            };
        },
          cache: true
        }
      }).on("change", function () {
         // console.log($(this));
        //var str = $("#s2id_search_code .select2-choice span").text();
         var data = $(this).select2('data')[0];
        console.log(data); 
         if(data){
         $("#txtPriceProduct").val(data.sellPrice);
         $("#txtAmountProduct").val(1);
         $("#txtProductId").val(data.idProduct);
         }
      });
      
      $('#addRowProduct').validator();
     /* $('#myModal').on('shown.bs.modal', function(e) {
           //console.log("ssss");
            $("#msjAddProducts").text("");
        });*/
        
         $("#btnmyModal").click(function(event){
            $("#msjAddProducts").text("");
            $("#txtPriceProduct").val('');
                $("#txtAmountProduct").val('');
                $("#txtProductId").val('');
                //$("#addRowProduct")[0].reset();
                //$('#txtProductSearch').val('').trigger('change');
                $("#txtProductSearch").empty().trigger('change');
                $('#addRowProduct').trigger('reset');
            
             $("#myModal").modal('show');
            
        });
      
      $(document).on("submit","#addRowProduct",function(e){    
      
        if (e.isDefaultPrevented()) {
            return false;
        }
       e.preventDefault();
       
       
       var idProduct = $("#txtProductId").val();  
    	var duplicate = false;
    	$("#tblProducts tbody tr").each(function (indexTr) {
            var data = $(this).data("item");            
            if(data.idProduct == idProduct){
            	duplicate = true;
            }      
        });
        
        if(duplicate == true){
            
            $("#msjAddProducts").text("The product is already added.");
            //alert("")
            return;
        }
       
       
       
       
       console.log("addRowProduct");
       var idProduct = $("#txtProductId").val();	
	var stock = 10;
        var amount=$("#txtAmountProduct").val();
        var sellPrice=$("#txtPriceProduct").val();
       
       var data = {'idProduct' : idProduct, 'sellPrice': sellPrice, 'amount' : amount, 'stock' : stock};
	$("#tblProducts").append(
			"<tr id='item-" + idProduct + "' data-item='" + JSON.stringify(data) + "'>" +
			"	<td>" + $('#txtProductSearch').select2('data')[0].text+ "</td>" +
			"	<td class='text-xs-right'><span id='lblSellItem" + idProduct + "'>" + (sellPrice * 1) + "</span><input id='txtSelltItem" + idProduct + "' class='form-control numbersOnly' size='10' style='display: none;' type='text'></td>" +
			"	<td class='text-xs-right'><span id='lblAmountItem" + idProduct + "'>" + (amount * 1) + "</span><input id='txtAmountItem" + idProduct + "' class='form-control numbersOnly' size='10' style='display: none;' type='text'></td>" +
			"	<td class='text-xs-right'><span id='lblSaleItem" + idProduct + "'>" + (amount * sellPrice * 1)  + "</span></td>" +
			/*"	<td class='text-xs-right'>" +
			"		<button id='btnEditItem" + idProduct + "' class='btn btn-info btn-xs btnEditItem' role='button' data-id='"+idProduct+"' > " +
			"			<span class='glyphicon glyphicon-pencil'></span> " +
			"		 </button> " +
			"		<button id='btnSaveItem" + idProduct + "' class='btn btn-inverse btn-xs btnSaveItem' role='button' data-id='"+idProduct+"' style='display: none;'> " +
			"			<span class='glyphicon glyphicon-ok'></span> " +
			"		 </button> " +
			"	</td>" +*/
			"	<td class='text-xs-right'>" + 
			"		<button id='btnDeleteItem" + idProduct + "' class='btn btn-danger btn-xs btnDeleteItem' role='button' data-id='"+idProduct+"' > " +
			"			<span class='glyphicon glyphicon-trash'></span> " +
			"		 </button> " +
			"	</td>" +
			"</tr>");
                
                calculateTotalTransfer();
                $('#myModal').modal('hide');
                //$('#txtProductSearch').select2('val','');
                //$('#txtProductSearch').select2('data', null);
                //$('#txtProductSearch').select2('val','');
                $('#txtProductSearch').val('').trigger('change'); 
                //$("#txtProductSearch").select2('data', null)
                //$('#txtProductSearch').change();
                //$("#txtProductSearch").empty();
                //$("#txtProductSearch option").remove();
                //$("#txtProductSearch").val(null).trigger("change");
                $("#txtPriceProduct").val('');
                $("#txtAmountProduct").val('');
                $("#txtProductId").val('');
                $("#addRowProduct")[0].reset();
                $("#msjtblProducts").text("");

       
      });
      
       calculateTotalTransfer=function(){
	
	var totalSale = 0;
	$("#tblProducts tbody tr").each(function (indexTr) {
        var amout;
        var price;        
        
        $(this).children("td").each(function (indexTd) 
        {
            switch (indexTd) 
            {                
                case 1: 
                	amount = $(this).text();
                        break;
                case 2: 
                	price = $(this).text();
                        break;
            }
           // $(this).css("background-color", "#FFFAA8");
        });
        totalSale = totalSale + ((amount * 1)  * (price * 1));        
        });
    
	$("#lblTotalSale").text(totalSale);
        };
        
       $(document).on("click",".btnDeleteItem",function(e){
           console.log("btnDeleteItem")
           var id = $(this).data('id');
               $('#tblProducts #item-' + id).remove();
        	calculateTotalTransfer();
           
       });
       
       $(document).on("click",".btnEditItem",function(e){
           console.log("btnEditItem")
           var id = $(this).data('id');
            $("#lblAmountItem" + id).hide();
            $("#lblSellItem" + id).hide();
            $("#btnDeleteItem" + id).hide();
            $("#btnSaveItem" + id).show();
            $("#btnEditItem" + id).hide();

            $("#txtSelltItem" + id).val($("#lblSellItem" + id).text());	
            $("#txtSelltItem" + id).show();
            $("#txtAmountItem" + id).val($("#lblAmountItem" + id).text());	
            $("#txtAmountItem" + id).show();
           
       });
       
       $(document).on("click",".btnSaveItem",function(e){
           console.log("btnSaveItem")
           var id = $(this).data('id');
           var price = $("#txtSelltItem" + id).val();
	var amount = $("#txtAmountItem" + id).val();
           
           
           $("#txtSelltItem" + id).hide();		
	$("#txtAmountItem" + id).hide();
	
	
	$("#lblAmountItem" + id).text(amount * 1);
	$("#lblAmountItem" + id).show();
	$("#lblSellItem" + id).text(price * 1);
	$("#lblSaleItem" + id).text(amount * price);
	$("#lblSellItem" + id).show();
	
	$("#btnDeleteItem" + id).show();
	$("#btnSaveItem" + id).hide();
	$("#btnEditItem" + id).show();
	calculateTotalTransfer();
           
       });
       
       //btnSavePurchaseOrder
       $(document).on("click","#btnSavePurchaseOrder",function(e){
          savePurchaseOrder(); 
           
       });
       
       $("#form_pocab").validator();
       savePurchaseOrder=function(e){
           
           //form_pocab
           // e.preventDefault();
        //$("#form_pocab").submit();
        //console.log($('#form_pocab'));
        //console.log(":::::: "+$('#form_pocab')[0].checkValidity());
        if(!$('#form_pocab')[0].checkValidity()){
            $('#submit').click();
        }
         
        
        //e.preventDefault();
        //$('input:text[required]').parent().show();
	//$('#txtDate')	
	//validations of information.	
	/*if ($("#cboSupplier").val() == 0) {
		$("#txtDialogMessage").text("Select a supplier.");
		$("#dialogConfirm").dialog({
	        resizable: false,
	        height: "auto",
	        width: 400,
	        modal: true,
	        buttons: {
	          "Ok": function() {
	        	$(this).dialog("close");	        	
	          }
	        }
	    });	
		return;
	}
		
	if ($("#txtDate").val() == "") {
		$("#txtDialogMessage").text("Select a date.");
		$("#dialogConfirm").dialog({
	        resizable: false,
	        height: "auto",
	        width: 400,
	        modal: true,
	        buttons: {
	          "Ok": function() {
	        	$(this).dialog("close");	        	
	          }
	        }
	    });	
		return;
	}*/
		
	//details
	var rows =0;
	var dataDetails = []; 
	$("#tblProducts tbody tr").each(function (indexTr) {
		rows = rows + 1;
		var item={};
		var data =  $(this).data("item");	
				
		item['product'] = {id: data.idProduct};
		item['amount'] =  data.amount;
		item['costPrice'] = data.sellPrice; 
		dataDetails.push(item);
    });
		
	if (rows == 0) {
		$("#msjtblProducts").text("You need to add a product.");
                //$("#msjtblProducts").addClass("help-block has-error");
                //class="help-block with-errors"
                
		/*$("#dialogConfirm").dialog({
	        resizable: false,
	        height: "auto",
	        width: 400,
	        modal: true,
	        buttons: {
	          "Ok": function() {
	        	$(this).dialog("close");	        	
	          }
	        }
	    });	*/
		return;
	}
	
	var csrf = $("#_csrf").val();
	var date = $("#txtDate").val().trim();	
	date = date.substring(6,10) + '-' + date.substring(0,2) + '-' + date.substring(3,5) ;
	//"yyyy-MM-dd'T'HH:mm:ss.SSSZ
	
	
	var purchaseOrder = {dateCreation: date, supplier: { id_supplier: $("#cboSupplier").val()}, amount: $("#lblTotalSale").text(), details: dataDetails};
        
        
        console.log(JSON.stringify(purchaseOrder));
        //return false;
        
           $.ajax({
            type: "POST",
            url: baseurl+"/purchaseOrdersController/ActSavePurchaseOrder",
           // contentType: 'application/json; charset=utf-8',
            data:{ data: JSON.stringify(purchaseOrder)},
            success: function(result){
                //alert(result);
                if(result==0){
                        //alerts(0,msj,"");   
                        //loadDataTable("#tbSuppliers");
                        ezBSAlert({ headerText:"success", messageText: "El proveedor se elmino con exito", alertType: "success"});
                        $("#txtDate").val("");
                	//$("#cboSupplier").val("");
                        $("#cboSupplier").empty().trigger('change');
                	$("#tblProducts tbody").empty();
                        $('#form_pocab').trigger('reset');
                        
                    }else{
                       // alerts(2,msj,"No se completo el proceso.. !!!");
                        ezBSAlert({ headerText:"Error",messageText: "No se completo el proceso.. !!!", alertType: "danger"});
                    }
                
            },
            error: function() {
                //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
                
                
            } 
        });
        /*
        $.post( baseurl+"/purchaseOrdersController/ActSavePurchaseOrder" , {_csrf: '577as', data: JSON.stringify(purchaseOrder)}, function( data ) {    			
                
                $("#txtDialogMessage").text(data.description);
                if (data.state == 201 || data.state == 200) {
                	//clean the fields
                	$("#txtDate").val("");
                	$("#lblTotalSale").text("0.00");
                	$("#cboSupplier").val("0");
                	$("#tblProducts tbody").empty();
                	
				}
    		});*/
	
	/*$("#txtDialogMessage").text("You want to save the purchase order?");
	$("#dialogConfirm").dialog({    	
        resizable: false,
        height: "auto",
        width: 400,
        modal: true,
        buttons: {
          "Ok": function() {
        	$(this).dialog("close");
        	//Save the transfer by ajax.
        	
        	        	
        	
          },
          "Cancel": function() {
            $(this).dialog("close");
          }
        }
      });*/
	
};

         var dataTablepo = $('#tbPurchaseOrder').DataTable({
           processing: true,
            //"serverSide": true,
            responsive: true,
            autoWidth: false,
            order: [],
            ajax:{
                url :baseurl+"/purchaseOrdersController/ActListPurchaseOrder", // json datasource
                type: "post",  // method  , by default get
                complete: function(){
                   table=$('#tbPurchaseOrder');
                  //alert(dataTable);
                  //console.log(table.parent());
                  table.parent().addClass("table-responsive");
                 // table.parent().addClass('table-responsive');//.removeClass('col-sm-12');
                },
                error: function(xhr, textStatus, errorThrown){  // error handling
                   
                    $("#tbPurchaseOrder_wrapper").html("");
                    $("#tbPurchaseOrder_wrapper").append('<div class="alert alert-danger alert-dismissable"><tr><th colspan="3">No data found in the server</th></tr></div>'); 
                }
            },
            "aoColumns": [
            { "mData": "id" },
            { "mData": "code_suppliers" },
            { "mData": "name_suppliers" },
            { "mData": "username" },
            { "mData": "amount"},
            { "mData": "dateCreation","sClass": "text-center"},
            { "mData": "ico_search","sClass": "text-center"},
            { "mData": "ico_delete","sClass": "text-center"},
            { "mData": "ico_print","sClass": "text-center"}
            //{ "sClass": "a-right", "aTargets": [ 4 ] }
            
            ]
    });
      /*
    
    editItem=function(id) {
		
	$("#lblAmountItem" + id).hide();
	$("#lblSellItem" + id).hide();
	$("#btnDeleteItem" + id).hide();
	$("#btnSaveItem" + id).show();
	$("#btnEditItem" + id).hide();
	
	$("#txtSelltItem" + id).val($("#lblSellItem" + id).text());	
	$("#txtSelltItem" + id).show();
	$("#txtAmountItem" + id).val($("#lblAmountItem" + id).text());	
	$("#txtAmountItem" + id).show();
		
}*/
//-------------------------final del documento--------------------------------------------------//    
});