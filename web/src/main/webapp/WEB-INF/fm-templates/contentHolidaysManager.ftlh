<div>
  <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
  <script src="/js/holidayManagment.js"></script>
  <article id="TRESC">

    <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#exampleModal">
      <img src="/img/button_add.svg"> Add Holiday
    </button>
    <br><br>

    <table class="table table-sm" style="text-align: center">

      <thead>
      <tr>
        <th scope="col">Id</th>
        <th scope="col">Holiday name</th>
        <th scope="col">Date</th>
        <th scope="col">Type</th>
        <th scope="col">Description</th>
      </tr>
      </thead>
      <tbody>
      <#list holidays>
          <#items as holiday>
            <tr>
              <td>${holiday.id}.</td>
              <td>${holiday.name}</td>
              <td>${holiday.date}</td>
              <td>Holiday</td>
              <td>${holiday.description}</td>
              <td>
                <div style="text-align:center">
                  <button id='upd' data-id='${holiday.id}' class='update-holiday'
                          data-toggle="modal" data-target="#exampleModal">
                    Edit
                  </button>
                  <button id='del' data-id='${holiday.id}' class='delete-holiday'
                          href="/admin/holiday?id=${holiday.id}">
                    Delete
                  </button>
                </div>
              </td>
            </tr>
          </#items>
      </#list>
      <!---->
      <!-- MOOOOOOOOOOODDDDDDDDDDDDDDDDDDDDDAAAAAAAAAAAAAAAAAAALLLLLLLLLLLLLLLLLL!!!!!!!!!!!!!! -->
      <!---->
      <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
           aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">Add Holiday</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <form id="contactForm" name="contact" role="form">
              <input id="addHolidayMethod" type="hidden" name="method" value="${method!"post"}"/>
              <input type="hidden" name="id" value="${(oneHoliday.id)!""}"/>
              <div class="modal-body">
                <div class="form-group">
                  <label for="name">Name</label>
                  <input type="text" name="holiday_name"
                         class="form-control value="${(oneHoliday.name)!""}">
                </div>
                <div class="form-group">
                  <label for="name">Date</label>
                  <input type="date" name="holiday_date" class="form-control"
                         value="${(oneHoliday.date)!""}">
                </div>
                <div class="form-group">
                  <label for="name">Type</label>
                  <select class="form-control" name="holiday_type">
                      <#--                    <option value="NATIONAL_HOLIDAY" ${selectedType[NATIONAL_HOLIDAY]!""}>National holiday</option>-->
                      <#--                    <option value="OBSERVANCE" ${selectedType[NATIONAL_HOLIDAY]!""}>Observance</option>-->
                      <#--                    <option value="SEASON" ${selectedType[NATIONAL_HOLIDAY]!""}>Season</option>-->
                    <option value="NATIONAL_HOLIDAY" }>National holiday</option>
                    <option value="OBSERVANCE" }>Observance</option>
                    <option value="SEASON" }>Season</option>
                  </select>
                </div>
                <div class="form-group">
                  <label for="message">Description</label>
                  <textarea name="holiday_description" class="form-control"
                            value="${(oneHoliday.description)!""}"></textarea>
                </div>
          </form>
          </div>
          <div class=" modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                  </button>
                  <button type="button" class="btn btn-primary" id="add_holiday">Save changes
                  </button>
                </div>
              </div>
          </div>
        </div>
  </article>
</div>

