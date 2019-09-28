<div>
  <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
  <script src="/js/employee.js"></script>
  <article id="contentView">

    <div class="container-fluid">
      <table class="table table-borderless">
        <tr>
          <td style="text-align: left"><h2>Employee Manager</h2></td>
          <td style="text-align: right">
            <button type="button" class="btn btn-outline-secondary"
                    data-toggle="modal" data-target="#exampleModal" id="add_user_button">
              <img src="/img/button_add.svg" alt="Add"> Add Employee
            </button>
          </td>
        </tr>
      </table>
    </div>

    <div>
        <#if success??>
          <div id="successMessage" class="alert alert-success fade show" role="alert"
               style="text-align: center">
            <strong>
                <#list success>
                    <#items as successMessages>
                        ${successMessages}<br/>
                    </#items>
                </#list>
            </strong>
          </div>
        </#if>
    </div>

    <div id="successMain" class="alert alert-success fade show" role="alert"
         style="text-align: center; display: none;">
    </div>

    <div id="errorsMain" class="alert alert-danger fade show" role="alert"
         style="text-align: center; display: none;">
    </div>

    <table class="table table-sm table-hover" style="text-align: center">

      <thead>
      <tr>

        <th scope="col" style="width: 3%">#</th>
        <th scope="col" style="width: 3%">Id</th>
        <th scope="col" style="width: 9%">First name</th>
        <th scope="col" style="width: 14%">Last name</th>
        <th scope="col" style="width: 14%">Email</th>
        <th scope="col" style="width: 23%">Team</th>
        <th scope="col" style="width: 10%">Employed from</th>
        <th scope="col" style="width: 10%">First employment</th>
        <th scope="col" style="width: 6%">Admin</th>
        <th scope="col" style="width: 8%">Manage</th>

      </tr>
      </thead>
      <tbody>
      <#list employees>
          <#items as employee>
            <tr id="employee${employee.id}" style="vertical-align: middle">
              <td>${employee_index + 1}.</td>
              <td>${employee.id}</td>
              <td>${employee.firstName}</td>
              <td>${employee.secondName}</td>
              <td>${employee.email}</td>
              <td>${employee.team.name}</td>
              <td>${employee.startDate}</td>
              <td>${employee.startHireDate}</td>
              <td><#if employee.admin == true>
                  <img src="/img/button_admin.svg" alt="Admin" title="Admin">
                  </#if>
              </td>
              <td>
                <div>
                    <#if (employee.id) == 1>
                  <img src="/img/button_edit_inactive.svg" id="label" alt="Edit" title="Can't edit">
                    <#else>
                  <a class="edit-employee" data-id="${employee.id}" href="#" data-toggle="modal"
                     data-target="#exampleModal">
                    <img src="/img/button_edit.svg" id="label" alt="Edit" title="Edit"></a>
                      </#if>
                      <#if (employee.id) == 1>
                        <img src="/img/button_delete_inactive.svg" alt="Can't delete"
                             title="Can't delete">
                      <#else>
                  <a class="delete-employee" data-id="${employee.id}" href="#">
                    <img src="/img/button_delete.svg" alt="Delete" title="Delete"></a>
                </#if>
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

          <div id="errors" class="alert alert-danger" role="alert"
               style="text-align: center; display: none;">
          </div>

          <div class="modal-body">
            <form id="settingForm" name="contact" role="form">
              <input id="formMethod" type="hidden" name="method" value="${method!"post"}"/>
              <input type="hidden" id="id" name="id" value="${(employee.id)!""}"/>
              <div class="form-group">
                <label for="firstName">First name:
                  <input style="width: 475px" autocomplete="off" type="text" id="firstName"
                         class="form-control"
                         name="firstName"
                         placeholder="New first name" value="${(employee.firstName)!""}">
                </label>
              </div>
              <div class="form-group">
                <label for="secondName">Last name:
                  <input style="width: 475px" autocomplete="off" type="text" id="secondName"
                         class="form-control"
                         name="secondName"
                         placeholder="New last name" value="${(employee.secondName)!""}">
                </label>
              </div>
              <div class="form-group">
                <label for="email">Email:
                  <input style="width: 475px" autocomplete="off" type="email" id="email"
                         class="form-control"
                         name="email"
                         placeholder="example@email.com" value="${(employee.email)!""}">
                </label>
              </div>
              <div class="form-group">
                <label for="team">Team:
                  <select style="width: 475px" autocomplete="off" id="team" class="form-control"
                          name="team">
                      <#list teams>
                          <#items as team>
                            <option value="${(team.id)!""}">${team.name}</option>
                          </#items>
                      </#list>
                  </select>
                </label>
              </div>
              <div class="form-group">
                <label for="startDate">Employed from:
                  <input style="width: 475px" autocomplete="off" type="date" id="startDate"
                         class="form-control"
                         name="startDate"
                         value="${(employee.startDate)!""}">
                </label>
              </div>
              <div class="form-group">
                <label for="startHireDate">First employment:
                  <input style="width: 475px" autocomplete="off" type="date" id="startHireDate"
                         class="form-control"
                         name="startHireDate"
                         value="${(employee.startHireDate)!""}">
                </label>
              </div>
              <div class="form-group">
                <label for="admin">
                  <input style="vertical-align: middle" autocomplete="off" type="checkbox"
                         id="admin" name="admin" value="false">
                  Set as admin
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