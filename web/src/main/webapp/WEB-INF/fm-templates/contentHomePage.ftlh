<div class="container">
  <div class="container">
    <script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>
    <script type='text/javascript' src='/js/deleteHoliday.js'></script>
    <div>
      <article id="TRESC">
        <p><button id='add'>Add Holiday</button></p>
          <#list holidays>
            <table>
              <#items as holiday>
              <tr>
                <td>${holiday.id}.</td>
                <td>${holiday.name}</td>
                <td><button id='rev' data-id=${holiday.id}>Reviev</button></td>
                <td><button id='upd' data-id=${holiday.id}>Update</button></td>
                <td><button id='del' data-id='${holiday.id}' class='delete-holiday'>Delete</button></td>
              </tr>
              </#items>
            </table>
          </#list>
      </article>
    </div>
