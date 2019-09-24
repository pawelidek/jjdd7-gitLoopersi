<div>
  <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
  <script src="/js/vacation-user.js"></script>
  <article id="contentView">

    <div class="container-fluid">
      <table class="table table-borderless">
        <tr>
          <td style="text-align: left"><h2>Vacation request</h2></td>
          <td style="text-align: right">
            <button type="button" class="btn btn-outline-secondary"
                    data-toggle="modal" data-target="#exampleModal" id="add_vacation_button">
              <img src="/img/button_add.svg" alt="Add"> Add Vacation
            </button>
          </td>
        </tr>
      </table>
    </div>
    <div>
        <#if success??>
          <div class="alert alert-success alert-dismissible fade show" role="alert"
               style="text-align: center">
            <strong>
                <#list success>
                    <#items as successMessages>
                        ${successMessages}<br>
                    </#items>
                </#list>
            </strong>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
              <span aria-hidden="true">x</span>
            </button>
          </div>
        </#if>
    </div>
    <table class="table table-sm table-hover" style="text-align: center">
      <thead>
      <tr>
        <th scope="col" style="width: 5%">#</th>
        <th scope="col" style="width: 5%">Id</th>
        <th scope="col" style="width: 10%">Date from</th>
        <th scope="col" style="width: 10%">Date to</th>
        <th scope="col" style="width: 15%">Amount of vacations</th>
        <th scope="col" style="width: 10%">Deputy</th>
        <th scope="col" style="width: 15%">Vacation type</th>
        <th scope="col" style="width: 10%">Status type</th>
        <th scope="col" style="width: 15%">Manage</th>
      </tr>
      </thead>
      <tbody>
      <#list vacations>
          <#items as vacation>
            <tr id="vacation${vacation.id}">
              <td>${vacation_index + 1}</td>
              <td>${vacation.id}</td>
              <td>${vacation.dateFrom}</td>
              <td>${vacation.dateTo}</td>
              <td>${vacation.daysCount}</td>
              <td>${(vacation.deputy)!"Not stated"}</td>
              <td>${vacation.vacationType.type}</td>
              <td>${vacation.statusType}</td>
              <td>
                <div>
                  <a class="delete-vacation" data-id="${vacation.id}" href="#">
                    <img src="/img/button_delete.svg" alt="Delete" title="Delete">
                  </a>
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
              Vacation
            </h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">x</span>
            </button>
          </div>

          <div id="errors" class="alert alert-danger" role="alert"
               style="text-align: center; display: none;">
          </div>

          <div class="modal-body">
            <form id="settingForm" name="contact" role="form">
              <input id="formMethod" type="hidden" name="method" value="${method!"post"}"/>
              <input type="hidden" id="id" name="id" value="${(vacation.id)!""}"/>
              <div class="form-group">
                <label for="dateFrom">Date from:
                  <input style="width: 475px" autocomplete="off" type="date" id="dateFrom"
                         class="form-control"
                         name="dateFrom"
                         value="${(vacation.dateFrom)!""}">
                </label>
              </div>
              <div class="form-group">
                <label for="dateTo">Date to:
                  <input style="width: 475px" autocomplete="off" type="date" id="dateTo"
                         class="form-control"
                         name="dateTo"
                         value="${(vacation.dateTo)!""}">
                </label>
              </div>
              <div class="form-group">
                <label for="vacationType">Vacation type:
                  <select style="width: 475px" autocomplete="off" id="vacationType"
                          class="form-control"
                          name="vacationType">
                    <option value="Vacation leave">Vacation leave</option>
                    <option value="Childcare">Childcare</option>
                    <option value="Special leave">Special leave</option>
                  </select>
                </label>
              </div>
              <div class="form-group">
                <label for="deputy">Deputy:
                  <select style="width: 475px" autocomplete="off" id="deputy" class="form-control"
                          name="deputy">
                      <#list employees>
                          <#items as employee>
                            <option
                                value="${employee.id}. ${employee.firstName} ${employee.secondName}">
                                ${employee.firstName} ${employee.secondName}
                            </option>
                          </#items>
                      </#list>
                  </select>
                </label>
              </div>
            </form>
            <div class="modal-footer">
              <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Close
              </button>
              <button type="button" class="btn btn-outline-secondary" id="saveVacation">
                Save changes
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

  </article>
</div>