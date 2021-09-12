<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/8/29
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>

    <script type="text/javascript">

      // 页面加载完成之后
      $(function () {

        $("#cdo").click(function () {
          this.src="${basePath}kaptchaServlet.jpg?d="+new data();
          alert(this.src="${basePath}kaptchaServlet.jpg?d="+new data());
        });
      })
    </script>
  </head>
  <body>
  $END$
  <img id="cdo" src="ss">
  </body>
</html>
