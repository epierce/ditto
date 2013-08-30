<html>

  <head>
    <meta name="layout" content="kickstart" />
  </head>

  <body>
    <legend>Auth Token Generator</legend>

    <h4>JSON data</h4>
    <pre class="prettyprint">${jsonData}</pre>

    <h4>Token Value</h4>
    <pre class="prettyprint">${encrytedToken}</pre>

    <h4>Link to CAS Server</h4>
    <pre class="prettyprint">${finalURL}</pre>

    <a href="${finalURL}" class="btn btn-primary">Login to CAS</a>

  </body>

</html>