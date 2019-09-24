var monthNamesRy = ["January", "February", "March", "April", "May", "June",
  "July", "August", "September", "October", "November", "December"];
var daysOfTheWeekRy = [ "M", "T", "W", "T", "F", "S", "S"]

var d = new Date();
var year = d.getFullYear();// 2019
document.querySelector("#year").innerHTML = year;

var months = ["January", "February", "March", "April", "May", "June", "July",
  "August", "September", "October", "November", "December"];

// var d = new Date();

var thisMonth = d.getMonth();

var yearDirection = 0;

function getNextMonth() {
  yearDirection++;
  var current;
  var now = new Date();

  current = new Date(now.getFullYear() + yearDirection);
  // if (now.getMonth() == 11) {
  //   current = new Date(now.getFullYear() + monthDirection, 0, 1);
  // } else {
  //   current = new Date(now.getFullYear(), now.getMonth() + monthDirection, 1);
  // }
  createCalendar(month);
}

function getPrevMonth() {
  yearDirection--;
  var current;
  var now = new Date();

  current = new Date(now.getFullYear() + yearDirection);
  // if (now.getMonth() == 11) {
  //   current = new Date(now.getFullYear() + monthDirection, 0, 1);
  // } else {
  //   current = new Date(now.getFullYear(), now.getMonth() + monthDirection, 1);
  // }
  createCalendar(month);
}

// var thisMonth = d.getMonth();// 0 - 11
var today = d.getDate();// 1 -31
//var nthday = d.getDay();// 0 - 7
var daysOfTheMonthDiv = document.querySelectorAll(".daysOfTheMonth");

for (var month = 0; month < 12; month++) {
  createCalendar(month);
}

function createCalendar(month) {
  var monthDiv = createMonthHeader(month);

  var firstDayOfTheMonth = getFirstDayOfTheMonth(year, month);
  var daysinmonth = daysInMonth(year, month);
  var counter = 0, order = 7;

  for (var i = 1; i < firstDayOfTheMonth + 7; i++) {
    order++;
    createDay(month, "&nbsp;", order, monthDiv);
  }
  for (var i = firstDayOfTheMonth;
      i < daysInMonth(year, month) + firstDayOfTheMonth; i++) {
    counter++;
    order++;
    createDay(month, counter, order, monthDiv);
  }

  for (var i = firstDayOfTheMonth + daysinmonth; i < 6 * 7; i++) {
    order++;
    createDay(month, "&nbsp;", order, monthDiv);
  }

}

function resetToDay(date) {
  date.setHours(0);
  date.setMinutes(0);
  date.setSeconds(0);
  date.setMilliseconds(0);
}

function isHoliday(candidate) {
  resetToDay(candidate);
  return window.listOfDates.some((holidayDate) => {
    resetToDay(holidayDate);

    return holidayDate.getTime() === candidate.getTime();
  });
}

function createDay(month, counter, order, monthDiv) {
  var candidate = new Date(2019, month + 1, counter);
// if(order == 8){order = -1}
  var day = document.createElement("div");
  if (month == thisMonth && counter == today) {
    day.setAttribute("class", "to day");
  } else if (isHoliday(candidate)) {
    day.setAttribute("class", "holiday");
    day.setAttribute("style", "order:" + order);
  } else {
    day.setAttribute("class", "day");
  }
  day.setAttribute("style", "order:" + order);
  day.innerHTML = counter;
  monthDiv.appendChild(day);
  /*
  <div class="monthDiv">
  <div class="day">5</div>
  */
}

function createMonthHeader(month) {
  var calendar = document.querySelector(".calendar");

  var monthDiv = document.createElement("div");
  monthDiv.setAttribute("class", "month");
  calendar.appendChild(monthDiv);

  var h4 = document.createElement("h4");
  h4.innerHTML = monthNamesRy[month];
  monthDiv.appendChild(h4);

  for (var i = 0; i < 7; i++) {
    //var order = (i == 0) ? order = 7 : order = i;
    var hday = document.createElement("div");
    hday.setAttribute("class", "day OfWeek");
    hday.setAttribute("style", "order:" + i);
    hday.innerHTML = daysOfTheWeekRy[i].toUpperCase();
    monthDiv.appendChild(hday);
  }

  return monthDiv;

  /*
  <div class="month">

  <div class="monthHeader">
  <div class="day OfWeek">Sun</div>
  <div class="day OfWeek">Mon</div>
  <div class="day OfWeek">Tue</div>
  <div class="day OfWeek">Wed</div>
  <div class="day OfWeek">Thu</div>
  <div class="day OfWeek">Fri</div>
  <div class="day OfWeek">Sat</div>
  </div>

  <div class="daysOfTheMonth">
  */
}

function daysInMonth(year, month) {
  return new Date(year, month + 1, 0).getDate();//29/03/2016 (month + 1)
}

/*function leapYear(year){
  return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
}

function getNextMonth(month){
 if (month == 11) {
    var nextMonth = 0;
} else {
    var nextMonth = month+1;
}
return nextMonth;
}
*/
function getMonthName(month) {
  return monthNamesRy[month];
}

function getDayName(day) {
  return daysOfTheWeekRy[day];
}

function getFirstDayOfTheMonth(y, m) {
  var firstDay = new Date(y, m, 1);
  return firstDay.getDay();
}

function getLastDayOfTheMonth(y, m) {
  var lastDay = new Date(y, m + 1, 0);
  return lastDay.getDay();
}

// the popp up

var calendar = document.querySelector(".calendar");
var cloneCont = document.querySelector(".cloneCont");
var requestId = false;
calendar.addEventListener("click", function (e) {
  if (this.querySelector(".cloneCont")) {
    this.removeChild(this.querySelector(".cloneCont"));
  } else if (e.target.parentNode.className == 'month') {

    var monthClone = e.target.parentNode.cloneNode(true);
    monthClone.className += " cloneMonth";
    var cloneCont = document.createElement("div");
    cloneCont.className += " cloneCont";
    cloneCont.appendChild(monthClone);
    this.appendChild(cloneCont);

  }
}, false);

$(".fa-angle-left").click(function () {
  getPrevMonth();
  $(".main").addClass("is-rotated-left");
  setTimeout(function () {
    $(".main").removeClass("is-rotated-left");
  }, 195);
});

$(".fa-angle-right").click(function () {
  getNextMonth();
  $(".main").addClass("is-rotated-right");
  setTimeout(function () {
    $(".main").removeClass("is-rotated-right");
  }, 195);
});
