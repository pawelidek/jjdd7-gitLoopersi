<script>
  window.listOfDates = [
    <#list dates>
    <#items as date>
    new Date(${date.year?long?c}, ${date.month}, ${date.day}),
    </#items>
    </#list>
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