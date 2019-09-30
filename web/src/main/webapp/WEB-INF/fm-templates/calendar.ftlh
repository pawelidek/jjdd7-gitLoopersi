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

<script>
  window.listOfMyVacations = [
    <#if myVacationDates??>
    <#list myVacationDates>
    <#items as myVacationDate>
    new Date(${myVacationDate.year?long?c}, ${myVacationDate.month}, ${myVacationDate.day}),
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
        <#if myVacationDates??>
          <div>
            <table class="table table-borderless">
              <tr>
                <td style="text-align: center; width: 50%">
                  <div class="myVacationLegend">My vacation</div>
                </td>
                <td style="text-align: center; width: 50%">
                  <div class="vacationLegend">Teammates vacation</div>
                </td>
              </tr>
            </table>
          </div>
        </#if>
    </section>
  </div>
</div>
