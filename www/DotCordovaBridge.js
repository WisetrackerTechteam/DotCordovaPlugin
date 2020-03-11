cordova.define("kr.co.wisetracker.DotCordovaBridge", function(require, exports, module) {
exports.init = function(key, value, success, error) {
	window.WISETRACKER_SDK_ENV_CODE = 2; 
	// script dynamic loading 
	try { 
		
		cordova.exec(function() {console.log('success')},
				function() {console.log('error')},
				"DotCordovaBridge",
				"initialization",
				[]);
		
		var head= document.getElementsByTagName('head')[0];
		var script= document.createElement('script');
		script.type= 'text/javascript'; 
		script.src = "plugins/kr.co.wisetracker/www/dop-native-sdk-inf.js";
		head.appendChild(script);  
		
	} catch (e) {
		console.log(e);
	} 
}
});
