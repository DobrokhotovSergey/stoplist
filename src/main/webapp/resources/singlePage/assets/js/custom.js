$(document).ready(function() {

  // $("main#spapp > section").height($(document).height() - 60);

  var app = $.spapp({pageNotFound : 'error_404'}); // initialize
    // define routes
    app.route({
        view: 'tableStopList',
        onReady: function () {
            NProgress.done();
            unblock_screen();
            // ajaxGetLisStoplist();
            // ajaxOnlineUsersChart();
        }
    });


    app.route({
        view: 'tableCodificator',
        onReady: function () {
            NProgress.done();
            unblock_screen();
            // ajaxGetLisStoplist();
            // ajaxOnlineUsersChart();
        }
    });
    app.route({
        view: 'users',
        onCreate: function () {
            // getPageUsers();
        },
        onReady: function () {
            if( typeof getPageUsers != 'undefined'){
                getPageUsers();
            }
            else {
                NProgress.done();
                unblock_screen();
            }

        }
    });
  // run app
  app.run();

});