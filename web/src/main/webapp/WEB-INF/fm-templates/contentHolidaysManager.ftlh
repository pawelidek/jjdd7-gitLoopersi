<div class="container">
  <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
  <script src="/js/holiday.js"></script>
  <article id="TRESC">
    <button type="button" class="btn btn-secondary" id="add_user_button">
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
        <th scope="col" style="width: 50%">Description</th>
      </tr>
      </thead>
      <tbody>
        <#list holidays>
          <#items as holiday>
            <tr>
              <td>${holiday.id}.</td>
              <td>${holiday.name}</td>
              <td>${holiday.date}</td>
              <td>${(holiday.holidayType.type)!"National Holiday"}</td>
              <td>${(holiday.description)!""}</td>
              <td>
                <div style="text-align:center">
                  <button id='upd' data-id='${holiday.id}' class='update-holiday'
                          <#if (holiday.custom) == false>disabled</#if>>
                    Edit
                  </button>
                  <button id='del' data-id='${holiday.id}' class='delete-holiday'
                          <#if (holiday.custom) == false>disabled</#if> >
                    Delete
                  </button>
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
              <h5 class="modal-title" id="label">Add Holiday</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <form id="contactForm" name="contact" role="form">
              <input id="addHolidayMethod" type="hidden" name="method" value=""/>
              <input type="hidden" id="id" name="id" value=""/>
              <div class="modal-body">
                <div class="form-group">
                  <label for="name">Name</label>
                  <input type="text" name="holiday_name" id="holiday_name_input" autocomplete="off"
                         class="form-control value="">
                </div>
                <div class="form-group">
                  <label for="name">Date</label>
                  <input type="date" name="holiday_date" id="holiday_date_input"
                         class="form-control" value="">
                </div>
                <div class="form-group">
                  <label for="name">Type</label>
                  <select class="form-control" name="holiday_type" id="holiday_type_input">
                    <option value="National holiday" }>National holiday</option>
                    <option value="Observance" }>Observance</option>
                    <option value="Season" }>Season</option>
                  </select>
                </div>
                <div class="form-group">
                  <label for="message">Description</label>
                  <textarea name="holiday_description" class="form-control"
                            id="holiday_description_input"
                            value=""></textarea>
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

