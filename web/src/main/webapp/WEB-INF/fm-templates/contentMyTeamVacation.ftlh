<div>
  <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
  <script src="/js/vacation-user.js"></script>
  <article id="contentView">

    <div class="container-fluid">
      <table class="table table-borderless">
        <tr>
          <td style="text-align: left"><h2>My Team's Vacation</h2></td>
        </tr>
      </table>
    </div>

    <table class="table table-sm table-hover" style="text-align: center">
      <thead>
      <tr>
        <th scope="col" colspan="6" style="width: 100%">Members of "${team.name}" team</th>
      </tr>
      <tr>
        <th scope="col" style="width: 3%">#</th>
        <th scope="col" style="width: 3%">Id</th>
        <th scope="col" style="width: 28%">First name</th>
        <th scope="col" style="width: 28%">Last name</th>
        <th scope="col" style="width: 28%">Email</th>
        <th scope="col" style="width: 10%">Manager</th>
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
              <td><#if employee.admin == true>
                  <img src="/img/button_admin.svg" alt="Manager" title="Manager">
                  </#if>
              </td>
            </tr>
          </#items>
      </#list>
      </tbody>
    </table>

    <br>

    <table class="table table-sm table-hover" style="text-align: center">
      <thead>
      <tr>
        <th scope="col" colspan="10" style="width: 100%">Vacation of "${team.name}" team members</th>
      </tr>
      <tr>
        <th scope="col" style="width: 3%">#</th>
        <th scope="col" style="width: 5%">Emp Id</th>
        <th scope="col" style="width: 12%">First name</th>
        <th scope="col" style="width: 12%">Last name</th>
        <th scope="col" style="width: 10%">Date from</th>
        <th scope="col" style="width: 10%">Date to</th>
        <th scope="col" style="width: 12%">Amount of vacations</th>
        <th scope="col" style="width: 12%">Deputy</th>
        <th scope="col" style="width: 14%">Vacation type</th>
        <th scope="col" style="width: 10%">Status type</th>
      </tr>
      </thead>
      <tbody>
      <#list vacations>
          <#items as vacation>
            <tr id="vacation${vacation.id}">
              <td>${vacation_index + 1}</td>
              <td>${vacation.employee.id}</td>
              <td>${vacation.employee.firstName}</td>
              <td>${vacation.employee.secondName}</td>
              <td>${vacation.dateFrom}</td>
              <td>${vacation.dateTo}</td>
              <td>${vacation.daysCount}</td>
              <td>${(vacation.deputy)!"Not stated"}</td>
              <td>${vacation.vacationType.type}</td>
              <td>${vacation.statusType}</td>
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
              Vacation
            </h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">x</span>
            </button>
          </div>

          <div id="errors" class="alert alert-danger" role="alert"
               style="text-align: center; display: none;">
          </div>
        </div>
      </div>
    </div>

  </article>
</div>