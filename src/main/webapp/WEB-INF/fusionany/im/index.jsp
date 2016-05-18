<%--
  Created by IntelliJ IDEA.
  User: eric
  Date: 16-5-13
  Time: 上午11:42
  To change this template use File | Settings | File Templates.
  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Demo</title>
    <script type="text/javascript" src="<%=contextPath %>/resources/js/jquery/jquery-2.2.3.min.js"></script>
    <script type="text/javascript" src="<%=contextPath %>/resources/js/im/im.js" ></script>
    <script type="text/javascript" >
        var contextPath = '<%=contextPath%>';
    </script>
  </head>
  <body>
  <h1>消息发送测试页面</h1>
  <hr>
  <div id="result">

      ${mes}
      ${success}
  </div>
  <hr>
  <div id="divT" style="height: 200px;">
      <form action="<%=contextPath%>/im/addText.htm" method="post">
    <div id="divT_left" style="float: left;width: 270px;">
        <textarea rows="10" cols="30" id="mesT" name="mesT" ></textarea>
    </div>
    <div id="divT_right" style="float: left;width: 70px;margin-top: 120px;">
        <button type="submit">发送</button>
    </div>
      </form>
  </div>
  <div id="divF" style="margin-bottom: 50px">
      <form action="<%=contextPath%>/im/addFile.htm" method="post" enctype="multipart/form-data">
          <div id="divF_left" style="float: left;width: 270px;">
            <input type="file" id="mesF" name="mesF">
          </div>
          <div id="divF_right" style="float: left;width: 70px;">
              <button type="submit">发送</button>
          </div>
      </form>

  </div>
  <div id="divE">
      <form id="form_emoji" action="<%=contextPath%>/im/addEmoji.htm" method="post" >
          <div id="divE_left" style="float: left;width: 270px;">
          </div>
          <div id="divE_right" style="float: left;width: 70px;margin-top: 100px;">
              <input type="hidden" id="mesE" name="mesE">
              <button type="button" onclick="preEmoji()">发送</button>
          </div>
      </form>

  </div>
  </body>
</html>
