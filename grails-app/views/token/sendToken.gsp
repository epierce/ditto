<html>
  <body>
    <form action="${casURL}/login" method="GET" id="AuthSubmit" style="display: none;">
      <input type="hidden" name="auth_token" value="${authToken}" >
      <input type="hidden" name="username" value="${username}" >
      <input type="hidden" name="token_service" value="${tokenService}" >
      <input type="hidden" name="service" value="${service}" >
    </form>
    <script>
      window.onload = function(){
          document.forms[0].submit();
      }
    </script>
  </body>
</html>