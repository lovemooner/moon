
    $( document ).ready(function() {
        console.log( "ready!" );

        $("#sendGet").click(function(){
          $("#resultDIV").text("");
         $.ajax({
             url: '/papa/cart/list',
             success: function(result){
               $.each(result,function(index,obj){
                  var  tmp = index+" id:"+obj.id;
                   $("#resultDIV").append(tmp).append(",");
               });
             }
         });

        });

         $("#sendMultiReq").click(function(){
              $("#resultDIV").text("");
                 for(var i=0;i<10;i++){
                    sendReq();
                 }
             });

    });


    function sendReq(){
             $.ajax({
                  url: '/papa/cart/list',
                  success: function(result){
                    $.each(result,function(index,obj){
                       var  tmp = index+" id:"+obj.id;
                        $("#resultDIV").append("<hr>").append(tmp).append(",");
                    });
                  }
              });
//         setInterval(function(){
//             //too
//          },1000);

    }

