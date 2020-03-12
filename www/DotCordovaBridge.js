exports.init = function(key, value, success, error) {
    try {
        cordova.exec(function() {console.log('success')},
                    function() {console.log('error')},
                    "DotCordovaBridge",
                    "initialization",
                    []);
     } catch (e) {
         console.log(e);
     }
          
  }
  exports.injecting = function(key, value, success, error) {
        window.WISETRACKER_SDK_ENV_CODE = 2;
        try {
           var head= document.getElementsByTagName('head')[0];
           var script= document.createElement('script');
           script.type= 'text/javascript';
           script.src = cordova.file.applicationDirectory + "www/plugins/kr.co.wisetracker/www/dop-native-sdk-inf.js";
           head.appendChild(script);
        } catch (e) {
           console.log('error');
           console.log(e);
        }
        console.log('injecting:' + cordova.file.applicationDirectory );
    }