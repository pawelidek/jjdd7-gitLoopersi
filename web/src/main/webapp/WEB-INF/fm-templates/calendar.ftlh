<script>
  window.listOfDates = [
    <#list dates>
    <#items as date>
    new Date(${date.year?long?c}, ${date.month}, ${date.day}),
    </#items>
    </#list>
  ];
</script>

<script>
  window.listOfVacations = [
    <#if vacationDates??>
    <#list vacationDates>
    <#items as vacationDate>
    new Date(${vacationDate.year?long?c}, ${vacationDate.month}, ${vacationDate.day}),
    </#items>
    </#list>
    </#if>
  ];
</script>

<div class="main">
  <div id="calendar2" class="container">
    <section class="calendar-wrapper">
      <h1 id="year"></h1>
      <div class="calendar"></div>
    </section>
  </div>
</div>