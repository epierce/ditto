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
    <g:form class="form-inline" name="searchForm" controller="personSearch">
      <fieldset>
        <legend>Search for an existing user</legend>
        <select style="width: 100px;" id="searchBy" name="identifier">
          <option value="netid">NetID</option>
          <option value="mail">Email address</option>
          <option value="usfid">Unumber</option>
          <option value="emplid">Employee ID</option>
          <option value="idcard">IDcard</option>
        </select>
        <div class="input-append">
          <input class="span2" id="searchTerm" name="searchTerm" type="text" required>
          <button class="btn" type="submit" id="submitButton">Search</button>
          <img src="images/spinner.gif" id="spinner" style="padding-left: 10px; display: none;">
        </div>
      </fieldset>
    </g:form>
    <g:link controller="personSearch" class="btn btn-primary">Create New User</g:link>
  </body>

</html>