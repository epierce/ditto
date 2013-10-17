<html>
  <head>
    <title>Token Error!</title>
    <meta name="layout" content="kickstart" />
    <g:set var="layout_nomainmenu"    value="${true}" scope="request"/>
    <g:set var="layout_nosecondarymenu" value="${true}" scope="request"/>
  </head>

<body>
  <content tag="header">
    <!-- Empty Header -->
  </content>
  
    <section id="Error" class="">
    <div class="big-message">
      <div class="container">
        <h1>
          Error
        </h1>
        <h2>
          There was a problem creating a token.
        </h2>
        <p>
          ${reason}
        </p>
        
      </div>
    </div>
  </section>
  
  
  </body>
</html>