$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
});

$(function () {
  $(document).ready(function () {
    $(".edit-employee").click(function (event) {

      var buttonId = $(this).attr('data-id');
      $.ajax({
        url: '/api/employee/id/' + buttonId,
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
        $('#team').val(data.team);
        $('#startDate').val(data.startDate.iso);
        $('#startHireDate').val(data.startHireDate.iso);
        $('#exampleModal').modal('toggle');
      });
    });
  });
});

$(function () {
  $(document).ready(function () {
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
      $.ajax({
        url: "/admin/employee",
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
    $(".delete-employee").click(function () {
      $.ajax({
        url: '/admin/employee?id=' + $(this).attr('data-id'),
        type: 'DELETE',
        success: function (result) {
          location.reload();
        }
      });
    });
  });
});
