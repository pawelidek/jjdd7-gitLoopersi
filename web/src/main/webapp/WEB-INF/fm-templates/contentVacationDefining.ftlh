<article id="contentView">

  <div class="container-fluid">
    <table class="table table-borderless">
      <tr>
        <td style="text-align: left"><h2>Vacation request</h2></td>
      </tr>
    </table>

      <#if errorMessage??>
        <div class="alert alert-danger" role="alert" style="text-align: center">
          <strong>${errorMessage}</strong>
        </div>
      <#elseif successMessage??>
        <div class="alert alert-success" role="alert" style="text-align: center">
          <strong>${successMessage}</strong>
        </div>
      </#if>

    <form method="post" action="/user/vacation/report">
      <div class="form-group">
        <label for="exampleInputDateFrom"><h5>From:</h5></label>
        <input type="date" name="dateFrom" class="form-control" id="exampleInputDateFrom"
               aria-describedby="emailHelp" placeholder="Enter vacation date from">
      </div>
      <div class="form-group">
        <label for="exampleInputDateTo"><h5>To:</h5></label>
        <input type="date" name="dateTo" class="form-control" id="exampleInputDateTo"
               placeholder="Enter vacation date to">
      </div>
      <table>
        <tr>
          <td style="text-align: left" width="100%"><br>
            <button type="submit" class="btn btn-outline-secondary"
                    data-toggle="modal" data-target="#exampleModal" id="add_team_button">
              <img src="/img/button_add.svg" alt="Add"> Request your vacation
            </button>
          </td>
        </tr>
      </table>
      <br>
    </form>
</article>