<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
</head>
<body>
Dear Admin,<br><br>
A list of notified vacations:<br><br>
<table style="border: 1px">
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
      <#items as vacation>
        <tr>
          <td>${vacation.id}</td>
          <td>${vacation.dateFrom}</td>
          <td>${vacation.dateTo}</td>
          <td>${vacation.daysCount}</td>
          <td>${(vacation.deputy)!"Not stated"}</td>
          <td>${vacation.vacationType.type}</td>
          <td>${vacation.statusType}</td>
        </tr>
      </#items>
  </#list>
  </tbody>
</table>
</body>
</html>