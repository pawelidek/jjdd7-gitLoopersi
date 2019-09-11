<script>
  // function holidays() {

    var holidays = {
      "2019,5,1": "swietopracy",
      "2019,11,1": "swietozmarlych",
      "2019,1,1": "Nowy rok"
    };

  var holiday = ['2014-01-01', '2014-07-04', '2014-12-24', '2014-12-25'];



    // var Swieto_pracy = new Date(2019, 5, 1);
    // var y = Swieto_pracy.getDate().css("color", "#ff0000");
    //
    // var Swieto_zmarlych = new Date(2019,11,1);
    // var x = Swieto_zmarlych.getDate().css("color", "#ff0000");
    //
    // var Nowy_rok = new Date(2019,1,1);
    // var z = Nowy_rok.getDate().css("color", "#ff0000");


</script>
<div class="main">
  <div class="sideb">
    <div class="header"><i class="fa fa-angle-left" aria-hidden="true"></i><span><span class="month"> </span><span class="year"></span></span><i class="fa fa-angle-right" aria-hidden="true"></i></div>
    <div class="calender">
      <table>
        <thead>
        <tr class="weedays">
          <th data-weekday="sun" data-column="0">Sun</th>
          <th data-weekday="mon" data-column="1">Mon</th>
          <th data-weekday="tue" data-column="2">Tue</th>
          <th data-weekday="wed" data-column="3">Wed</th>
          <th data-weekday="thu" data-column="4">Thu</th>
          <th data-weekday="fri" data-column="5">Fri</th>
          <th data-weekday="sat" data-column="6">Sat</th>
        </tr>
        </thead>
        <tbody>
        <tr class="days" data-row="0">
          <td id="sun" style="color: #ff0000;" data-column="0"></td>
          <td data-column="1"></td>
          <td data-column="2"></td>
          <td data-column="3"></td>
          <td data-column="4"></td>
          <td data-column="5"></td>
          <td style="color: #ffc107;" data-column="6"></td>
        </tr>
        <tr class="days" data-row="1">
          <td style="color: #ff0000;"data-column="0"></td>
          <td data-column="1"></td>
          <td data-column="2"></td>
          <td data-column="3"></td>
          <td data-column="4"></td>
          <td data-column="5"></td>
          <td style="color: #ffc107;" data-column="6"></td>
        </tr>
        <tr class="days" data-row="2">
          <td style="color: #ff0000;" data-column="0"></td>
          <td data-column="1"></td>
          <td data-column="2"></td>
          <td data-column="3"></td>
          <td data-column="4"></td>
          <td data-column="5"></td>
          <td style="color: #ffc107;" data-column="6"></td>
        </tr>
        <tr class="days" data-row="3">
          <td style="color: #ff0000;"data-column="0"></td>
          <td data-column="1"></td>
          <td data-column="2"></td>
          <td data-column="3"></td>
          <td data-column="4"></td>
          <td data-column="5"></td>
          <td style="color: #ffc107;" data-column="6"></td>
        </tr>
        <tr class="days" data-row="4">
          <td style="color: #ff0000;"data-column="0"></td>
          <td data-column="1"></td>
          <td data-column="2"></td>
          <td data-column="3"></td>
          <td data-column="4"></td>
          <td data-column="5"></td>
          <td style="color: #ffc107;"data-column="6"></td>
        </tr>
        <tr class="days" data-row="5">
          <td style="color: #ff0000;"data-column="0"></td>
          <td data-column="1"></td>
          <td data-column="2"></td>
          <td data-column="3"></td>
          <td data-column="4"></td>
          <td data-column="5"></td>
          <td style="color: #ffc107;" data-column="6"></td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</div>