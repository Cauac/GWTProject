window.fbAsyncInit = function() {
    FB.init({
        appId : '234252656753529',
        status : true, // check login status
        cookie : true, // enable cookies to allow the server to access the session
        xfbml : true // parse XFBML
    });
    FB.Event.subscribe('auth.authResponseChange', function(response) {
        if (response.status === 'connected') {
            testAPI();
        } else if (response.status === 'not_authorized') {
            FB.login();
        } else {
            FB.login();
        }
    });
};
(function(d){
    var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
    if (d.getElementById(id)) {return;}
    js = d.createElement('script'); js.id = id; js.async = true;
    js.src = "http://connect.facebook.net/en_US/all.js";
    ref.parentNode.insertBefore(js, ref);
}(document));

function testAPI() {
    console.log('Welcome! Fetching your information.... ');
    FB.api('/me', function(response) {
        window.fbLogin(response.id);
        console.log('Good to see you, ' + response.name + '.');
    });
}
