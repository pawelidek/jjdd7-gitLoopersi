$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
});

$(function () {
  $(document).ready(function () {
    $("#add_vacation_button").click(function (event) {
      $('#id').val("");
      $('#label').html("Add vacation");
      $('#formMethod').val("post");
      $('#dateFrom').val("");
      $('#dateTo').val("");
      $('#vacationType').val("");
      $('#exampleModal').modal('toggle');
    });
  });
});

$(function () {
  $(document).ready(function () {
    $("#saveVacation").click(function (event) {
      $.ajax({
        url: "/user/vacation",
        method: $('#formMethod').val(),
        data: $('form#settingForm').serialize(),
        success: function () {
          location.reload();
        },
        error: function (error) {
          alert('Error! Can\'t save changes. Check form data');
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