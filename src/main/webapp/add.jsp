<%--@elvariable id="UserAccount" type="javax.mail.PasswordAuthentication"--%>
<%--
  Created by IntelliJ IDEA.
  User: ander
  Date: 14.05.2023
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add new user</title>
</head>

<body>
<div>
  <h1>Super app!</h1>
</div>

<div>
  <%
    if (request.getAttribute("userName") != null) {
      out.println("<p>User '" + request.getAttribute("userName") + "' added!</p>");
    }
  %>
  <div>
    <div>
      <h2>Add user</h2>
    </div>

    <form method="post">
      <h3>Hello: "${ userName}"</h3>
    </form>
  </div>
</div>

<div>
  <button onclick="location.href='/home'">Back to main</button>
</div>
</body>
</html>--%>

<!DOCTYPE html>
<html xmlns:border="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="content-type"  content="text/html;charset=UTF-8">
  <style type="text/css" media="screen">
    body {
      color: black;
      background-color: white;
      margin: 0px
    }

    p {
      border: 1px solid powderblue;
    }


    .horizon {
      color: black;
      background-color: transparent;
      text-align: center;
      position: absolute;
      top: 50%;
      left: 0px;
      width: 100%;
      height: 1px;
      overflow: visible;
      visibility: visible;
      display: block
    }

    .content {
      font-family: Verdana, Geneva, Arial, sans-serif;
      background-color: transparent;
      margin-left: -125px;
      position: absolute;
      top: -35px;
      left: 50%;
      width: 250px;
      height: 70px;
      visibility: visible
    }

    .bodytext {
      font-size: 14px
    }

    .headline {
      font-weight: bold;
      font-size: 24px
    }

    #footer {
      font-size: 11px;
      font-family: Verdana, Geneva, Arial, sans-serif;
      text-align: center;
      position: absolute;
      bottom: 0px;
      left: 0px;
      width: 100%;
      height: 20px;
      visibility: visible;
      display: block
    }

    a:link, a:visited {
      color: black;
      text-decoration: none;
    }

    a:hover {
      color: darkblue;
      text-decoration: none
    }

    .button{
      display: inline-block;
      background: white;
      border-radius: 4px;
      font-size: 14px;
      color: black;
      padding: 8px 12px;
      cursor: pointer;
    }

    --></style>
</head>
<body>
<form method="post" > <!--action="/index.jsp"-->
  <div class="horizon">
    <div class="content">
        <span class="headline">
          <div class="row">
              <div>
                  <h3>Log in success</h3>
              </div>
              <div>
               <button onclick="location.href='/home'">Back to main</button>
            </div>
          </div>
        </span>
      </div>
    </div>
  </div>
</form>
</body>
</html>


