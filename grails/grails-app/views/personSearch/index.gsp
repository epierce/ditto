<html>

<head>
  <meta name="layout" content="kickstart" />
</head>

<body>
  <div class="page-header">
    <h1>Ditto <small>copy a user</small></h1>
  </div>
  <g:if test="${result.result == 'success'}">
    <legend>User attributes</legend>
    <g:form name="tokenForm" controller="token" action="generateToken" id="dev" useToken="true">
      <div class="well">     
        <fieldset>
          <div class="control-group" id="uid-group">
            <label class="control-label" for="uid">
              <h4>username (required)</h4>
            </label>
            <div class="controls">
                <input 
                  style="width: 450px;"
                  name="username" 
                  type="text" 
                  placeholder="username"
                  value="${result.uid}" 
                  required>
            </div>
            <br>
          </div>
          <g:each var="attribute" in="${result.attributes}">
            <!-- Text input-->
            <div class="control-group" id="${attribute.key}-group">
              <label class="control-label" for="${attribute.key}">
                <script type="text/javascript">
                  ${attribute.key}_input = '<div class="controls"><div class="input-append"><input style="width: 450px;" name="${attribute.key}" type="text" placeholder="${attribute.key}"><button class="btn" onClick="$(this).parent().remove();" type="button"><i class="icon-trash"></i></button></div></div>';
                </script>
                <h4>${attribute.key} <button class="btn btn-mini" type="button" onClick="$(this).parent().after(${attribute.key}_input);"><i class="icon-plus"></i></button></h4>
              </label>
              <g:each var="entry" in="${attribute.value}">
                <div class="controls">
                  <div class="input-append">
                    <input 
                      style="width: 450px;"
                      name="${attribute.key}" 
                      type="text" 
                      placeholder="${attribute.key}" 
                      value="<g:if test="${attribute.value.size() > 0}">${entry}</g:if>" />
                    <button class="btn" type="button" onClick="$(this).parent().remove();"><i class="icon-trash"></i></button>
                  </div>
                </div>
              </g:each>
              <br>
            </div>
          </g:each>
          <p>
            <h4><input type="checkbox" name="debug" value="TRUE"> View token contents before sending to CAS</h4>
            <br>
          </p>
          <p>
            <button id="submitButtonDev" name="submitButton" class="btn btn-primary btn-large btn-block" type="button">Login to Development CAS Server</button>
            <script>
              $("#submitButtonDev").click(function() {
                $("#tokenForm").attr("action", "<g:createLink controller="token" action="generateToken" id="dev" />");
                $("#tokenForm").submit();
              });
            </script>
          </p>
          <sec:ifAllGranted roles="${grailsApplication.config.ditto.roles.admin.test}">
            <p>
              <button id="submitButtonTest" name="submitButton" class="btn btn-warning btn-large btn-block" type="button">Login to Pre-Production CAS Server</button>
            </p>
            <script>
              $("#submitButtonTest").click(function() {
                $("#tokenForm").attr("action", "<g:createLink controller="token" action="generateToken" id="test" />");
                $("#tokenForm").submit();
              });
            </script>
          </sec:ifAllGranted>
          <sec:ifAllGranted roles="${grailsApplication.config.ditto.roles.admin.production}">
            <p>
              <button id="submitButtonProd" name="submitButton" class="btn btn-danger btn-large btn-block" type="button">Login to Production CAS Server</button>
            </p>
            <script>
              $("#submitButtonProd").click(function() {
                $("#tokenForm").attr("action", "<g:createLink controller="token" action="generateToken" id="production" />");
                $("#tokenForm").submit();
              });
            </script>
          </sec:ifAllGranted>
        </fieldset>     
      </div>
    </g:form>
  </g:if>
  <g:else>
    <div class="hero-unit">
      <h2>There was a problem!</h1>
      <p>The server said: "${result.details}"</p>
      <g:link controller="home" class="btn btn-primary"><i class="icon-chevron-left icon-white"></i> Return to search</g:link>
    </div>
  </g:else>
</body>
</html>