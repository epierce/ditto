<html>

<head>
	<meta name="layout" content="kickstart" />
</head>

<body>
  <fieldset>
    <!-- Form Name -->
    <div class="page-header">
      <h1>Ditto <small>copy a user</small></h1>
    </div>

  <form class="form-inline">
    <legend>Search for an existing user</legend>
    <select style="width: 100px;" id="searchBy">
      <option>NetID</option>
      <option>Unumber</option>
      <option>EmplID</option>
    </select>
    <div class="input-append">
      <input class="span2" id="appendedInputButton" type="text">
      <button class="btn" type="button">Search</button>
    </div>
  </form>
  </fieldset>
  <g:link controller="personSearch" class="btn btn-info">Create New User</g:link>
</body>

</html>
