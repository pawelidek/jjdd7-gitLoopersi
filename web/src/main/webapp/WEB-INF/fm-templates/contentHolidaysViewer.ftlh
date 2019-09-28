<div>
  <script src="/js/holiday.js"></script>
  <article id="contentView">

    <div class="container-fluid">
      <table class="table table-borderless">
        <tr>
          <td style="text-align: left"><h2>Holiday List</h2></td>
        </tr>
      </table>
    </div>

    <table class="table table-sm table-hover" style="text-align: center">

      <thead>
      <tr>
        <th scope="col" style="width: 3%">#</th>
        <th scope="col" style="width: 3%">Id</th>
        <th scope="col" style="width: 10%">Holiday name</th>
        <th scope="col" style="width: 10%">Date</th>
        <th scope="col" style="width: 10%">Type</th>
        <th scope="col" style="width: 64%">Description</th>
      </tr>
      </thead>
      <tbody>
      <#list holidays>
          <#items as holiday>
            <tr>
              <td>${holiday_index + 1}.</td>
              <td>${holiday.id}</td>
              <td>${holiday.name}</td>
              <td>${holiday.date}</td>
              <td>${(holiday.holidayType.type)!"National Holiday"}</td>
              <td>${(holiday.description)!""}</td>
            </tr>
          </#items>
      </#list>
      </tbody>
    </table>

  </article>
</div>