<!DOCTYPE html>
<html lang="en">
<head>
  <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
</head>
<body>
Dear Admin,<br><br>
A list of notified vacations:<br><br>
<table border="1" style="text-align: center">
  <thead>
  <tr style="text-align: center">
    <th>ID</th>
    <th>Date from</th>
    <th>Date to</th>
    <th>Days count</th>
    <th>Deputy</th>
    <th>Vacation type</th>
    <th>Status type</th>
  </tr>
  </thead>
  <tbody>
  <#list vacations>
      <#items as vacationView>
        <tr id="vacationView${vacationView.id}">
          <td>${vacationView.id}</td>
          <td>${vacationView.dateFrom}</td>
          <td>${vacationView.dateTo}</td>
          <td>${vacationView.daysCount}</td>
          <td>${(vacationView.deputy)!"Not stated"}</td>
          <td>${vacationView.vacationType.type}</td>
          <td>${vacationView.statusType}</td>
        </tr>
      </#items>
  </#list>
  </tbody>
</table>
</body>
</html>