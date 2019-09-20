<div class="container">
  <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
  <script src="/js/holiday.js"></script>
  <article id="TRESC">
    <br><br>
    <button type="button" class="btn btn-secondary" id="searchByDates">
      Search by dates
    </button>
    <button type="button" class="btn btn-secondary" id="searchByPattern">
      Search by pattern
    </button>

    <div align="center">
      <input type="text" name="search" id="search" placeholder="Enter your" class="form-control" />
    </div>
    <ul class="list-group" id="result"></ul>

    <table class="table table-sm" style="text-align: center">
      <thead>
      <tr>
        <th scope="col">Id</th>
        <th scope="col">Holiday name</th>
        <th scope="col">Date</th>
        <th scope="col">Type</th>
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
  </article>
</div>
