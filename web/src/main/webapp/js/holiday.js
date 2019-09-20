$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
})

$(function () {
  $(document).ready(function () {
    $(".delete-holiday").click(function (event) {
      var buttonId = $(event.target).attr('data-id');
      $.ajax({
        url: "/admin/holiday",
        method: "DELETE",
        data: {id: buttonId},
        success: function () {
          location.reload();
        },
        error: function (error) {
          alert('Error! Holiday has not been deleted');
        }
      });
    });
  });
});

$(function () {
  $(document).ready(function () {
    $(".update-holiday").click(function (event) {

      var buttonId = $(event.target).attr('data-id');
      $.ajax({
        url: '/api/holiday/id/' + buttonId,
        method: "GET",
        success: function () {
        },
        error: function (error) {
          alert('Error! Holiday has not been updated');
        }
      }).done(function (data) {
        $('#id').val(buttonId);
        $('#label').html("Edit holiday");
        $('#addHolidayMethod').val("put");
        $('#holiday_name_input').val(data.name);
        $('#holiday_date_input').val(data.date.iso);
        $('#holiday_type_input').val(data.type[0]);
        $('#holiday_description_input').val(data.description);
        $('#exampleModal').modal('toggle');
      });
    });
  });
});

$(function () {
  $(document).ready(function () {
    $("#add_holiday").click(function (event) {
      $.ajax({
        url: "/admin/holiday",
        method: $('#addHolidayMethod').val(),
        data: $('form#contactForm').serialize(),
        success: function () {
          location.reload();
        },
        error: function (error) {
          alert(
              'Error! Holiday has not been added/updated. Check data in your form');
        }
      });
    });
  });
});

$(function () {
  $(document).ready(function () {
    $("#add_user_button").click(function (event) {
      $('#id').val("");
      $('#label').html("Add holiday");
      $('#addHolidayMethod').val("post");
      $('#holiday_name_input').val("");
      $('#holiday_date_input').val("");
      $('#holiday_type_input').val("National holiday");
      $('#holiday_description_input').val("");
      $('#exampleModal').modal('toggle');
    });
  });
});


$(function () {
  $(document).ready(function () {
    $('#liveSearchHoliday').keyup(function () {
      if (this.value.length < 2) return;
      let substring = $('#liveSearchHoliday').val();
      $.ajax({
        url: '/api/holiday/param/' + substring,
        type: 'GET',

        success: function (data) {
          console.log(data);
          // let listOfNames = data;
          let result = data.map(function (holiday) {
            return holiday.name
          });
          $('#liveSearchHoliday').autocomplete({
            source: result,
            /*minLength: 3*/
          });
        }, error: function (error) {
          alert('Error!');
        }
      });
    });
  });
});


$(function () {
  $(document).ready(function () {
    $("#searchByPattern").click(function (event) {
      let holName = $('#liveSearchHoliday').val();
      $.ajax({
        url: '/search/holiday/name?name=' + holName,
        type: 'GET',
         success: function(){
           window.location.href = "/search/holiday/name?name="+holName
         ;}
      });
    });
  });
});

$(function () {
  $(document).ready(function () {
    $("#searchByDates").click(function (event) {
      let startDate = $('#startDate').val();
      let endDate = $('#endDate').val();
      $.ajax({
        url: '/search/holiday/dates?start_date=' + startDate + '&end_date=' +endDate,
        type: 'GET',
        success: function(){
          window.location.href = '/search/holiday/dates?start_date=' + startDate + '&end_date=' +endDate
          ;}
      });
    });
  });
});