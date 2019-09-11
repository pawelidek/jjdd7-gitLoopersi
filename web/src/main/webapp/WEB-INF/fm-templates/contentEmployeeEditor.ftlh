<div>

  <article id="TRESC">

    <h3>Edit employee</h3>
    <br>
    <form method="put" action="/admin/employee/edit">
      <div class="form-group">
        <label for="firstName">First name:
          <input type="text" class="form-control" name="newName"
                 value="${employee.firstName}" placeholder="Enter new first name value"/>
        </label>
      </div>
      <div class="form-group">
        <label for="firstName">Last name:
          <input type="text" class="form-control" name="newSecondName"
                 value="${employee.secondName}" placeholder="Enter new last name value"/>
        </label>
      </div>
      <button type="submit" name="save" value="Submit" class="btn btn-outline-secondary">
        Submit
      </button>
      <br><br/>
    </form>

  </article>
</div>