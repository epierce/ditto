<html>

<head>
  <meta name="layout" content="kickstart" />
</head>

<body>
  <!-- Form Name -->
  <div class="page-header">
    <h1>Ditto <small>copy a user</small></h1>
  </div>
  
  <legend>User attributes</legend>
  <div class="well">
    <g:form name="tokenForm" controller="token" action="generateToken">
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
                value="${dirEntry.uid}" 
                required>
          </div>
        <br>
      </div>
      <g:each var="attribute" in="${dirEntry.attributes}">
        <!-- Text input-->
        <div class="control-group" id="${attribute.key}-group">
          <label class="control-label" for="${attribute.key}">
          <script type="text/javascript">
            ${attribute.key}_input = '<div class="controls"><div class="input-append"><input style="width: 450px;" name="${attribute.key}" type="text" placeholder="${attribute.key}"><button class="btn" onClick="$(this).parent().remove();" type="button"><i class="icon-trash"></i></button></div></div>';
          </script>
            <h4>${attribute.key}
            <button class="btn btn-mini" type="button" onClick="$(this).parent().after(${attribute.key}_input);"><i class="icon-plus"></i></button></h4>
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
    </fieldset>

  </div>

  <div class="form-actions">
    <button id="submitButton" name="submitButton" class="btn btn-info" type="submit">Submit</button>
  </div>
    </g:form>
</script>
</body>

</html>