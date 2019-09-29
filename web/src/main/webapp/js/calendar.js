var monthNamesRy = ["January", "February", "March", "April", "May", "June",
  "July", "August", "September", "October", "November", "December"];
var daysOfTheWeekRy = ["M", "T", "W", "T", "F", "S", "S"];

var d = new Date();
var year = d.getFullYear();
document.querySelector("#year").innerHTML = year;

var months = ["January", "February", "March", "April", "May", "June", "July",
  "August", "September", "October", "November", "December"];

var thisMonth = d.getMonth();

var yearDirection = 0;

var today = d.getDate();// 1 -31

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

function isVacation(candidate) {
  resetToDay(candidate);
  return window.listOfVacations.some((vacationDate) => {
    resetToDay(vacationDate);
    return vacationDate.getTime() === candidate.getTime();
  })
}

function isMyVacation(candidate) {
  resetToDay(candidate);
  return window.listOfMyVacations.some((vacationDate) => {
    resetToDay(vacationDate);
    return vacationDate.getTime() === candidate.getTime();
  })
}

function createDay(month, counter, order, monthDiv) {

  var candidate = new Date(2019, month + 1, counter);
  var day = document.createElement("div");

  if (month == thisMonth && counter == today) {
    day.setAttribute("class", "to day");
  } else if (isHoliday(candidate)) {
    day.setAttribute("class", "holiday");
    day.setAttribute("style", "order:" + order);
  } else if (isMyVacation(candidate)) {
    day.setAttribute("class", "myVacation");
    day.setAttribute("style", "order:" + order);
  } else if (isVacation(candidate)) {
    day.setAttribute("class", "vacation");
    day.setAttribute("style", "order:" + order);
  } else {
    day.setAttribute("class", "day");
  }
  day.setAttribute("style", "order:" + order);
  day.innerHTML = counter;
  monthDiv.appendChild(day);
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
    var hday = document.createElement("div");
    hday.setAttribute("class", "day OfWeek");
    hday.setAttribute("style", "order:" + i);
    hday.innerHTML = daysOfTheWeekRy[i].toUpperCase();
    monthDiv.appendChild(hday);
  }

  return monthDiv;

}

function daysInMonth(year, month) {
  return new Date(year, month + 1, 0).getDate();
}

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
