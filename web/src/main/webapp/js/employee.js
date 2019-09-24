$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
});

$(function () {
  $(document).ready(function () {

    let errorsTag = $("#errors");
    errorsTag.empty();
    errorsTag.hide();

    $(".edit-employee").click(function (event) {
      var buttonId = $(this).attr('data-id');
      $.ajax({
        url: '/api/admin/employee/id/' + buttonId,
        method: "GET",
        success: function () {
        },
        error: function (error) {
          alert(buttonId);
        }
      }).done(function (data) {
        $('#id').val(buttonId);
        $('#label').html("Edit employee");
        $('#formMethod').val("put");
        $('#firstName').val(data.firstName);
        $('#secondName').val(data.secondName);
        $('#email').val(data.email);
        $('#team option[value=' + data.team.id + ']').attr("selected",
            "selected");
        $('#startDate').val(data.startDate.iso);
        $('#startHireDate').val(data.startHireDate.iso);
        $('#exampleModal').modal('toggle');
      });
    });
  });
});

$(function () {
  $(document).ready(function () {

    let errorsTag = $("#errors");
    errorsTag.empty();
    errorsTag.hide();

    $("#add_user_button").click(function (event) {
      $('#id').val("");
      $('#label').html("Add employee");
      $('#formMethod').val("post");
      $('#firstName').val("");
      $('#secondName').val("");
      $('#email').val("");
      $('#team').val("");
      $('#startDate').val("");
      $('#startHireDate').val("");
      $('#exampleModal').modal('toggle');
    });
  });
});

$(function () {
  $(document).ready(function () {
    $("#saveEmployee").click(function (event) {

      let errorsTag = $("#errors");
      errorsTag.empty();
      errorsTag.hide();

      $.ajax({
        url: "/api/admin/employee",
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
    $(".delete-employee").click(function () {

      var employeeId = $(this).attr('data-id');

      $.ajax({
        url: '/api/admin/employee?id=' + employeeId,
        type: 'DELETE',
        success: function (result) {
          $('#employee' + employeeId).remove();
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