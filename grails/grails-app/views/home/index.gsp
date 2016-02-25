<html>

<head>
	<meta name="layout" content="kickstart" />
</head>

<body>

  <script type="text/javascript">
    $(document).ready(function() {
      $('#spinner').hide();

      $('#submitButton').click(function(){
        if($('#searchTerm').val()){
          $('#spinner').show();
        };
      });
    });
  </script>
  
    <!-- Form Name -->
    <div class="page-header">
      <h1>Ditto <small>copy a user</small></h1>
    </div>
    <div class="row">
      <div class="span4 offset1">
        <g:form name="searchForm" controller="personSearch">
          <fieldset>
            <legend>Search for an existing user</legend>
            <p>
              <label>Search by</label>
              <select class="input-block-level" id="searchBy" name="identifier">
                <option value="netid">NetID</option>
                <option value="mail">Email address</option>
                <option value="usfid">Unumber</option>
                <option value="emplid">Employee ID</option>
                <option value="idcard">IDcard</option>
              </select>
            </p>
            <input class="input-block-level" id="searchTerm" name="searchTerm" type="text" required>
            <p>
              <button class="btn btn-primary btn-block" type="submit" id="submitButton">Search <g:img dir="images" file="spinner.gif" id="spinner" /></button>
              
            </p>
          </fieldset>
        </g:form>
      </div>
      <div class="span4 offset1">
          <legend>Create a new user</legend>
          <g:link controller="personSearch" class="btn btn-primary btn-block">Set Attributes</g:link>
      </div>
    </div>
  </body>

</html>
