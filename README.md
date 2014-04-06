# Android Push Notification Example using [Parse](https://www.parse.com/)
==========================

To run it:
1-Create a [Parse](https://www.parse.com/) account, and create your first app.

2-Change YOUR_APP_ID and YOUR_CLIENT_KEY in Application.java file with the values of your app.

    Parse.initialize(this, "YOUR_APP_ID", "YOUR_CLIENT_KEY");

3-Include google-play-services_lib project to your workspace.

4-Make sure the project has dependencies with google-play-services_lib, google-play-services.jar and Parse-1.4.2.jar

5-Add the following funcion in your Parse Cloud Code.
    Parse.Cloud.define("pushNotifications", function(request, response) {
    var projectIds = request.params.project_ids;
    var userId = request.params.user_id;
    
    console.log("ProjectID: " + projectIds);	
    console.log("UserID: " + userId);
    
    var query = new Parse.Query(Parse.Installation);
    query.notEqualTo("userId", userId);
    query.equalTo("projects", projectIds);
    
    Parse.Push.send({
      where: query, // Set our Installation query
      data: {
        alert: "Project updated!"
      }
    });
    response.success("Hello world!");
    });
    
5-Run

