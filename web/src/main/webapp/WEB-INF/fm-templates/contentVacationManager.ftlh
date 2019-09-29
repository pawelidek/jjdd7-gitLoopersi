<div>
  <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
  <script src="/js/vacation-admin.js"></script>
  <article id="contentView">

    <div class="container-fluid">
      <table class="table table-borderless">
        <tr>
          <td style="text-align: left"><h2>Vacation Manager</h2></td>
        </tr>
      </table>
    </div>



    <input type="date" name="dateFrom" id="dateFrom" placeholder="Enter date from"
           class="form-control"/>
    <input type="date" name="dateTo" id="dateTo" placeholder="Enter date to"
           class="form-control"/>
    <button type="button" class="btn btn-outline-secondary pull-right" id="searchByDates">
      <img src="/img/button_search.svg" alt="Search by dates"> Search by
      dates
    </button>



    <div>
        <#if success??>
          <div class="successMessage" role="alert"
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

    <table class="table table-sm table-hover" style="text-align: center">
      <thead>
      <tr>
        <th scope="col" style="width: 3%">#</th>
        <th scope="col" style="width: 3%">Id</th>
        <th scope="col" style="width: 13%">Requester</th>
        <th scope="col" style="width: 10%">Date from</th>
        <th scope="col" style="width: 10%">Date to</th>
        <th scope="col" style="width: 15%">Amount of vacations</th>
        <th scope="col" style="width: 13%">Deputy</th>
        <th scope="col" style="width: 15%">Vacation type</th>
        <th scope="col" style="width: 10%">Status type</th>
        <th scope="col" style="width: 8%">Manage</th>
      </tr>
      </thead>
      <tbody>
      <#list vacations>
          <#items as vacation>
            <tr id="vacation${vacation.id}">
              <td>${vacation_index + 1}</td>
              <td>${vacation.id}</td>
              <td>${vacation.employee.firstName} ${vacation.employee.secondName}</td>
              <td>${vacation.dateFrom}</td>
              <td>${vacation.dateTo}</td>
              <td>${vacation.daysCount}</td>
              <td>${(vacation.deputy)!"Not stated"}</td>
              <td>${vacation.vacationType.type}</td>
              <td class="status-type-${vacation.id}">${vacation.statusType}</td>
              <td>
                <div>
                  <a class="reject-vacation" data-id="${vacation.id}" href="#">
                    <img src="/img/button_reject.svg" alt="Reject" title="Reject">
                  </a>
                  <a class="accept-vacation" data-id="${vacation.id}" href="#">
                    <img src="/img/button_accept.svg" alt="Accept" title="Accept">
                  </a>
                </div>
              </td>
            </tr>
          </#items>
      </#list>
      </tbody>
    </table>
  </article>
</div>