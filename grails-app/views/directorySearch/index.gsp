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
      <g:each var="attribute" in="${dirEntry}">
        <!-- Text input-->
        <div class="control-group" id="${attribute.key}-group">

          <label class="control-label" for="${attribute.key}">
            <h4>${attribute.key} 
          <script type="text/javascript">
            ${attribute.key}_input = '<div class="controls"><div class="input-append"><input style="width: 450px;" name="${attribute.key}" type="text" placeholder="${attribute.key}"><button class="btn" onClick="$(this).parent().remove();" type="button"><i class="icon-trash"></i></button></div></div>';
          </script>
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
                  class="input-xlarge" 
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

    <!-- Button (Double) -->
  <div class="control-group">
    <div class="controls">
      <button id="button1id" name="button1id" class="btn btn-info" type="submit">Submit</button>
      <button id="button2id" name="button2id" class="btn btn-danger" type="reset">Cancel</button>
    </div>
  </div>
  <br>
    </g:form>
</body>

</html>