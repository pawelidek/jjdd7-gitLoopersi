<div>
  <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
  <script src="/js/deleteEmployee.js"></script>
  <article id="TRESC">

    <a href="/admin/user/add">
      <button type="button" class="btn btn-secondary" title="Add new employee">
        <img src="/img/button_add.svg"> Add new employee
      </button>
    </a>
    <br><br>

    <table class="table table-sm" style="text-align: center">

      <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Id</th>
        <th scope="col">First name</th>
        <th scope="col">Last name</th>
        <th scope="col">Team</th>
        <th scope="col">Employed from</th>
        <th scope="col">First employment</th>
        <th scope="col">Manage</th>
      </tr>
      </thead>
      <tbody>
      <#list employees>
          <#items as employee>
            <tr>
              <td scope="row">${employee_index + 1}</td>
              <td>${employee.id}</td>
              <td>${employee.firstName}</td>
              <td>${employee.secondName}</td>
              <td>gitLoopersi</td>
              <td>2010-05-06</td>
              <td>2005-05-06</td>
              <td>
                <div style="text-align:center">
                  <a href="#"><img src="/img/button_preview.svg" alt="Preview" title="Preview"></a>
                  <a href="#"><img src="/img/button_edit.svg" alt="Edit" title="Edit"></a>
                  <a class="delete-employee-link" data-id="${employee.id}" href="#">
                  <img src="/img/button_delete.svg" alt="Delete" title="Delete"></a>
                </div>
              </td>
            </tr>
          </#items>
      </#list>
      </tbody>
    </table>


  </article>
</div>