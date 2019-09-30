$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
});

$(function () {
  $(document).ready(function () {

    let errorsTag = $("#errors");
    errorsTag.empty();
    errorsTag.hide();

    $("#add_vacation_button").click(function (event) {
      $('#id').val("");
      $('#label').html("Add vacation");
      $('#formMethod').val("post");
      $('#dateFrom').val("");
      $('#dateTo').val("");
      $('#vacationType').val("");
      $('#deputy').val("");
      $('#exampleModal').modal('toggle');
    });
  });
});

$(function () {
  $(document).ready(function () {
    $("#saveVacation").click(function (event) {

      let errorsTag = $("#errors");
      errorsTag.empty();
      errorsTag.hide();

      $.ajax({
        url: "/api/user/vacation",
        method: $('#formMethod').val(),
        data: $('form#settingForm').serialize(),
        success: function () {
          location.reload();
        },
        error: function (error) {
          var errors = JSON.parse(error.responseText);
          var errorsHtml = "";
          for (var i = 0; i < errors.length; i++) {
            errorsHtml += "<strong>" + errors[i] + "</strong><br/>"
          }
          errorsTag.html(errorsHtml);
          errorsTag.show();
        }
      });
    });
  });
});

$(function () {
  $(document).ready(function () {
    $(".delete-vacation").click(function () {

      var vacationId = $(this).attr('data-id');

      $.ajax({
        url: '/user/vacation?id=' + vacationId,
        type: 'DELETE',
        success: function (result) {
          $('#vacation' + vacationId).remove();
        }
      });
    });
  });
});

window.setTimeout(function () {
  $(".alert-success").fadeTo(500, 0).slideUp(500, function () {
    $(this).remove();
  });
}, 1500);

$(function () {
  $(document).ready(function () {
    $("#searchByDates").click(function (event) {
      let dateFrom = $('#dateFrom').val();
      let dateTo = $('#dateTo').val();
      $.ajax({
        url: '/user/vacation?dateFrom=' + dateFrom + '&dateTo='
            + dateTo,
        type: 'GET',
        success: function () {
          window.location.href = '/user/vacation?dateFrom=' + dateFrom + '&dateTo='
              + dateTo
          ;
        }
      });
    });
  });
});

$(function () {
  $(document).ready(function () {
    $("#searchByDatesInTeams").click(function (event) {
      let dateFrom = $('#dateFrom1').val();
      let dateTo = $('#dateTo1').val();
      $.ajax({
        url: '/user/vacation/team?dateFrom=' + dateFrom + '&dateTo='
            + dateTo,
        type: 'GET',
        success: function () {
          window.location.href = '/user/vacation/team?dateFrom=' + dateFrom + '&dateTo='
              + dateTo
          ;
        }
      });
    });
  });
});