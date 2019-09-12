// $(function () {
//
//   $(document).ready(function () {
//
//     $(".delete-employee").click(function () {
//
//       $.ajax({
//         url: '/admin/employee?id=' + $(this).attr('data-id'),
//         method: 'DELETE',
//         data: {id: buttonId},
//
//         success: function () {
//           alert('Employee has been deleted');
//           location.reload();
//         },
//         error: function (error) {
//           alert(buttonId);
//         }
//       });
//     });
//
//   });
// });

$(function () {
  $(document).ready(function () {
    $("#settingButton").click(function (event) {
      $.ajax({
        url: '/admin/employee?id=' + $(this).attr('data-id').val(),
        method: $('#setEmployeeMethod').val(),
        data: $('form#settingForm').serialize(),
        success: function () {
          location.reload();
        },
        error: function (error) {
          alert('Error! Holiday has not been added. Check data in your form');
        }
      });
    });
  });
});
===========================================================================

$(function () {
  $(document).ready(function () {
    $(".edit-employee").click(function (event) {

      var buttonId = $(event.target).attr('data-id');
      $.ajax({
        url: '/api/employee/id/'+ buttonId,
        method: "GET",
        success: function() {
        },
        error: function (error) {
          alert(buttonId);
        }
      }).done(function(data) {
        $('#id').val(buttonId);
        $('#label').html("Edit holiday");
        $('#formMethod').val("put");
        $('#holiday_name_input').val(data.name);
        $('#holiday_date_input').val(data.date.iso);
        $('#holiday_type_input').val(data.type[0]);
        $('#holiday_description_input').val(data.description);
        $('#exampleModal').modal('toggle');
      });
    });
  });
});

$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
})

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
          alert('Error! Holiday has not been added. Check data in your form');
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