
    $( document ).ready(function() {
        console.log( "ready!" );

        $("#sendGet").click(function(){

         $.ajax({
             url: '/cart/list',
             complete : function(){
                 alert(this.url)
             },
             success: function(result){
             }
         });

        });


    });

