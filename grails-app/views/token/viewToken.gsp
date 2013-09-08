<html>
  <head>
    <meta name="layout" content="kickstart" />
  </head>

  <body>
    <legend>Auth Token Generator</legend>
    <form action="${casURL}/login" method="GET" name="AuthSubmit">
      <h4>JSON data</h4>
      <pre class="prettyprint">${jsonData}</pre>

      <h4>Token Value</h4>
      <pre class="prettyprint">${authToken}</pre>
      <input type="hidden" name="auth_token" value="${authToken}" >

      <h4>CAS Server</h4>
      <pre class="prettyprint">${casURL}</pre>

      <h4>Username</h4>
      <pre class="prettyprint">${username}</pre>
      <input type="hidden" name="username" value="${username}" >

      <h4>Encryption key name</h4>
      <pre class="prettyprint">${tokenService}</pre>
      <input type="hidden" name="token_service" value="${tokenService}" >

      <h4>CAS service</h4>
      <pre class="prettyprint">${service}</pre>
      <input type="hidden" name="service" value="${service}" >

      <button type="submit" class="btn btn-primary">Login to CAS</button>
    </form>
  </body>
</html>