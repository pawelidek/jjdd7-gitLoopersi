<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
</head>
<body>
Dear Admin,<br><br>
A list of notified vacations:<br><br>
<table style="border: 5px">
  <thead>
  <tr style="align-content: center">
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