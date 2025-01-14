exports.init = function(key, value, success, error) {
    try {
        console.log("[wisetracker] cordova bridge init");
        cordova.exec(function() {console.log('success')},
                    function() {console.log('error')},
                    "DotCordovaBridge",
                    "initialization",
                    []);
     } catch (e) {
         console.log(e);
     }
          
}

exports.inject = function(key, value, success, error) {
     window.WISETRACKER_SDK_ENV_CODE = 2;
     try {
        console.log("[wisetracker] cordova bridge inject");
        var head= document.getElementsByTagName('head')[0];
        var script= document.createElement('script');
        script.type= 'text/javascript';
        script.src = "plugins/kr.co.wisetracker/www/dop-native-sdk-inf.js";
        head.appendChild(script);
     } catch (e) {
        console.log('error');
        console.log(e);
     }
}