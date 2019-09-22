<div class="container">
<<<<<<< HEAD
  <script src="/js/holiday.js"></script>
  <article id="TRESC">
    <br><br>
=======
    <#--  <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>-->
  <script src="/js/holiday.js"></script>
  <article id="TRESC">
    <br><br>
      <#--    <button type="button" class="btn btn-secondary" id="searchByDates">-->
      <#--      Search by dates-->
      <#--    </button>-->
      <#--    <button type="button" class="btn btn-secondary" id="searchByPattern">-->
      <#--      Search by pattern-->
      <#--    </button>-->

      <#--    <div align="center">-->
      <#--      <input type="text" name="liveSearchHoliday" id="liveSearchHoliday" placeholder="Enter your" class="form-control" />-->
      <#--      <input type="date" name="startDate" id="startDate" placeholder="Enter first date..." class="form-control" />-->
      <#--      <input type="date" name="endDate" id="endDate" placeholder="Enter second date..." class="form-control" />-->
      <#--    </div>-->


>>>>>>> 9ce7d80ac897ce2b213e45ce37edd2476f394869
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-4 text-right">
          <button type="button" class="btn btn-secondary" id="searchByPattern">Search by pattern
          </button>
        </div>
        <div class="col-4 mb-5">
          <input type="text" name="liveSearchHoliday" id="liveSearchHoliday"
                 placeholder="Enter holday's name..." class="form-control"/>
        </div>
      </div>
    </div>

    <div class="container">
      <div class="row justify-content-center">
        <div class="col-4 text-right">
          <button type="button" class="btn btn-secondary pull-right" id="searchByDates">Search by
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
        <th scope="col">Id</th>
        <th scope="col">Holiday name</th>
        <th scope="col">Date</th>
        <th scope="col">Type</th>
        <th scope="col" style="width: 50%">Description</th>
      </tr>
      </thead>
      <tbody>
<<<<<<< HEAD
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
=======
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
>>>>>>> 9ce7d80ac897ce2b213e45ce37edd2476f394869
      </tbody>
        <#elseif errorMessage??>
          <div class="alert alert-danger" role="alert" style="text-align: center">
            <strong>${errorMessage}</strong>
          </div>
        <#else>
          <div class="alert alert-success" role="alert" style="text-align: center">
            <strong>Type a holiday name or dates interval to see the results</strong>
          </div>
        </#if>
  </article>
</div>
