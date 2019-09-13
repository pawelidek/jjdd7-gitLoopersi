<div>
  <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
  <script src="/js/employee.js"></script>
  <article id="TRESC">

    <table>
      <tr>
        <td style="text-align: left" width="100%"><h2>Employee manager</h2></td>
        <td style="text-align: right" width="100%">
          <button type="button" class="btn btn-outline-secondary"
                  data-toggle="modal" data-target="#exampleModal" id="add_user_button">
            <img src="/img/button_add.svg" alt="Add"> Add new employee
          </button>

        </td>
      </tr>
    </table>

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
              <td>${employee_index + 1}</td>
              <td>${employee.id}</td>
              <td>${employee.firstName}</td>
              <td>${employee.secondName}</td>
              <td>${employee.team.name}</td>
              <td>${employee.startDate}</td>
              <td>${employee.startHireDate}</td>
              <td>
                <div>
                  <a class="edit-employee" data-id="${employee.id}" href="#" data-toggle="modal"
                     data-target="#exampleModal">
                    <img src="/img/button_edit.svg" id="label" alt="Edit" title="Edit"></a>
                  <a class="delete-employee" data-id="${employee.id}" href="#">
                    <img src="/img/button_delete.svg" alt="Delete" title="Delete"></a>
                </div>
              </td>
            </tr>
          </#items>
      </#list>
      </tbody>
    </table>

    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">
              Employee
            </h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form id="settingForm" name="contact" role="form">
              <input id="formMethod" type="hidden" name="method" value="${method!"post"}"/>
              <input type="hidden" id="id" name="id" value="${(employee.id)!""}"/>
              <div class="form-group">
                <label for="firstName">First name:
                  <input type="text" id="firstName" class="form-control" name="firstName"
                         placeholder="New first name" value="${(employee.firstName)!""}">
                </label>
              </div>
              <div class="form-group">
                <label for="secondName">Last name:
                  <input type="text" id="secondName" class="form-control" name="secondName"
                         placeholder="New last name" value="${(employee.secondName)!""}">
                </label>
              </div>
              <div class="form-group">
                <label for="team">Team:
                  <select id="team" class="form-control" name="team">
                      <#list teams>
                          <#items as team>
                            <option value="${team.id}">${team.name}</option>
                          </#items>
                      </#list>
                  </select>
              </div>
              <div class="form-group">
                <label for="startDate">Employed from:
                  <input type="date" id="startDate" class="form-control" name="startDate"
                         value="${(employee.startDate)!""}">
                </label>
              </div>
              <div class="form-group">
                <label for="startHireDate">First employment:
                  <input type="date" id="startHireDate" class="form-control" name="startHireDate"
                         value="${(employee.startHireDate)!""}">
                </label>
              </div>

            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Close
            </button>
            <button type="button" class="btn btn-outline-secondary" id="saveEmployee">Save changes
            </button>
          </div>
        </div>
      </div>
    </div>

  </article>
</div>