<div>
  <script src="/js/holiday.js"></script>
  <article id="contentView">

    <div class="container-fluid">
      <table class="table table-borderless">
        <tr>
          <td style="text-align: left"><h2>Holiday Search</h2></td>
        </tr>
      </table>
    </div>

    <div class="container">
      <div class="row justify-content-center">
        <div class="col-4 text-right">
          <button type="button" class="btn btn-outline-secondary" id="searchByPattern">
            <img src="/img/button_search.svg" alt="Search by pattern"> Search by pattern
          </button>
        </div>
        <div class="col-4 mb-5">
          <input type="text" name="liveSearchHoliday" id="liveSearchHoliday"
                 placeholder="Enter holiday's name..." class="form-control"/>
        </div>
      </div>
    </div>

    <div class="container">
      <div class="row justify-content-center">
        <div class="col-4 text-right">
          <button type="button" class="btn btn-outline-secondary pull-right" id="searchByDates">
            <img src="/img/button_search.svg" alt="Search by dates"> Search by
            dates
          </button>
        </div>
        <div class="col-4 mb-5">
          <input type="date" name="startDate" id="startDate" placeholder="Enter first date..."
                 class="form-control"/>
          <input type="date" name="endDate" id="endDate" placeholder="Enter second date..."
                 class="form-control"/>
        </div>
      </div>
    </div>

    <ul class="list-group" id="result"></ul>
      <#if holidays?? >
    <table class="table table-sm" style="text-align: center">
      <thead>
      <tr>
        <th scope="col" style="width: 3%">Id</th>
        <th scope="col" style="width: 17%">Holiday name</th>
        <th scope="col" style="width: 15%">Date</th>
        <th scope="col" style="width: 15%">Type</th>
        <th scope="col" style="width: 50%">Description</th>
      </tr>
      </thead>
      <tbody>
      <#if holidays?? >
          <#list holidays>
              <#items as holiday>
                <tr>
                  <td>${holiday.id}.</td>
                  <td>${holiday.name}</td>
                  <td>${holiday.date}</td>
                  <td>${(holiday.holidayType.type)!"National Holiday"}</td>
                  <td>${(holiday.description)!""}</td>
                </tr>
              </#items>
          </#list>
      </#if>
      </tbody>
        <#elseif errorMessage??>
          <div class="alert alert-danger" role="alert" style="text-align: center">
            <strong>${errorMessage}</strong>
          </div>
        <#else>
          <div class="alert alert-secondary" role="alert" style="text-align: center">
            <strong>Type a holiday name or dates interval to see the results</strong>
          </div>
        </#if>
    </table>
  </article>
</div>
