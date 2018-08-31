
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


function jsonp(url, callback) {
    var callbackName = 'jsonp_callback_' + Math.round(100000 * Math.random());
    window[callbackName] = function(data) {
        delete window[callbackName];
        document.body.removeChild(script);
        callback(data);
    };

    var script = document.createElement('script');
    script.src = url + (url.indexOf('?') >= 0 ? '&' : '?') + 'callback=' + callbackName;
    document.body.appendChild(script);
}


function doSend(){
 jsonp('http://slc11fsp.us.oracle.com:8080/papa/', function(data) {
    alert(data);
 });
}

doSend();

