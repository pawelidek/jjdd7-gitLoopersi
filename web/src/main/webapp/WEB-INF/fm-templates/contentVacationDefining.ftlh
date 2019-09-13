<div class="container">
  <article id="TRESC">
    <form method="post" action="/user/vacation">
      <div class="form-group">
        <label for="exampleInputDateFrom">From: </label>
        <input type="hidden" name="id" value="${employee.id}"/>
        <input type="date" name="dateFrom" class="form-control" id="exampleInputDateFrom"
               aria-describedby="emailHelp" placeholder="Enter vacation date from">
      </div>
      <div class="form-group">
        <label for="exampleInputDateTo">To :</label>
        <input type="date" name="dateTo" class="form-control" id="exampleInputDateTo"
               placeholder="Enter vacation date to">
      </div>
      <button type="submit" class="btn btn-primary">Send</button>
    </form>
  </article>
</div>