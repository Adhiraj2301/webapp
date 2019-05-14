<%-- 
    Document   : AjaxPage
    Created on : 25 Jul, 2018, 12:06:13 PM
    Author     : rahul.yadav
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    
    <head><title>welcome</title>

        <meta charset="utf-8">
        
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
        <script language="javascript">
            reqObj=null;
            function varify(){
                document.getElementById("res").innerHTML="<img src=\"image\\loader.gif\" width=\"400px\" height=\"150px\">";
                if(window.XMLHttpRequest){
                    reqObj=new XMLHttpRequest();
                }else {
                    reqObj=new ActiveXObject("Microsoft.XMLHTTP");
                }
            
                reqObj.onreadystatechange=process;
                reqObj.open("POST","./makeTinyUrl.jsp?url="+document.getElementById("LongUrl").value,true);
                reqObj.send(null);
            }
            function process(){
                if(reqObj.readyState==4){
                    var x=reqObj.responseText;
                    var html='<a  id="redirect" href="./RedirectOriginalPage.jsp?url='+x+'">Test Tiny Url!</a>';
                    res.innerHTML =html;
                   
                    
                    document.getElementById("resText").innerHTML=x;
            

                }
            }
        </script>

    </head>

    <body>

        <div class="container">
            <h2>Welcome</h2>
            <div class="panel-group">
                <div class="panel panel-primary">
                    <div class="panel-heading">Tikona Tiny URL</div>
                    <div class="panel-body">
                        <label for="SUrl">Enter a long URL to make tiny</label>
                        <input type="text" name="LongUrl" id="LongUrl" class="form-control"  placeholder="Enter URL" required><br>
                        <input type="submit" class="btn btn-warning" onClick="varify();"><br>
                        <label for="TinyName">Tiny URL</label><br>
                        
                        <button type="button" class="btn btn-link" id="res"></button><br>
                        
                        
                        Short URL: <div id="resText" style="color: red"></div>

                    </div>
                </div>


            </div>
        </div>           
</body>
</html >